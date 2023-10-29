package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Size;
import savit.group2.sockstore.service.SizeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/sizes")
public class SizeRestController {
    @Autowired
    private SizeService service;

    @GetMapping
    public List<Size> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Size create(@RequestBody Size size) {
        return service.create(size);
    }

    @PutMapping("{id}")
    public Size update(@PathVariable("id") UUID id, @RequestBody Size size) {
        return service.update(size, id);
    }

    @DeleteMapping("{id}")
    public Size delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
