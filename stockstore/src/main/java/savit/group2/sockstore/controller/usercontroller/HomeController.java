package savit.group2.sockstore.controller.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import savit.group2.sockstore.service.SockService;

@Controller
@RequestMapping("/")
public class HomeController {
  @Autowired
  SockService sockService;
  @GetMapping("index")
  public String getHomePage(Model model) {
    model.addAttribute("products", sockService.getAllByOutstanding());
    return "/user/index.html";
  }


  
}
