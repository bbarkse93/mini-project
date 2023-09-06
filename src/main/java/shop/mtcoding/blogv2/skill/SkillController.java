package shop.mtcoding.blogv2.skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    public List<Skill> findAll() {
        List<Skill> skills = skillService.findAll();
        return skills;

    }
}
