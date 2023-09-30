package savit.group2.sockstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Pattern;
import savit.group2.sockstore.model.reponse.PatternResponse;

import java.util.UUID;
@Repository
public interface PatternRepository extends JpaRepository<Pattern, UUID> {
  @Query(value="SELECT new savit.group2.sockstore.model.reponse.PatternResponse(pattern.id ,pattern.code ,pattern.name ,pattern.status) FROM Pattern pattern")
  Page<PatternResponse> getResposePage(Pageable pageable);
}
