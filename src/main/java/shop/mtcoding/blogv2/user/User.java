package shop.mtcoding.blogv2.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = true, length = 20)
    private String personalName;

    @Column(nullable = true, length = 20)
    private String companyName;

    @Column(nullable = true, length = 10)
    private String personalBirth;

    @Column(nullable = true)
    private String proprietaryNumber;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true, length = 20)
    private String email;

    @Column(nullable = true)
    private Integer personal_id;

    @Column(nullable = true)
    private Integer company_id;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String username, String password, String personalName, String companyName,
            String personalBirth, String proprietaryNumber, String phoneNumber, String email, Integer personal_id,
            Integer company_id, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.personalName = personalName;
        this.companyName = companyName;
        this.personalBirth = personalBirth;
        this.proprietaryNumber = proprietaryNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.personal_id = personal_id;
        this.company_id = company_id;
        this.createdAt = createdAt;
    }

    
}
