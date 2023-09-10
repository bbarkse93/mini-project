package shop.mtcoding.blogv2.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {

    @Query("SELECT a FROM Apply a WHERE a.resume.id = :resumeId")
    List<Apply> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("SELECT COUNT(a) FROM Apply a")
    Long findAllCoutnt();

}
