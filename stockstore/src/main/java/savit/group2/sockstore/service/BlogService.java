package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Blog;
import savit.group2.sockstore.repository.BlogRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    private BlogRepository repository;

    public List<Blog> getAll() {
        return repository.findAll();
    }

    public Blog create(Blog blog) {
        blog.setStatus(true);
        blog.setDate_create(new java.sql.Date(System.currentTimeMillis()));
        return repository.save(blog);
    }

    public Blog update(Blog blog, UUID id) {
        Optional<Blog> optional = repository.findById(id);
        return optional.map(o -> {
            o.setTitle(blog.getTitle());
            o.setPath(blog.getPath());
            o.setContent(blog.getContent());
            o.setStatus(blog.getStatus());
            return repository.save(blog);
        }).orElse(null);
    }

}
