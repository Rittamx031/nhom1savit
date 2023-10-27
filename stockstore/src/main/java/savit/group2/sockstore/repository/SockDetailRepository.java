package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Sock_Detail;

import java.util.List;
import java.util.UUID;

@Repository
public interface SockDetailRepository extends JpaRepository<Sock_Detail, UUID> {
    List<Sock_Detail> getAllByStatus(boolean status);
}
