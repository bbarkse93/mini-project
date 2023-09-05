package shop.mtcoding.blogv2.bookmark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.mtcoding.blogv2.user.User;



public interface BookmarkRepository extends JpaRepository <Bookmark,Integer>{
    
    List<Bookmark> findAllByUserId(Integer userId);

}

