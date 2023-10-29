package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Category;
import savit.group2.sockstore.service.CategoryService;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    private CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Category create(@RequestBody Category cate) {
        return service.create(cate);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable("id") UUID id, @RequestBody Category cate) {
        return service.update(cate, id);
    }

    @DeleteMapping("{id}")
    public Category delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
