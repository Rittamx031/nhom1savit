package savit.group2.sockstore.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import savit.group2.sockstore.model.request.LoginRequest;

@Controller
public class SecurityController {
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

}
