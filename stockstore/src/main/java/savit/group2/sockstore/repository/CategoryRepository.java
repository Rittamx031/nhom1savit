package savit.group2.sockstore.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Category;
import savit.group2.sockstore.model.entity.Material;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("select ca from Category ca order by ca.created_at desc")
    List<Category> getAll(Pageable pageable);
}
