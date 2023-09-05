package shop.mtcoding.blogv2.wishduty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "wish_duty_tb")
@Entity
public class WishDuty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Duty duty;

    @Builder
    public WishDuty(Integer id, Resume resume, Notice notice, Duty duty) {
        this.id = id;
        this.resume = resume;
        this.notice = notice;
        this.duty = duty;
    }

}
