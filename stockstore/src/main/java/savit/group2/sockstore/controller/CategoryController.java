package savit.group2.sockstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import savit.group2.sockstore.model.entity.Category;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.service.CategoryService;
import savit.group2.sockstore.service.MaterialService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/pages/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("get-all")
    public String getALl(Model model, @RequestParam(value = "pageNo", defaultValue = "0") String pageNo) {
        List<Category> listCategory = categoryService.getAll(Integer.valueOf(pageNo));
        model.addAttribute("listCategory", listCategory);
        return "admin/pages/category/hien-thi.html";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        categoryService.delete(UUID.fromString(id));
        return "redirect:get-all";
    }

    @GetMapping("view-add")
    public String viewAdd(Model model) {
        model.addAttribute("category", new Category());
        return "admin/pages/category/add.html";
    }

    @GetMapping("view-update")
    public String viewUpdate(Model model, @RequestParam("id") String id) {
        Category category=categoryService.getOne(UUID.fromString(id));
        model.addAttribute("cagory",category);
        return "admin/pages/category/update.html";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("category") Category category, @RequestParam(value = "pageNo", defaultValue = "0") String pageNo) {
        categoryService.add(category);
//        model.addAttribute("listCategory", materialService.getAll(Integer.valueOf(pageNo)));
        return "redirect:get-all";
    }

    @PostMapping("update")
    public String update( @ModelAttribute("material") Category category,@RequestParam("id") String id ) {
        categoryService.update(category,UUID.fromString(id));
        return "redirect:get-all";
    }
}
