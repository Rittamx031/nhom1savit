package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Blog;
import savit.group2.sockstore.model.entity.Sock;
import savit.group2.sockstore.model.entity.Sock_Blog;
import savit.group2.sockstore.repository.SockBlogRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SockBlogService {
    @Autowired
    private SockBlogRepository repository;

    public List<Sock_Blog> getAll() {
        return repository.findAll();
    }

    public List<Sock_Blog> getAllByBlogIdAndStatus(UUID uuid, boolean status) {
        return repository.getAllByBlog_IdAndStatus(uuid, status);
    }

    public List<Sock_Blog> getAllByBlogId(UUID id) {
        return repository.getAllByBlog_Id(id);
    }

    public Sock_Blog create (Sock_Blog entity) {
        entity.setStatus(true);
        return repository.save(entity);
    }

    public Sock_Blog update(Blog blog, Sock sock, boolean status) {
        Sock_Blog sockBlog = repository.getByBlogAndSock(blog, sock);
        sockBlog.setStatus(status);
        return repository.save(sockBlog);
    }
}
