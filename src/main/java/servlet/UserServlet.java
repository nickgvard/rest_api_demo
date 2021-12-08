package servlet;

import com.google.gson.Gson;
import controller.UserController;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
public class UserServlet extends HttpServlet {

    private static final Gson GSON = new Gson();
    private UserController userController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userController = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI();

        //getById();
        if (Pattern.compile("/users/\\d+$").matcher(requestUrl).find()) {
            Long id = Long.parseLong(requestUrl.substring("/users/".length()));

            User user = userController.getById(id);

            if (user != null) {
                String json = GSON.toJson(user);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(json);
            } else
                resp.setStatus(SC_NO_CONTENT);
        }//findAll();
        else if (Pattern.compile("/users$").matcher(requestUrl).find()) {
            List<User> users = userController.findAll();

            if (!users.isEmpty()) {
                String json = GSON.toJson(users);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(json);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = User
                .builder()
                .name(req.getParameter("name"))
                .build();

        userController.save(newUser);
    }


}
