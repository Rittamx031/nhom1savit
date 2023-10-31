package savit.group2.sockstore.controller.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
  @GetMapping("index")
  public String getHomePage(Model model) {

    return "/user/index.html";
  }

  @GetMapping("shop")
  public String getSockPage(Model model) {
    return "/user/shop.html";
  }

  @GetMapping("cart")
  public String getCartPage(Model model) {
    return "/user/cart.html";
  }

  @GetMapping("info")
  public String getUserInfoPage(Model model) {
    return "/user/user-info.html";
  }

  @GetMapping("news")
  public String getNewsPage(Model model) {
    return "/user/cart.html";
  }

  @GetMapping("blog")
  public String getBlogPage(Model model) {
    return "/user/blog.html";
  }

   @GetMapping("sock-detail")
  public String getSockDetailPage(Model model) {
    return "/user/sock-detail.html";
  }

  
}
