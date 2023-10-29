package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Color;
import savit.group2.sockstore.service.ColorService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/colors")
public class ColorRestController {
    @Autowired
    private ColorService service;

    @GetMapping
    public List<Color> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Color create(@RequestBody Color color) {
        return service.create(color);
    }

    @PutMapping("{id}")
    public Color update(@PathVariable("id") UUID id, @RequestBody Color color) {
        return service.update(color, id);
    }

    @DeleteMapping("{id}")
    public Color delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
