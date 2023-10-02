package savit.group2.sockstore.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Material;

import java.util.List;
import java.util.UUID;
@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {

    @Query("select mt from Material mt order by mt.created_at desc")
    List<Material> getAll(Pageable pageable);
}