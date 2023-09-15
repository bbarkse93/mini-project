package shop.mtcoding.blogv2.apply;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.blogv2._core.error.MyExceptionHandler;
import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.user.User;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private HttpSession session;

    public List<Apply> getAppliesByStatus(Integer userId) {
        // Assuming you have a method in your repository to fetch applies by user ID

        return applyRepository.findByUserId(userId);
    }

    public List<Apply> 지원현황조회(Integer userId) {
        // Assuming you have a method in your repository to fetch applies by user ID
        return applyRepository.findByUserId(userId);
    }

    public Long 지원개수(Integer userId) {
        return applyRepository.findByUserIdCount(userId);
    }

    @Transactional
    public void 기업지원관리(Integer id, ApplyRequest.UpdateDTO updateDTO) {

        Optional<Apply> optionalApply = applyRepository.findById(id);

        if (optionalApply.isPresent()) {
            Apply apply = optionalApply.get();
            apply.setStatus(updateDTO.getStatus());
            try {
                applyRepository.save(apply);
            } catch (Exception e) {
                throw new MyException("지원서를 찾을 수 없어요");
            }
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
        try {
            applyRepository.save(apply);
        } catch (Exception e) {
            throw new MyException(id + "를 찾을 수 없어요");
        }
    }

}