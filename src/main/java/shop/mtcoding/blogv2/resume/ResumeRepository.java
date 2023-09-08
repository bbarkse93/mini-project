package shop.mtcoding.blogv2.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    @Query("SELECT COUNT(a) FROM Resume a")
    Long findTotalCount();
}
