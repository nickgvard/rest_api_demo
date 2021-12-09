package rest;

import com.google.gson.Gson;
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
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */
/**
http:www.example.com/developer/v2/resource
http:www.developer.example.com/v1/resource
 */
@WebServlet(urlPatterns = "/storage/v1/users")
public class UserRestControllerV1 extends HttpServlet {

    private static final Gson GSON = new Gson();
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        if (Pattern.compile("/api/v1/users/\\d+$").matcher(requestUrl).find()) {
            Long id = Long.parseLong(requestUrl.substring("/users/".length()));

            UserDto userDto = UserMapper
                    .userDto(
                            userService.getById(id));

            if (userDto != null) {
                String json = GSON.toJson(userDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(json);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/users$").matcher(requestUrl).find()) {

            List<UserEntity> userEntities = userService.findAll();

            String json = GSON.toJson(userEntities);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ServletUtil.fromServletInputStream(req.getInputStream());

        UserEntity userEntity = GSON.fromJson(json, UserEntity.class);

        String saved = GSON.toJson(
                userService.save(userEntity));

        resp.setStatus(SC_CREATED);
        resp.setContentType("application/json");
        resp.getOutputStream().println(saved);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        Long id = Long.parseLong(requestUri.substring("/users/".length()));

        String json = ServletUtil.fromServletInputStream(req.getInputStream());

        UserEntity userEntity = GSON.fromJson(json, UserEntity.class);
        userEntity.setId(id);

        userService.update(userEntity);

        resp.setStatus(SC_OK);
        resp.setContentType("application/json");
        resp.getOutputStream().println(
                GSON.toJson(userEntity));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        Long id = Long.parseLong(requestUri.substring("/users/".length()));

        UserEntity userEntity =
                userService.deleteById(id);

        resp.setStatus(SC_OK);
        resp.setContentType("application/json");
        resp.getOutputStream().println(
                GSON.toJson(userEntity));
    }
}