package shop.mtcoding.blogv2.apply;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.user.User;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    public List<Apply> getAppliesByStatus(Integer userId) {
        // Assuming you have a method in your repository to fetch applies by user ID

        return applyRepository.findByUserId(userId);
    }

    public List<Apply> 지원현황조회(Integer userId) {
        // Assuming you have a method in your repository to fetch applies by user ID
        return applyRepository.findByUserId(userId);
    }

    public Long 지원개수() {
        return applyRepository.findAllCoutnt();
    }

    @Transactional
    public void 기업지원관리(Integer id, ApplyRequest.UpdateDTO updateDTO) {

        Optional<Apply> optionalApply = applyRepository.findById(id);

        if (optionalApply.isPresent()) {
            Apply apply = optionalApply.get();
            apply.setStatus(updateDTO.getStatus());

            applyRepository.save(apply);
        } else {

        }
    }

    
    @Transactional
    public void 지원하기(Integer id) {
      
        Apply apply = Apply.builder()
                .notice(Notice.builder().id(id).build())
                .resume(Resume.builder().id(id).build())
                
                .user(User.builder().id(id).build())
                .status(true)
                .build();
        applyRepository.save(apply);
    }


  
}