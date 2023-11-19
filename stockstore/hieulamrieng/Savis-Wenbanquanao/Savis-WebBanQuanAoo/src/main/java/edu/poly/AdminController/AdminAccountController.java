package edu.poly.AdminController;


import edu.poly.Service.MailerService;
import edu.poly.Service.SessionService;
import edu.poly.Service.ShoppingCartService;
import edu.poly.Repo.AccountDAO;
import edu.poly.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AdminAccountController {
    @Autowired
    AccountDAO dao;
    @Autowired
    SessionService session;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    MailerService mailer;
    @GetMapping("/account/login")
    public String login() {
        return "layout/loginform";
    }

    @PostMapping("/account/login")
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password , HttpServletRequest req) {

           Account user = dao.findAccountByUsername(username);
            if (!user.getPassword().equals(password)) {
                model.addAttribute("message", "Mật khẩu không hợp lệ ");
            } else {
                String uri = session.get("security-uri");
                if (uri != null) {
                    return "redirect:" + uri;
                } else {
                    if(user.isActivated()){
                    if(user.isAdmin()){
                        model.addAttribute("message", "Đăng nhập thành công");
                        session.set("user", user);
                        return "redirect:/Admin/Views";
                    }else {
                        model.addAttribute("message", "Đăng nhập thành công ");
                        session.set("user", user);
                        return "redirect:/Home/views";
                    }
                }else{
                        model.addAttribute("message", "Đăng nhập thất bại ");
                    }
                }
            }

        return "layout/loginform";
    }
    @RequestMapping("/account/logout")
    public String logout(){
      session.remove("user");
      shoppingCartService.clear();
        return "redirect:/Home/views";
    }
    @RequestMapping("/account/register")
    public String register(@ModelAttribute("item") Account item){

        return"layout/signup_form";
    }
    @RequestMapping ("/account/signup")
    public String signup(@Validated @ModelAttribute("item") Account item , BindingResult errors,Model model){
        if(errors.hasErrors()){
            model.addAttribute("message", "Some field are not valid . Please fix them");
        }else {
            item.setAddress("ABC");
            item.setFullname("ABC");
            item.setPhone(123);
            item.setPhoto("ABC");
            item.setActivated(false);
            item.setAdmin(false);


            dao.save(item);
            Account account=dao.findAccountByUsername(item.getUsername());
            mailer.queue(account.getEmail(),"Vui lòng đợi chúng tôi xác thực tài khoản ","TK:"+account.getUsername()+","+"Mật khẩu là:"+ account.getPassword()+"br"+"Vui lòng chờ trong giây lát để chúng tôi xác nhận tài khoản " );

            model.addAttribute("message", "SignUp Success ");
        }
        return "layout/signup_form";
    }
    @RequestMapping("/account/about/{id}")
    public String about(Model model  , @PathVariable("id") long id){
        Account item = dao.findById(id);
        model.addAttribute("item", item);
        List<Account> items = dao.findAll();
        model.addAttribute("items", items);
        return "layoutChangeAdmin/aboutadmin";
    }
    @RequestMapping("/account/about/save")
    public String aboutsave(Model model ,@Validated @ModelAttribute("item")  Account item,BindingResult errors ){
        if(errors.hasErrors()){
            model.addAttribute("message","something was wrong");
        }else {
            dao.save(item);
            model.addAttribute("message","Update Success");
        }
        return "layoutChangeAdmin/aboutadmin";
    }
    @RequestMapping("/Admin/Account/Remove/{id}")
    public String Remove(Model model, @PathVariable("id") Long id, RedirectAttributes prams){
        try {
            dao.deleteById(id);
            prams.addAttribute("message", "Delete Success");
        } catch (Exception e) {
            prams.addAttribute("message", "Can't Account Category beacause the order form account are present ");
        }
        return "redirect:/Admin/Account";
    }
   
}

