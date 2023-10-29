package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Sock_Blog;
import savit.group2.sockstore.repository.SockBlogRepository;

import java.util.List;

@Service
public class SockBlogService {
    @Autowired
    private SockBlogRepository repository;

    public List<Sock_Blog> getAll() {
        return repository.getAllByStatus(true);
    }

    public List<Sock_Blog> create(List<Sock_Blog> list) {
        return repository.saveAll(list);
    }
}
