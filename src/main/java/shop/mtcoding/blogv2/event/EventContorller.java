package shop.mtcoding.blogv2.event;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventContorller {

    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public String event(HttpServletRequest request) {
        List<Event> events = eventService.findAll();
        request.setAttribute("events", events);
        return "main/event";
    }

}
