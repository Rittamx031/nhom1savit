package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Image_Sock_Detail;

import java.util.UUID;
@Repository
public interface ImageSockDetailRepository extends JpaRepository<Image_Sock_Detail, UUID> {
}
