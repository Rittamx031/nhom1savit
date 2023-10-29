package savit.group2.sockstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import savit.group2.sockstore.model.request.EmployeeSignupRequest;
import savit.group2.sockstore.model.request.UserSingupRequest;
import savit.group2.sockstore.service.AccountService;
import savit.group2.sockstore.service.EmployeeService;
import savit.group2.sockstore.util.CheckEmailHelper;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
  @Autowired
  EmployeeService employeeService;
  @Autowired
  AccountService accountService;
  @Autowired
  CheckEmailHelper emailHelper;

  @GetMapping("/login")
  public String loginPage(Model model, @RequestParam(value = "error", required = false) Boolean error,
      RedirectAttributes redirAttrs) {
    if (error == null) {
      // LoginRequest login = new LoginRequest();
      // model.addAttribute("login", login);
    } else {
      redirAttrs.addFlashAttribute("message", "Đăng Nhập không thành công");
      model.addAttribute("loginError", true);
    }
    return "login.html";
  }

  @GetMapping("/homepage")
  public String loggedInPage() {
    return "redirect:/admin";
  }

  @GetMapping("/signOut")
  public String signOutPage() {
    return "signOut";
  }

  @GetMapping("user/signup")
  public String signUpUser(Model model) {
    model.addAttribute("signUpRequest", new UserSingupRequest());
    return "user/user-sigup.html";
  }

  @PostMapping(value = "user/signup")
  public String signupUser(Model model, @Valid @ModelAttribute("signUpRequest") UserSingupRequest signUpRequest,
      BindingResult theBindingResult, RedirectAttributes redirAttrs) {
    if (theBindingResult.hasErrors()) {
      return "user/user-sigup.html";
    }
    if (signUpRequest.validateHasError()) {
      model.addAttribute("message", signUpRequest.ValidateError());
      return "user/user-sigup.html";
    }
    // TODO: process POST request
    if (emailHelper.isEmailNotExsits(signUpRequest.getEmail())) {
      if (accountService.singup(signUpRequest) == null) {
        model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
        return "user/user-sigup.html";
      }
    } else {
      model.addAttribute("message", "Email already exists");
      return "user/user-sigup.html";

    }
    return "redirect:/login";
  }

  @GetMapping("employee/signup")
  public String signUpEmployee(Model model) {
    model.addAttribute("signUpRequest", new EmployeeSignupRequest());
    return "admin/pages-singup.html";
  }

  @PostMapping(value = "employee/signup")
  public String signUpEmployee(Model model, @Valid @ModelAttribute("signUpRequest") EmployeeSignupRequest signUpRequest,
      BindingResult theBindingResult, RedirectAttributes redirAttrs) {
    if (theBindingResult.hasErrors()) {
      return "admin/pages-singup.html";
    }
    if (signUpRequest.validateHasError()) {
      model.addAttribute("message", signUpRequest.ValidateError());
      return "admin/pages-singup.html";
    }
    if (emailHelper.isEmailNotExsits(signUpRequest.getEmail())) {
      // TODO: process POST request
      if (employeeService.signup(signUpRequest) == null) {
        model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
        return "admin/pages-singup.html";
      }
    } else {
      model.addAttribute("message", "Email already exists");
      return "admin/pages-singup.html";
    }
    return "redirect:/login";
  }

}
