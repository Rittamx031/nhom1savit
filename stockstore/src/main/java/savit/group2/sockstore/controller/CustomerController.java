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
import savit.group2.sockstore.model.entity.Customer;
import savit.group2.sockstore.service.CustomerService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/pages/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("get-all")
    public String getALL(Model model, @RequestParam(name = "pageNo", defaultValue = "0") String pageNo) {
        List<Customer> customerList = customerService.getALl(Integer.valueOf(pageNo));
        model.addAttribute("customerList", customerList);
        return "admin/pages/customer/hien-thi.html";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        customerService.delete(UUID.fromString(id));
        return "redirect:get-all";
    }

    @GetMapping("view-add")
    public String viewAdd(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/pages/customer/add.html";
    }

    @GetMapping("view-update")
    public String viewUpdate(Model model, @RequestParam("id") String id) {
        Customer customer = customerService.getOne(UUID.fromString(id));
        model.addAttribute("customer", customer);
        return "admin/pages/customer/update.html";
    }

    @PostMapping("add")
    public String add(Model model, @ModelAttribute("customer") Customer customer, @RequestParam(value = "pageNo", defaultValue = "0") String pageNo) {
        customerService.add(customer);
//        model.addAttribute("listCategory", materialService.getAll(Integer.valueOf(pageNo)));
        return "redirect:get-all";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("customer") Customer customer, @RequestParam("id") String id) {
        customerService.update(customer, UUID.fromString(id));
        return "redirect:get-all";
    }
}
