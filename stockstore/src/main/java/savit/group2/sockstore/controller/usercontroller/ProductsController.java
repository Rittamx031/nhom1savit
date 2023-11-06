package savit.group2.sockstore.controller.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import savit.group2.sockstore.service.SockService;

import java.util.UUID;

@Controller
public class ProductsController {
    @Autowired
    SockService sockService;

    @GetMapping("/products/{id}")
    public String getProductDetail(Model model, @PathVariable(name = "id") UUID id) {
        model.addAttribute("product", sockService.getSockByID(id));
        return "/user/sock-detail.html";
    }
}
