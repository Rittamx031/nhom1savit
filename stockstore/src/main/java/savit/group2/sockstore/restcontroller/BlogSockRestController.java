package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Sock_Blog;
import savit.group2.sockstore.service.SockBlogService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/rest/blog-sock")
public class BlogSockRestController {
    @Autowired
    SockBlogService service;

    @GetMapping()
    public List<Sock_Blog> getlAll() {
        return service.getAll();
    }

    @RequestMapping("/blog/{id}")
    public List<Sock_Blog> getAllByBlogId(@PathVariable("id") UUID id) {
        return service.getAllByBlogId(id);
    }

    @RequestMapping("/blog-status/{id}")
    public List<Sock_Blog> getAllByBlogIdAndStatus(@PathVariable("id") UUID id) {
        return service.getAllByBlogIdAndStatus(id, true);
    }

    @PostMapping
    public Sock_Blog create(@RequestBody Sock_Blog sockBlog) {
        return service.create(sockBlog);
    }

    @PutMapping
    public Sock_Blog update(@RequestBody Sock_Blog sockBlog) {
        return service.update(sockBlog.getBlog(), sockBlog.getSock(), sockBlog.getStatus());
    }
}
