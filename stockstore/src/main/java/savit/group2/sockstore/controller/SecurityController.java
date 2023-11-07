package savit.group2.sockstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import savit.group2.sockstore.model.entity.VertifyEmail;
import savit.group2.sockstore.model.request.EmployeeSignupRequest;
import savit.group2.sockstore.model.request.ResetPasswordRequest;
import savit.group2.sockstore.model.request.UserSingupRequest;
import savit.group2.sockstore.security.service.SercurityService;
import savit.group2.sockstore.service.AccountService;
import savit.group2.sockstore.service.EmailService;
import savit.group2.sockstore.service.EmployeeService;
import savit.group2.sockstore.service.VertifyEmailService;
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
  @Autowired
  SercurityService securityService;
  @Autowired
  VertifyEmailService vertifyEmailsService;
  @Autowired
  EmailService emailService;

  @GetMapping("/login")
  public String loginPage(Model model, @RequestParam(value = "error", required = false) Boolean error) {
    if (error != null) {
      model.addAttribute("message", "Đang có lỗi xẩy ra vui lòng thử lại sau");
    }
    return "login.html";
  }

  @GetMapping("/homepage")
  public String loggedInPage() {
    return "redirect:/admin";
  }

  // @GetMapping("/signOut")
  // public String signOutPage() {
  // return "signOut";
  // }

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

  @Secured("NOT_ACCTIVE")
  @GetMapping("vertifyemail")
  public String vertifyPage(Model model, RedirectAttributes thRedirectAttributes) {
    model.addAttribute("vertifyemail", new VertifyEmail());
    return "active-email.html";
  }

  @Secured("NOT_ACCTIVE")
  @GetMapping("sendvertifyemail")
  public String sendVertifyAcc(Model model, RedirectAttributes thRedirectAttributes) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      emailService.activeEmailMessage(
          vertifyEmailsService.createVertifyEmail(username));
      thRedirectAttributes.addFlashAttribute("message", "Vui lòng kiểm tra email!!!");
      model.addAttribute("vertifyemail", new VertifyEmail());
      return "redirect:/vertifyemail";
    }
    return "redirect:/signOut";
  }

  @Secured("NOT_ACCTIVE")
  @PostMapping("vertifyemail")
  public String vertifyAccount(Model model, RedirectAttributes thRedirectAttributes,
      @ModelAttribute("vertifyemail") VertifyEmail vertifyemail) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      vertifyemail.setEmail(username);
      if (vertifyEmailsService.vertifyEmail(vertifyemail)) {
        securityService.ActiveAccount();
        thRedirectAttributes.addFlashAttribute("message", "Xác thực thành công vui lòng đăng nhập lại!!!");
        return "redirect:/signOut";
      } else {
        thRedirectAttributes.addFlashAttribute("message",
            "Xác thực không thành công mã kích hoạt không đúng hoặc đã hết hạn vui lòng thử lại !!!");
      }
    }
    return "redirect:/vertifyemail";
  }

  @GetMapping("forgotpassword")
  public String findAccount(Model model, RedirectAttributes thRedirectAttributes) {
    model.addAttribute("vertifyemail", new VertifyEmail());
    return "find-account.html";
  }

  @PostMapping("sendresetpasswordcode")
  public String sendResetPasswordCode(Model model, RedirectAttributes thRedirectAttributes,
      @RequestParam("email") String email) {
    if (emailHelper.isEmailNotExsits(email)) {
      model.addAttribute("message", "Email Chưa đăng ký tài khoản nào!");
      return "find-account.html";
    } else {
      emailService.resetEmailMessage(vertifyEmailsService.createVertifyEmail(email));
      securityService.setAuthentichByEmail(email);
      thRedirectAttributes.addFlashAttribute("message", "Vui lòng kiểm tra email!!!");
      model.addAttribute("vertifyemail", new VertifyEmail());
      return "reset-password-code.html";
    }
  }

  @PostMapping("/resetpasswordcode")
  public String resetPasswordCode(
      Model model,
      RedirectAttributes thRedirectAttributes,
      @ModelAttribute("vertifyemail") VertifyEmail vertifyemail) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String username = userDetails.getUsername();
      vertifyemail.setEmail(username);
      if (vertifyEmailsService.vertifyEmail(vertifyemail)) {
        thRedirectAttributes.addFlashAttribute("message", "Xác thực thành công nhập mật khẩu mới!!!");
        return "redirect:/resetpassword";
      }
    }
    thRedirectAttributes.addFlashAttribute("message",
        "Xác thực không thành công mã kích hoạt không đúng hoặc đã hết hạn vui lòng thử lại !!!");
    return "redirect:/resetpasswordcode";
  }

  @GetMapping("resetpassword")
  public String resetPasswordPage(Model model, RedirectAttributes thRedirectAttributes) {
    model.addAttribute("requestPasswordRequest", new ResetPasswordRequest());
    return "reset-password.html";
  }

}
