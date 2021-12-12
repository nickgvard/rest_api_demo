package rest;

import com.google.gson.Gson;
import dto.UserDTO;
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
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */

@WebServlet(name = "UserRestControllerV1", urlPatterns = {"/api/v1/users/*"})
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

            UserDTO userDto = UserDTO.toDTO(
                            userService.getById(id));

            if (userDto != null) {
                String json = gson.toJson(userDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(json);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/users(/)*$").matcher(requestUrl).find()) {

            List<UserDTO> userEntities = userService.findAll()
                    .stream()
                    .map(UserDTO::toDTO)
                    .collect(Collectors.toList());

            String json = gson.toJson(userEntities);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(json);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        UserDTO userDTO = UserDTO
                .builder()
                .name(name)
                .build();

        userDTO = UserDTO
                .toDTO(
                        userService
                                .save(
                                        UserDTO
                                                .toEntity(userDTO)));

        String toJson = gson.toJson(userDTO);

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

            UserDTO userDTO = gson.fromJson(fromJson, UserDTO.class);
            userDTO.setId(id);

            userService.update(
                    UserDTO
                            .toEntity(userDTO));

            String toJson = gson.toJson(userDTO);

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

            userService.deleteById(id);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }
}