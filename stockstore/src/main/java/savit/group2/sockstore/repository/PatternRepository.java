package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Pattern;

import java.util.List;
import java.util.UUID;
@Repository
public interface PatternRepository extends JpaRepository<Pattern, UUID> {
    List<Pattern> getAllByStatus(boolean status);
}
