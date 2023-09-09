package shop.mtcoding.blogv2.event;

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

@NoArgsConstructor
@Getter
@Setter
@Table(name = "event_tb")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String eventPicUrl;

    @Column
    private String eventAddress;

    @Builder
    public Event(Integer id, String eventPicUrl, String eventAddress) {
        this.id = id;
        this.eventPicUrl = eventPicUrl;
        this.eventAddress = eventAddress;
    }
}
