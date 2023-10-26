package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Blog;
import savit.group2.sockstore.service.BlogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/blogs")
public class BlogRestController {
    @Autowired
    private BlogService service;

    @GetMapping
    public List<Blog> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Blog create(@RequestBody Blog blog) {
        return service.create(blog);
    }

    @PutMapping("{id}")
    public Blog update(@PathVariable("id") UUID id, @RequestBody Blog blog) {
        return service.update(blog, id);
    }

    @DeleteMapping("{id}")
    public Blog delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
