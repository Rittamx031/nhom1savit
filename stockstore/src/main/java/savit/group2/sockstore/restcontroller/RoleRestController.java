package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Role;
import savit.group2.sockstore.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    private RoleService service;

    @GetMapping
    public List<Role> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return service.create(role);
    }

    @PutMapping("{id}")
    public Role update(@PathVariable("id") UUID id, @RequestBody Role role) {
        return service.update(role, id);
    }

    @DeleteMapping("{id}")
    public Role delete(@PathVariable("id") UUID id) {
        return service.delete(id);
    }
}
