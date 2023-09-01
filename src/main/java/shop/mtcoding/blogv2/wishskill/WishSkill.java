package shop.mtcoding.blogv2.wishskill;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.skill.Skill;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "wish_skill_tb")
@Entity
public class WishSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Resume resume;

    @ManyToOne
    private Notice notice;

    @ManyToOne
    private Skill skill;

    @Builder
    public WishSkill(Integer id, Resume resume, Notice notice, Skill skill) {
        this.id = id;
        this.resume = resume;
        this.notice = notice;
        this.skill = skill;
    }

}
