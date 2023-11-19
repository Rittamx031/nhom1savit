package edu.poly.AdminController;

import edu.poly.Service.SessionService;
import edu.poly.Repo.CategoryDAO;
import edu.poly.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AdminCategoryController {
    @Autowired
    CategoryDAO catedao;
    @Autowired
    SessionService session;
    @RequestMapping("/Admin/Category")
    public String Product(Model model,@RequestParam("p") Optional<Integer> p){
        Category item = new Category();
        model.addAttribute("item",item);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = catedao.findAll(pageable);
        model.addAttribute("page", page)    ;

        return "layoutChangeAdmin/categoryadmin";
    }
    @RequestMapping("/Admin/Category/Add")
    public String add(Model model , @Validated @ModelAttribute("item") Category item,BindingResult errors, @RequestParam("p") Optional<Integer> p ){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = catedao.findAll(pageable);
        model.addAttribute("page", page)    ;
        if(errors.hasErrors()){
           model.addAttribute("message","something was wrong");

        }else {
            catedao.save(item);
            model.addAttribute("message","Success");
        }
        return "layoutChangeAdmin/categoryadmin";
    }
    @RequestMapping("/Admin/Category/Update")
    public String update(Category item){
        catedao.save(item);
        return "redirect:/Category/Edit/"+ item.getId();
    }
    @RequestMapping("/Admin/Category/Remove/{id}")
    public String Remove(Model model, @PathVariable("id") Long id, RedirectAttributes prams){
        try {
            catedao.deleteById(id);
            prams.addAttribute("message", "Delete Success");
        } catch (Exception e) {
            prams.addAttribute("message", "Can't detele Category beacause the product form category are present ");
        }
        return "redirect:/Admin/Category";
    }
    @RequestMapping("/Admin/category/search-and-page")
    public String searchAndPage(Category item,Model model, @RequestParam("keywords") Optional<String> kw, @RequestParam("p") Optional<Integer> p) {
        model.addAttribute("item",item);
        String kwords = kw.orElse(session.get("keywords"));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = catedao.findByNameLike("%"+kwords+"%", pageable);
        model.addAttribute("page", page);
        return "layoutChangeAdmin/categoryadmin";
    }
    @RequestMapping("/Admin/Category/Edit/{id}")
    public String Edit(Model model, @PathVariable("id") Long id ,@RequestParam("p") Optional<Integer> p){
        Category item = catedao.findById(id);
        model.addAttribute("item", item);

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Category> page = catedao.findAll(pageable);
        model.addAttribute("page", page)    ;
        return "layoutChangeAdmin/categoryadmin";
    }
}
