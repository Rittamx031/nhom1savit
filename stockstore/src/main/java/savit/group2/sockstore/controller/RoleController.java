package savit.group2.sockstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/role")
public class RoleController {
    @GetMapping
    public String view() {
        return "admin/pages-role";
    }
}
