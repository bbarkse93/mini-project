package shop.mtcoding.blogv2.skill.wishskill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;

public interface WishSkillRepository extends JpaRepository<WishSkill, Integer> {
    List<WishSkill> findByNoticeId(Integer noticeId);

    List<WishSkill> findByResumeId(Integer id);

    void deleteAllByResume(Resume resume);

    void deleteAllByNotice(Notice notice);
}