package rest;

import com.google.gson.Gson;
import dto.eventdto.EventCreationDTO;
import dto.eventdto.EventDTO;
import dto.eventdto.EventIdDTO;
import dto.mapper.EventEntityMapper;
import entity.EventEntity;
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

import static javax.servlet.http.HttpServletResponse.*;

/**
 * @author Nikita Gvardeev
 * 07.12.2021
 */


@WebServlet(name = "EventRestControllerV1", urlPatterns = "/storage/v1/events/*")
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

            EventDTO eventDto = EventEntityMapper.toEventDto(
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

            List<EventEntity> fileEntities = eventService.findAll();

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

        EventEntity eventEntity = EventEntity
                .builder()
                .name(name)
                .build();

        EventIdDTO eventIdDto = EventEntityMapper
                .toEventIdDto(
                        eventService
                                .save(eventEntity));

        String toJson = gson.toJson(eventIdDto);

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

            EventEntity eventEntity = gson.fromJson(fromJson, EventEntity.class);
            eventEntity.setId(id);

            EventDTO eventDto = EventEntityMapper
                    .toEventDto(
                            eventService
                                    .update(eventEntity));

            String toJson = gson.toJson(eventDto);

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

            EventEntity eventEntity =
                    eventService.deleteById(id);

            EventDTO eventDto = EventEntityMapper
                    .toEventDto(eventEntity);

            String toJson = gson.toJson(eventDto);

            resp.setStatus(SC_OK);
            resp.setContentType("application/json");
            resp.getOutputStream().println(toJson);
        } else
            resp.setStatus(SC_BAD_REQUEST);
    }
}