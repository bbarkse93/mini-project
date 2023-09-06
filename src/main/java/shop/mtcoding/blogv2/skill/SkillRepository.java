package shop.mtcoding.blogv2.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    // @Query("select s from Skill s where s.skill_name = :skillName")
    // Skill findBySkillName(@Param("skillName") String skillName);

    @Query("SELECT s FROM Skill s WHERE s.skillName = :skillName")
    Skill findBySkillName(@Param("skillName") String skill);

}
