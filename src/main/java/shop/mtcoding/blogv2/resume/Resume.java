package shop.mtcoding.blogv2.resume;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.edu.Edu;
import shop.mtcoding.blogv2.user.User;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "resume_tb")
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( length = 100)
    private String title;
    

    @Column
    private String personalEmail;

    @Column
    private String phoneNumber;

    @Column
    private String coverLetter;

    @Column
    private String personalPicUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Edu edu;

    @CreationTimestamp
    private Timestamp createdAt;


    @Builder
    public Resume(Integer id, String title, String personalEmail, String phoneNumber, String coverLetter,
            String personalPicUrl, User user, Edu edu, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.coverLetter = coverLetter;
        this.personalPicUrl = personalPicUrl;
        this.user = user;
        this.edu = edu;
        this.createdAt = createdAt;
    }

   

}
