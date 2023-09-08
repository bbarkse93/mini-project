package shop.mtcoding.blogv2.apply;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2.apply.ApplyRequest.applyDTO;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeRepository;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.resume.ResumeRepository;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserRepository;

@Service
public class ApplyService {

    @Autowired

    private  ApplyRepository applyRepository;
    private  NoticeRepository noticeRepository;
    private  UserRepository userRepository;
    private  ResumeRepository resumeRepository;

    public List<Apply> getAppliesByStatus(Integer userId) {
        // Assuming you have a method in your repository to fetch applies by user ID
        return applyRepository.findByUserId(userId);
    }

    public List<Apply> 지원현황조회(Integer userId) {
        // Assuming you have a method in your repository to fetch applies by user ID
        return applyRepository.findByUserId(1);
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
    public void 지원하기() {
        Apply apply = Apply.builder()
                .notice(Notice.builder().id(1).build())
                .resume(Resume.builder().id(1).build())
                .user(User.builder().id(1).build())
                .status(true)
                .build();
        applyRepository.save(apply);
    }

}