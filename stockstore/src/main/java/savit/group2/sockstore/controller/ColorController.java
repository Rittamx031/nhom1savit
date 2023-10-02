package savit.group2.sockstore.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import savit.group2.sockstore.model.request.ColorRequest;
import savit.group2.sockstore.service.ColorService;

import java.util.UUID;

@Controller
@RequestMapping("product/color")
public class ColorController {
    @Autowired
    private ColorService service;

    @GetMapping
    public String pageColor(@RequestParam(defaultValue = "1") int page,
                            @ModelAttribute("color_create") ColorRequest createColor,
                            @ModelAttribute("color_update") ColorRequest updateColor,
                            Model model) {
        model.addAttribute("page", service.listAll(page));
        return "admin/pages-color";
    }

    @RequestMapping("/load/{id}")
    public String load(@PathVariable(name = "id") UUID id, Model model, @RequestParam(defaultValue = "1") int page,
                       @ModelAttribute("color_update") ColorRequest colorUpdate,
                       @ModelAttribute("color_create") ColorRequest colorCreate) {
        model.addAttribute("id_color", id);
        model.addAttribute("color_update", service.getColorByID(id));
        model.addAttribute("page", service.listAll(page));
        return "admin/pages-color";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable(name = "id") UUID id, Model model, @RequestParam(defaultValue = "1") int page,
                         @Valid @ModelAttribute("color_update") ColorRequest colorUpdate, BindingResult bindingResult,
                         @ModelAttribute("color_create") ColorRequest colorCreate, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorUpdate", true);
            return pageColor(page, colorCreate, colorUpdate, model);
        } else {
            service.update(colorUpdate, id);
            redirectAttributes.addFlashAttribute("success", "Update color successfully");
            return "redirect:/product/color";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", service.delete(id));
        return "redirect:/product/color";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("color_create") ColorRequest colorCreate,
                      BindingResult bindingResult, RedirectAttributes redirectAttributes,
                      @ModelAttribute("color_update") ColorRequest colorUpdate,  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", true);
            return pageColor(1, colorCreate, colorUpdate, model);
        } else {
            service.add(colorCreate);
            redirectAttributes.addFlashAttribute("success", "Add color successfully");
            return "redirect:/product/color";
        }
    }
}
