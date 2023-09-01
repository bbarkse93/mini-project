package shop.mtcoding.blogv2.apply;

import javax.persistence.Column;
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

@NoArgsConstructor
@Setter
@Getter
@Table(name = "apply_tb")
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean status;

    @ManyToOne
    private Resume resume;

    @ManyToOne
    private Notice notice;

    @Builder
    public Apply(Integer id, Boolean status, Resume resume, Notice notice) {
        this.id = id;
        this.status = status;
        this.resume = resume;
        this.notice = notice;
    }

}
