package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Sock;
import savit.group2.sockstore.service.SockService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    private SockService service;

    @GetMapping
    public List<Sock> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Sock create(@RequestBody Sock sock) {
        return service.create(sock);
    }

    @PutMapping("{id}")
    public Sock update(@PathVariable("id") UUID id, @RequestBody Sock sock) {
        return service.update(sock, id);
    }

    @DeleteMapping("{id}")
    public Sock delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
