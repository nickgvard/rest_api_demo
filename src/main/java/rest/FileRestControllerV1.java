package rest;

import com.google.gson.Gson;
import dto.FileDTO;
import entity.FileEntity;
import service.implementation.FileServiceImpl;
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
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */

@WebServlet(name = "FileRestControllerV1", urlPatterns = "/api/v1/files/*")
public class FileRestControllerV1 extends HttpServlet {

    private Gson gson;
    private FileServiceImpl fileServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        fileServiceImpl = new FileServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();

        if (Pattern.compile("/files/\\d+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            FileDTO fileDto = FileDTO.toDTO(
                    fileServiceImpl.getById(id));

            if (fileDto != null) {
                String toJson = gson.toJson(fileDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(toJson);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/files$").matcher(requestUri).find()) {

            List<FileDTO> fileEntities = fileServiceImpl.findAll()
                    .stream()
                    .map(FileDTO::toDTO)
                    .collect(Collectors.toList());

            String toJson = gson.toJson(fileEntities);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        FileDTO fileDTO = FileDTO
                .builder()
                .name(name)
                .build();

        fileDTO = FileDTO
                .toDTO(
                        fileServiceImpl
                                .save(
                                        FileDTO
                                                .toEntity(fileDTO)));

        String toJson = gson.toJson(fileDTO);

        resp.setStatus(SC_CREATED);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();

        if (Pattern.compile("/files/\\d+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

            FileDTO fileDTO = gson.fromJson(fromJson, FileDTO.class);
            fileDTO.setId(id);

            fileServiceImpl.update(
                    FileDTO
                            .toEntity(fileDTO));

            String toJson = gson.toJson(fileDTO);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();

        if (Pattern.compile("/files/\\d+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            fileServiceImpl.deleteById(id);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }
}