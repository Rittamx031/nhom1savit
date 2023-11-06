package savit.group2.sockstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import savit.group2.sockstore.model.request.CustomerRequest;
import savit.group2.sockstore.service.CustomerService;

@Controller
@RequestMapping("test/customer")
public class TestCustomerController {
  @Autowired
  private CustomerService service;
  @Autowired
  private CustomerRequest customerRequest;

  @ModelAttribute("customerRequest")
  public CustomerRequest setSignUpForm() {
    return customerRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    customerRequest = new CustomerRequest();
    customerRequest.setStatus(true);
    // model.addAttribute("listCustomer", customerService.getComboBox());
    model.addAttribute("customerRequest", customerRequest);
    return "admin/pages/customer/form-customer.html";
  }

  @GetMapping("edit")
  public String editCustomer(Model model, @RequestParam("id") String id) {
    // model.addAttribute("customerRequest",
    // service.getCustomerRequetById(UUID.fromString(id)));
    // model.addAttribute("listCustomer", customerService.getAllCustomers());
    return "admin/pages/customer/update-customer.html";
  }

  @PostMapping("store")
  public String storeCustomer(Model model, @Valid @ModelAttribute("customerRequest") CustomerRequest customerRequest,
      BindingResult theBindingResult) {
    System.out.println(customerRequest.toString());
    if (theBindingResult.hasErrors()) {
      // model.addAttribute("listCustomer", customerService.getComboBox());
      return "admin/pages/customer/form-customer.html";
    } else {
      // service.saveCustomer(customerRequest);
      return "admin/pages/customer/form-customer.html";
      // return "redirect:/admin/customer";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("customerRequest") CustomerRequest customerRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/customer/update-customer";
    }
    // service.updateCustomer(customerRequest);
    return "redirect:/admin/customer";
  }
}
