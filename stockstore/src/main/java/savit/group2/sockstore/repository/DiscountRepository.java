package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Discount;

import java.util.List;
import java.util.UUID;
@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    List<Discount> getAllByStatus(boolean status);
}
