package shop.mtcoding.blogv2.scrap;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.blogv2.notice.Notice;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {

      
    @Query("SELECT s FROM Scrap s WHERE s.user.id = :userId")
    List<Scrap> findAll(@Param("userId") Integer userId);

}
