package shop.mtcoding.blogv2.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.notice.Notice;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "location_tb")
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String locationName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    public Location(Integer id, String locationName, Notice notice) {
        this.id = id;
        this.locationName = locationName;
        this.notice = notice;
    }

}
