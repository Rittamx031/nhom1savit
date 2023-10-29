package savit.group2.sockstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DashboardController {

    @GetMapping(value = "admin/pages/dashboard")
    public ModelMap mmDashboard(Model model) {
        model.addAttribute("message", "hello this is meessage form controller ");
        return new ModelMap();
    }

    @GetMapping(value = "admin/employee/role")
    public String viewRole() {
        return "admin/pages-role";
    }

    @GetMapping(value = "admin/product/category")
    public String viewCategory() {
        return "admin/pages-category";
    }
    
    @GetMapping(value = "admin/product/producer")
    public String viewProducer() {
        return "admin/pages-producer";
    }

    @GetMapping(value = "admin/product")
    public String viewProduct() {
        return "admin/pages-product";
    }

    @GetMapping(value = "admin/product/color")
    public String viewColor() {
        return "admin/pages-color";
    }

    @GetMapping(value = "admin/product/pattern")
    public String viewPattern() {
        return "admin/pages-pattern";
    }

    @GetMapping(value = "admin/product/material")
    public String viewMaterial() {
        return "admin/pages-material";
    }

    @GetMapping(value = "admin/product/size")
    public String viewSize() {
        return "admin/pages-size";
    }

    @GetMapping(value = "admin/voucher")
    public String viewDiscount() {
        return "admin/pages-discount";
    }

    @GetMapping(value = "admin/blog")
    public String viewBlog() {
        return "admin/pages-blog";
    }
}
