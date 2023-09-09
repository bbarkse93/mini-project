package shop.mtcoding.blogv2.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

}
