package shop.mtcoding.blogv2.skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        List<Skill> skills = skillRepository.findAll();
        return skills;
    }

}
