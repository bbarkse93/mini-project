package shop.mtcoding.blogv2.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplyRepository extends JpaRepository<Apply ,Integer>{

    List<Apply> findByUserId(Integer userId);

    




    
    @Query("SELECT COUNT(a) FROM Apply a")
    Long findAllCoutnt();

}
