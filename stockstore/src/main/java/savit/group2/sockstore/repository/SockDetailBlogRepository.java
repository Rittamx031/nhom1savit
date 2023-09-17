package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.SockDetail_Blog;

import java.util.UUID;
@Repository
public interface SockDetailBlogRepository extends JpaRepository<SockDetail_Blog, UUID> {
}
