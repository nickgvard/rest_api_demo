package rest;

import com.google.gson.Gson;
import dto.FileDto;
import dto.mapper.FileMapper;
import entity.FileEntity;
import service.FileService;
import util.ServletUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */

@WebServlet(name = "FileRestControllerV1", urlPatterns = "/storage/v1/files/")
public class FileRestControllerV1 extends HttpServlet {

    private Gson gson;
    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        fileService = new FileService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        if (Pattern.compile("/files/\\d+$").matcher(requestUrl).find()) {
            Long id = Long.parseLong(requestUrl.substring("/storage/v1/files/".length()));

            FileDto fileDto = FileMapper.toFileDto(
                    fileService
                            .getById(id));

            if (fileDto != null) {
                String toJson = gson.toJson(fileDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(toJson);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/files$").matcher(requestUrl).find()) {

            List<FileEntity> fileEntities = fileService.findAll();

            String toJson = gson.toJson(fileEntities);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        FileDto fileDto = gson.fromJson(requestUri, FileDto.class);

        FileEntity fileEntity = FileMapper.toFile(fileDto);

        String toJson = gson.toJson(
                fileService
                        .save(fileEntity));

        resp.setStatus(SC_CREATED);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        Long id = Long.parseLong(requestUri.substring("/storage/v1/files/".length()));

        String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

        FileEntity fileEntity = gson.fromJson(fromJson, FileEntity.class);
        fileEntity.setId(id);

        String toJson = gson.toJson(
                fileService
                        .update(fileEntity));

        resp.setStatus(SC_OK);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        Long id = Long.parseLong(requestUrl.substring("/storage/v1/files/".length()));

        FileEntity fileEntity =
                fileService.deleteById(id);

        String toJson = gson.toJson(fileEntity);

        resp.setStatus(SC_OK);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }
}
