package shop.mtcoding.blogv2.duty.wishduty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;

public interface WishDutyRepository extends JpaRepository<WishDuty, Integer> {
    List<WishDuty> findByNoticeId(Integer noticeId);

    List<WishDuty> findByResumeId(Integer id);

    void deleteAllByResume(Resume resume);

    void deleteAllByNotice(Notice notice);
}
