package shop.mtcoding.blogv2.edu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduService {

    @Autowired
    private EduRepository eduRepository;

    public List<Edu> findAll() {
        List<Edu> edus = eduRepository.findAll();
        return edus;
    }
}
