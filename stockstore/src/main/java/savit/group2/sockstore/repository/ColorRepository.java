package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Color;

import java.util.List;
import java.util.UUID;
@Repository
public interface ColorRepository extends JpaRepository<Color, UUID> {
    List<Color> getAllByStatus(boolean status);
}
