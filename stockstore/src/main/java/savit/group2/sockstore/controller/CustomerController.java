package savit.group2.sockstore.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import savit.group2.sockstore.model.request.CustomerRequest;
import savit.group2.sockstore.service.CustomerService;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
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
    model.addAttribute("customerRequest", new CustomerRequest());
    return "admin/pages/customer/form-customer.html";
  }

  @GetMapping("edit/{id}")
  public String editCustomer(Model model, @PathVariable("id") UUID id) {
    model.addAttribute("customerRequest",
        service.getCustomerRequetById(id));
    return "admin/pages/customer/update-customer.html";
  }

  @PostMapping("store")
  public String storeCustomer(Model model, RedirectAttributes thRedirectAttributes,
      @Valid @ModelAttribute("customerRequest") CustomerRequest customerRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      return "admin/pages/customer/form-customer.html";
    } else {
      if (service.saveCustomer(customerRequest) == null) {
        thRedirectAttributes.addFlashAttribute("message", "Lỗi không thể xử lý tác vụ");
        return "admin/pages/customer/form-customer.html";
      }
      return "redirect:/admin/customer";
    }
  }

  @PostMapping("update")
  public String update(Model model, RedirectAttributes thRedirectAttributes,
      @Valid @ModelAttribute("customerRequest") CustomerRequest customerRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/customer/update-customer";
    }
    if (service.updateCustomer(customerRequest) == null) {
      thRedirectAttributes.addFlashAttribute("message", "Lỗi không thể xử lý tác vụ");
      return "/admin/pages/customer/update-customer";
    }
    return "redirect:/admin/customer";
  }
}
