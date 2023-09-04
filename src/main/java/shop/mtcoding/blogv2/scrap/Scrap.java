package shop.mtcoding.blogv2.scrap;

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
import shop.mtcoding.blogv2.user.User;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "scrap_tb")
@Entity
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @Builder
    public Scrap(Integer id, User user, Notice notice) {
        this.id = id;
        this.user = user;
        this.notice = notice;
    }

}
