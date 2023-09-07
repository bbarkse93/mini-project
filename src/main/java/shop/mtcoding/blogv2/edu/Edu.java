package shop.mtcoding.blogv2.edu;

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
import shop.mtcoding.blogv2.resume.Resume;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "edu_tb")
@Entity
public class Edu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @Builder
    public Edu(Integer id, String grade, Resume resume) {
        this.id = id;
        this.grade = grade;
        this.resume = resume;
    }

}
