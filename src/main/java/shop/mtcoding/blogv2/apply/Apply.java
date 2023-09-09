package shop.mtcoding.blogv2.apply;

import java.util.Optional;

import javax.persistence.Column;
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
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.user.User;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "apply_tb")
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Builder
    public Apply(Integer id, Boolean status, Notice notice, User user, Resume resume) {
        this.id = id;
        this.status = status;
        this.notice = notice;
        this.user = user;
        this.resume = resume; // 수정된 부분
    }

}