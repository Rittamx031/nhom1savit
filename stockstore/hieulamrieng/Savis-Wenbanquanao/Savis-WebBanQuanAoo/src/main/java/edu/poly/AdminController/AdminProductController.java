package edu.poly.AdminController;

import edu.poly.Service.ParamService;
import edu.poly.Service.SessionService;
import edu.poly.Repo.ProductDAO;
import edu.poly.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
public class AdminProductController {
    @Autowired
    ProductDAO dao;
    @Autowired
    SessionService session;
    @Autowired
    ParamService paramService;
    @RequestMapping("/Admin/Table")
    public String Table(Model model ,@RequestParam("p") Optional<Integer> p){
        Product item = new Product();
        model.addAttribute("item",item);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page)    ;
        return "layoutChangeAdmin/tables";
    }
    @RequestMapping("/Admin/Tables/Edit/{id}")
    public String ProductEdit(Model model, @PathVariable("id") Long id,@RequestParam("p") Optional<Integer> p){
        Product item = dao.findById(id);
        model.addAttribute("item", item);
        Pageable pageable = PageRequest.of(p.orElse(0), 3);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page)    ;
        return "layoutChangeAdmin/tables";
    }
    @RequestMapping("/Admin/Tables/Remove/{id}")
    public String Remove(Model model, @PathVariable("id") Long id, RedirectAttributes prams){
        try {
            dao.deleteById(id);
            prams.addAttribute("message", "Delete Success");
        } catch (Exception e) {
            prams.addAttribute("message", "Can't detele product beacause the product are odered ");
        }
        return "redirect:/Admin/Table";
    }
//    @RequestMapping("/Admin/Table/Update")
//    public String ProductUpdate(Product item){
//        dao.save(item);
//        return "redirect:/Admin/Tables/Edit"+ item.getId();
//    }

    @RequestMapping("/product/search-and-page")
    public String searchAndPage(Model model, @RequestParam("keywords") Optional<String> kw, @RequestParam("p") Optional<Integer> p) {
        Product item = new Product();
        model.addAttribute("item",item);
        String kwords = kw.orElse(session.get("keywords"));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findByNameLike("%"+kwords+"%", pageable);
        model.addAttribute("page", page);
        return "layoutChangeAdmin/tables";
    }
     @RequestMapping("/Admin/Table/Save")
    public String SaveSomething( Model model,@Validated @ModelAttribute("item") Product item, BindingResult errors , @RequestParam("photo") MultipartFile multipartFile, @RequestParam("p") Optional<Integer> p) throws IOException {
         Pageable pageable = PageRequest.of(p.orElse(0), 3);
         Page<Product> page = dao.findAll(pageable);
         model.addAttribute("page", page)   ;
         if(errors.hasErrors()){
             model.addAttribute("message","Some field are not valid . Please fix them");
         }else {
             String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
             String uploadDir = "static/upload";
             item.setImage(fileName);
             dao.save(item);
             model.addAttribute("message", "Edit Success");
             paramService.save(multipartFile, uploadDir);
             model.addAttribute("item", new Product());
         }
         return "layoutChangeAdmin/tables";
    }




 }
