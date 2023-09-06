package shop.mtcoding.blogv2.duty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DutyService {

    @Autowired
    private DutyRepository dutyRepository;

    public List<Duty> findAll() {
        List<Duty> dutys = dutyRepository.findAll();
        return dutys;
    }
}
