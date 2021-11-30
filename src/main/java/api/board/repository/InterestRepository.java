package api.board.repository;

import api.board.domain.Interested;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interested, Long> {

}
