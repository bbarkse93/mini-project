package shop.mtcoding.blogv2.edu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EduController {

    @Autowired
    private EduService eduService;

    public List<Edu> findAll() {
        List<Edu> edus = eduService.findAll();
        return edus;

    }

}
