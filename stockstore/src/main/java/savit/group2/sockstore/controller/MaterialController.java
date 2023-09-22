package savit.group2.sockstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.service.MaterialService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/pages/material/")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("get-all")
    public String getALl(Model model, @RequestParam(value = "pageNo", defaultValue = "0") String pageNo) {
        List<Material> list = materialService.getAll(Integer.valueOf(pageNo));
        model.addAttribute("listMaterial", list);
        return "admin/pages/material/hien-thi.html";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        materialService.delete(UUID.fromString(id));
        return "redirect:get-all";
    }

    @GetMapping("view-add")
    public String viewAdd(Model model) {
        model.addAttribute("material", new Material());
        return "admin/pages/material/add.html";
    }

    @GetMapping("view-update")
    public String viewUpdate(Model model, @RequestParam("id") String id) {
        Material material=materialService.getOne(UUID.fromString(id));
        model.addAttribute("material",material);
        return "admin/pages/material/update.html";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("material") Material material, @RequestParam(value = "pageNo", defaultValue = "0") String pageNo) {
        materialService.add(material);
        model.addAttribute("listMaterial", materialService.getAll(Integer.valueOf(pageNo)));
        return "redirect:get-all";
    }

    @PostMapping("update")
    public String update( @ModelAttribute("material") Material material,@RequestParam("id") String id ) {
        materialService.update(material,UUID.fromString(id));
        return "redirect:get-all";
    }
}
