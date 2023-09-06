package shop.mtcoding.blogv2.wishduty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishDutyRepository extends JpaRepository<WishDuty, Integer> {
    List<WishDuty> findByNoticeId(Integer noticeId);

    void deleteByNoticeId(Integer id);
}
