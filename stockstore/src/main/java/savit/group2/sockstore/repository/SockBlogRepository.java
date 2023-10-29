package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Sock_Blog;

import java.util.List;
import java.util.UUID;
@Repository
public interface SockBlogRepository extends JpaRepository<Sock_Blog, UUID> {
    List<Sock_Blog> getAllByStatus(boolean status);
}
