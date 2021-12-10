package rest;

import com.google.gson.Gson;
import dto.UserCreationDto;
import dto.UserDto;
import dto.mapper.UserMapper;
import entity.UserEntity;
import service.UserService;
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

@WebServlet(name = "UserRestControllerV1", urlPatterns = "/storage/v1/users/")
public class UserRestControllerV1 extends HttpServlet {

    private Gson gson;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        if (Pattern.compile("/users/\\d+$").matcher(requestUrl).find()) {
            Long id = Long.parseLong(requestUrl.substring("/storage/users/".length()));

            UserDto userDto = UserMapper
                    .userDto(
                            userService.getById(id));

            if (userDto != null) {
                String json = gson.toJson(userDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(json);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/users$").matcher(requestUrl).find()) {

            List<UserEntity> userEntities = userService.findAll();

            String json = gson.toJson(userEntities);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

        UserCreationDto userDto = gson.fromJson(fromJson, UserCreationDto.class);

        UserEntity userEntity = UserMapper.toUser(userDto);

        String toJson = gson
                .toJson(
                        UserMapper
                                .userIdDto(
                                        userService
                                                .save(userEntity)));

        resp.setStatus(SC_CREATED);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        Long id = Long.parseLong(requestUri.substring("/storage/v1/users/".length()));

        String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

        UserEntity userEntity = gson.fromJson(fromJson, UserEntity.class);
        userEntity.setId(id);

        String toJson = gson.toJson(
                userService
                        .update(userEntity));

        resp.setStatus(SC_OK);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        Long id = Long.parseLong(requestUri.substring("/storage/v1/users/".length()));

        UserEntity userEntity =
                userService.deleteById(id);

        String toJson = gson.toJson(userEntity);

        resp.setStatus(SC_OK);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }
}