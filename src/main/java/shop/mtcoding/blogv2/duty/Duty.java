package shop.mtcoding.blogv2.duty;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blogv2.wishduty.WishDuty;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "duty_tb")
@Entity
public class Duty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String dutyName;

    @Builder
    public Duty(Integer id, String dutyName) {
        this.id = id;
        this.dutyName = dutyName;
    }

}
