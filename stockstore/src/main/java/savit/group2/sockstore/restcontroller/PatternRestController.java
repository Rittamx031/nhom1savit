package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Pattern;
import savit.group2.sockstore.service.PatternService;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/patterns")
public class PatternRestController {
    @Autowired
    private PatternService service;

    @GetMapping
    public List<Pattern> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Pattern create(@RequestBody Pattern pattern) {
        return service.create(pattern);
    }

    @PutMapping("{id}")
    public Pattern update(@PathVariable("id") UUID id, @RequestBody Pattern pattern) {
        return service.update(pattern, id);
    }

    @DeleteMapping("{id}")
    public Pattern delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
