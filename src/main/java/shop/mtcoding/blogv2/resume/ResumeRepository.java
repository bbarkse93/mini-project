package shop.mtcoding.blogv2.resume;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    @Query("SELECT COUNT(r) FROM Resume r WHERE r.user.id = :userId")
    Long findByUserIdCount(@Param("userId") Integer userId);

    @Query("SELECT r FROM Resume r WHERE r.user.id = :userId")
    List<Resume> findByUserId(@Param("userId") Integer userId);
}
