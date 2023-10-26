package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Blog;
import savit.group2.sockstore.model.entity.Sock_Blog;
import savit.group2.sockstore.service.BlogService;
import savit.group2.sockstore.service.SockBlogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/blog-sock")
public class BlogSockRestController {
    @Autowired
    SockBlogService service;

    @Autowired
    BlogService serviceBlog;

//    @PostMapping("{id}")
//    public List<Sock_Blog> create(@PathVariable("id") UUID id, @RequestBody String[] list) {
//        Blog blog = serviceBlog.g
//        if (list != null) {
//            for (String idProduct: list) {
//            }
//        }
//        return service.create(list);
//    }
}
