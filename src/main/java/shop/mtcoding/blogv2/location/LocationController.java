package shop.mtcoding.blogv2.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    public List<Location> findAll() {
        List<Location> locations = locationService.findAll();
        return locations;

    }
}
