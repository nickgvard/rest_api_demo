package rest;

import com.google.gson.Gson;
import dto.EventDTO;
import service.implementation.EventServiceImpl;
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

@WebServlet(name = "EventRestControllerV1", urlPatterns = "/api/v1/events/*")
public class EventRestControllerV1 extends HttpServlet {

    private EventServiceImpl eventService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        eventService = new EventServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        if (Pattern.compile("/events/(\\d)+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            EventDTO eventDto = EventDTO.toDTO(
                    eventService
                            .getById(id));

            if (eventDto != null) {
                String toJson = gson.toJson(eventDto);

                resp.setStatus(SC_OK);
                resp.setContentType("application/json");
                resp.getOutputStream().println(toJson);
            } else
                resp.setStatus(SC_NO_CONTENT);
        } else if (Pattern.compile("/events(/)*$").matcher(requestUri).find()) {

            List<EventDTO> events = eventService.findAll()
                    .stream()
                    .map(EventDTO::toDTO)
                    .collect(Collectors.toList());

            String toJson = gson.toJson(events);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);

        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");

        EventDTO eventDTO = EventDTO
                .builder()
                .name(newName)
                .build();

        eventDTO = EventDTO
                .toDTO(
                        eventService
                                .save(
                                        EventDTO
                                                .toEntity(eventDTO)));

        String toJson = gson.toJson(eventDTO);

        resp.setStatus(SC_CREATED);
        resp.setContentType("application/json");
        resp.getOutputStream().println(toJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        if (Pattern.compile("/events/(\\d)+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            String fromJson = ServletUtil.fromServletInputStream(req.getInputStream());

            EventDTO eventDTO = EventDTO.toDTO(fromJson);
            eventDTO.setId(id);

            eventService.update(
                    EventDTO
                            .toEntity(eventDTO));

            String toJson = gson.toJson(eventDTO);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI();

        if (Pattern.compile("/events/(\\d)+$").matcher(requestUri).find()) {

            String pathInfo = req.getPathInfo();

            Long id = Long.parseLong(pathInfo.substring(1));

            eventService.deleteById(id);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }
}