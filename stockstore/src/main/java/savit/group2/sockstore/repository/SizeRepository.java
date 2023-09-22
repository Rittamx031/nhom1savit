package savit.group2.sockstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Size;
import savit.group2.sockstore.model.reponse.SizeResponse;

import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, UUID> {
  @Query(value = "SELECT new savit.group2.sockstore.model.reponse.SizeResponse(pattern.id ,pattern.code ,pattern.name ,pattern.status) FROM Size pattern")
  Page<SizeResponse> getResposePage(Pageable pageable);
}
