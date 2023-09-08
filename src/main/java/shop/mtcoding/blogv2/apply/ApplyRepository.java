package shop.mtcoding.blogv2.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply ,Integer>{

    List<Apply> findByUserId(Integer userId);

    




    
}
