package edu.poly.controller;

import edu.poly.Repo.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
    @Autowired
    OrderDAO dao;
    @RequestMapping("/Save/Order")
    public String Save(){

        return "";
    }
}
