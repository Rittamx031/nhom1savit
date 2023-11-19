package edu.poly.controller;

import edu.poly.Repo.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
     @Autowired
    CategoryDAO dao;

     @RequestMapping("/Category/Home")
    public String index(Model model){

        return "Index/home";
    }


}
