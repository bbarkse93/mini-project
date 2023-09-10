package shop.mtcoding.blogv2.apply;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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

    @Autowired
    private HttpSession session;

    public List<Apply> getAppliesByStatus(Integer resumeId) {
        // Assuming you have a method in your repository to fetch applies by user ID

        return applyRepository.findByResumeId(resumeId);
    }

    public List<Apply> 지원현황조회(Integer resumeId) {
        // Assuming you have a method in your repository to fetch applies by user ID
        return applyRepository.findByResumeId(resumeId);
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
        User sessionUser = (User) session.getAttribute("sessionUser");
        Apply apply = Apply.builder()
                .notice(Notice.builder().id(id).build())
                .resume(Resume.builder().id(id).build())

                .user(User.builder().id(sessionUser.getId()).build())
                .status(true)
                .build();
        applyRepository.save(apply);
    }

}