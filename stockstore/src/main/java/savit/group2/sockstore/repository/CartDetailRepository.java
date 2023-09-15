package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Cart_Detail;

import java.util.UUID;
@Repository
public interface CartDetailRepository extends JpaRepository<Cart_Detail, UUID> {
}
