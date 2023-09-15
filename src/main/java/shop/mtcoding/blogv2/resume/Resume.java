package shop.mtcoding.blogv2.resume;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.apply.Apply;
import shop.mtcoding.blogv2.bookmark.Bookmark;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.wishduty.WishDuty;
import shop.mtcoding.blogv2.wishskill.WishSkill;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "resume_tb")
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String title;

    @Column
    private String personalName;

    @Column
    private String personalEmail;

    @Column
    private String phoneNumber;

    @Column
    private String coverLetter;

    @Column
    private String personalPicUrl;

    @Column
    private String edu;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WishSkill> wishSkills = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WishDuty> wishDutys = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apply> applies = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Resume(Integer id, String title, String personalName, String personalEmail, String phoneNumber,
            String coverLetter, String personalPicUrl, String edu, User user, List<WishSkill> wishSkills,
            List<WishDuty> wishDutys, List<Apply> applies, List<Bookmark> bookmarks,
            Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.personalName = personalName;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.coverLetter = coverLetter;
        this.personalPicUrl = personalPicUrl;
        this.edu = edu;
        this.user = user;
        this.wishSkills = wishSkills;
        this.wishDutys = wishDutys;
        this.applies = applies;
        this.bookmarks = bookmarks;
        this.createdAt = createdAt;
    }

}
