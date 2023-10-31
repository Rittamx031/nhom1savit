package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Blog;
import savit.group2.sockstore.model.entity.Sock;
import savit.group2.sockstore.model.entity.Sock_Blog;

import java.util.List;
import java.util.UUID;
@Repository
public interface SockBlogRepository extends JpaRepository<Sock_Blog, UUID> {

    List<Sock_Blog> getAllByBlog_IdAndStatus(UUID id, boolean status);

    List<Sock_Blog> getAllByBlog_Id (UUID id);

    @Query("SELECT sbl FROM Sock_Blog sbl WHERE sbl.blog = :blog AND sbl.sock = :sock")
    Sock_Blog getByBlogAndSock(@Param("blog") Blog blog, @Param("sock") Sock sock);
}
