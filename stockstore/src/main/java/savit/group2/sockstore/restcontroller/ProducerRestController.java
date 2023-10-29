package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Producer;
import savit.group2.sockstore.service.ProducerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/producers")
public class ProducerRestController {
    @Autowired
    private ProducerService service;

    @GetMapping
    public List<Producer> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Producer create(@RequestBody Producer producer) {
        return service.create(producer);
    }

    @PutMapping("{id}")
    public Producer update(@PathVariable("id") UUID id, @RequestBody Producer producer) {
        return service.update(producer, id);
    }

    @DeleteMapping("{id}")
    public Producer delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
