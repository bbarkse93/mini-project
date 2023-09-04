package shop.mtcoding.blogv2.notice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.apply.Apply;
import shop.mtcoding.blogv2.edu.Edu;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.wishduty.WishDuty;
import shop.mtcoding.blogv2.wishskill.WishSkill;

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
    private String phoneNumber;

    @Column
    private String companyInfo;

    @Column
    private String companyPicUrl;

    @Column
    private String location;

    @Column
    private String intake;

    @Column
    private String pay;

    @Column
    private String qualification;

    @Column
    private String period;

    @OneToMany(mappedBy = "notice", fetch = FetchType.LAZY)
    private List<WishSkill> wishSkills = new ArrayList<>();

    @OneToMany(mappedBy = "notice", fetch = FetchType.LAZY)
    private List<WishDuty> wishDutys = new ArrayList<>();

    @OneToMany(mappedBy = "notice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apply> applies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Edu edu;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Notice(Integer id, String title, String companyName, String companyEmail, String phoneNumber,
            String companyInfo, String companyPicUrl, String location, String intake, String pay, String qualification,
            String period, List<WishSkill> wishSkills, List<WishDuty> wishDutys, User user, Edu edu,
            Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.phoneNumber = phoneNumber;
        this.companyInfo = companyInfo;
        this.companyPicUrl = companyPicUrl;
        this.location = location;
        this.intake = intake;
        this.pay = pay;
        this.qualification = qualification;
        this.period = period;
        this.wishSkills = wishSkills;
        this.wishDutys = wishDutys;
        this.user = user;
        this.edu = edu;
        this.createdAt = createdAt;
    }

}
