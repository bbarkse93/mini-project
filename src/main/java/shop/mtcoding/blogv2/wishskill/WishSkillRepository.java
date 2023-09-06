package shop.mtcoding.blogv2.wishskill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishSkillRepository extends JpaRepository<WishSkill, Integer> {
    // 추가적인 메서드가 필요한 경우 여기에 선언할 수 있습니다.
}