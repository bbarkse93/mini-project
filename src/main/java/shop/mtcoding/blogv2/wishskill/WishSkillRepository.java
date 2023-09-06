package shop.mtcoding.blogv2.wishskill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.skill.Skill;

public interface WishSkillRepository extends JpaRepository<WishSkill, Integer> {
    List<WishSkill> findByNoticeId(Integer noticeId);

    void deleteByNoticeId(Integer id);

    WishSkill findByNoticeAndSkill(Notice notice, Skill skill);
}