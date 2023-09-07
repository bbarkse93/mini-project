package shop.mtcoding.blogv2.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }
}
