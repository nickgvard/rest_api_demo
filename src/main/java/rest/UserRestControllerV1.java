package rest;

import com.google.gson.Gson;
import dto.userdto.UserCreationDTO;
import dto.userdto.UserDTO;
import dto.mapper.UserEntityMapper;
import dto.userdto.UserIdDTO;
import entity.UserEntity;
import service.implementation.UserServiceImpl;
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

@WebServlet(name = "UserRestControllerV1", urlPatterns = {"/storage/v1/users/*"})
public class UserRestControllerV1 extends HttpServlet {

    private Gson gson;
    private UserServiceImpl userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUrl = req.getRequestURI();

        if (Pattern.compile("/users/\\d+$").matcher(requestUrl).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            UserDTO userDto = UserEntityMapper
                    .toUserDto(
                            userService.getById(id));

            if (userDto != null) {
                String json = gson.toJson(userDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(json);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/users(/)*$").matcher(requestUrl).find()) {

            List<UserEntity> userEntities = userService.findAll();

            String json = gson.toJson(userEntities);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(json);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

        UserCreationDTO userDto = gson.fromJson(fromJson, UserCreationDTO.class);

        UserEntity userEntity = UserEntityMapper.userEntity(userDto);

        UserIdDTO userIdDto = UserEntityMapper
                .userIdDto(
                        userService
                                .save(userEntity));

        String toJson = gson.toJson(userIdDto);

        resp.setStatus(SC_CREATED);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();

        if (Pattern.compile("/users/\\d+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

            UserEntity userEntity = gson.fromJson(fromJson, UserEntity.class);
            userEntity.setId(id);

            UserDTO userDto = UserEntityMapper
                    .toUserDto(
                            userService
                                    .update(userEntity));

            String toJson = gson.toJson(userDto);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();

        if (Pattern.compile("/users/\\d+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            UserEntity userEntity =
                    userService.deleteById(id);

            UserDTO userDto = UserEntityMapper
                    .toUserDto(userEntity);

            String toJson = gson.toJson(userDto);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }
}