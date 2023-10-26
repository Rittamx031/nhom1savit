package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Category;

import java.util.List;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> getAllByStatus(boolean status);
}
