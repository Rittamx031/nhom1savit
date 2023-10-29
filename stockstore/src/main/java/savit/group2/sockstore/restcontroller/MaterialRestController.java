package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.service.MaterialService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/materials")
public class MaterialRestController {
    @Autowired
    private MaterialService service;

    @GetMapping
    public List<Material> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Material create(@RequestBody Material material) {
        return service.create(material);
    }

    @PutMapping("{id}")
    public Material update(@PathVariable("id") UUID id, @RequestBody Material material) {
        return service.update(material, id);
    }

    @DeleteMapping("{id}")
    public Material delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
