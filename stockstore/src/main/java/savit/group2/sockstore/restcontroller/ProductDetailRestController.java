package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Sock_Detail;
import savit.group2.sockstore.service.SockDetailService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/product-detail")
public class ProductDetailRestController {
    @Autowired
    private SockDetailService service;

    @GetMapping
    public List<Sock_Detail> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Sock_Detail create(@RequestBody Sock_Detail sock) {
        return service.create(sock);
    }

    @PutMapping("{id}")
    public Sock_Detail update(@PathVariable("id") UUID id, @RequestBody Sock_Detail sock) {
        return service.update(sock, id);
    }

    @DeleteMapping("{id}")
    public Sock_Detail delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
