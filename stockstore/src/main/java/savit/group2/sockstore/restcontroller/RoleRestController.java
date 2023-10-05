package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savit.group2.sockstore.model.entity.Role;
import savit.group2.sockstore.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    private RoleService service;
    @GetMapping
    public List<Role> getAll() {
        return service.getAll();
    }

}
