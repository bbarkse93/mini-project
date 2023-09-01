package shop.mtcoding.blogv2.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional
    public void deleteById(Integer id) {
        resumeRepository.deleteById(1);
    }


}
