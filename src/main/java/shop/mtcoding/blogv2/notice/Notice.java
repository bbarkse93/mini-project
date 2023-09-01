package shop.mtcoding.blogv2.notice;

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
@Table(name = "notice_tb")
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column
    private String companyName;

    @Column
    private String companyEmail;

    @Column
    private String companyInfo;

    @Column
    private String companyPic;

    @Column
    private String location;

    @Column
    private String intake;

    @Column
    private String pay;

    @Column
    private String qualification;

    @Column
    private Timestamp period;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Edu eud;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Notice(Integer id, String title, String companyName, String companyEmail, String companyInfo,
            String companyPic, String location, String intake, String pay, String qualification, Timestamp period,
            User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyInfo = companyInfo;
        this.companyPic = companyPic;
        this.location = location;
        this.intake = intake;
        this.pay = pay;
        this.qualification = qualification;
        this.period = period;
        this.user = user;
        this.createdAt = createdAt;
    }

}
