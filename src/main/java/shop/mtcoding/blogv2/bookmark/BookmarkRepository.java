package shop.mtcoding.blogv2.bookmark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    List<Bookmark> findAllByUserId(Integer userId);

}