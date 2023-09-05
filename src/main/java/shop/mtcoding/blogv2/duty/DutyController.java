package shop.mtcoding.blogv2.duty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DutyController {

    @Autowired
    private DutyService dutyService;

    public List<Duty> findAll() {
        List<Duty> dutys = dutyService.findAll();
        return dutys;

    }
}
