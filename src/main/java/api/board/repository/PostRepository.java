package api.board.repository;

import api.board.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts,Long> {
    

}
