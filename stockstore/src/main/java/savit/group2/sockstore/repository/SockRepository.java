package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Sock;

import java.util.List;
import java.util.UUID;
@Repository
public interface SockRepository extends JpaRepository<Sock, UUID> {

    List<Sock> getAllByStatus(boolean status);

    List<Sock> getAllByOutstanding(boolean status);

}
