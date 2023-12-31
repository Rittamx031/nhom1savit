package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Producer;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProducerRepository extends JpaRepository<Producer, UUID> {
    List<Producer> getAllByStatus(boolean status);
}
