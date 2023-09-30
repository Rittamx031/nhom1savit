package savit.group2.sockstore.controller;

import java.util.List;

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
import savit.group2.sockstore.model.reponse.AccountResponse;
import savit.group2.sockstore.model.reponse.PatternResponse;
import savit.group2.sockstore.model.request.PatternRequest;
import savit.group2.sockstore.service.PatternService;

@Controller
@RequestMapping("admin/pattern")
public class PatternController {
  @Autowired
  private PatternService service;
  // @Autowired
  // private CustomerService customerService;
  @Autowired
  private PatternRequest patternRequest;
  private PatternResponse patternResponse;
  public int rowcount = 10;
  public int[] pagenumbers;
  public String sortBy = "name";
  public boolean sortDir = true;
  public int pageno = 0;
  public int totalpage = 0;

  // panigation and sort
  @GetMapping("/getcountrow")
  public String handleSubmit(Model model, @RequestParam("selectedValue") String selectedValue) {
    System.out.println(selectedValue);
    rowcount = Integer.parseInt(selectedValue);
    pagenumbers = service.getPanigation(rowcount, pageno);
    this.pageno = 1;
    List<PatternResponse> list = service.getPageNo(1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/pattern/table-pattern.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    List<PatternResponse> list = service.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
    totalpage = service.getPageNumber(rowcount);
    pagenumbers = service.getPanigation(rowcount, pageno);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/pattern/table-pattern.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    List<PatternResponse> list = service.getPageNo(this.pageno - 1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    pagenumbers = service.getPanigation(rowcount, this.pageno);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    return "/admin/pages/pattern/table-pattern.html";
  }

  @GetMapping("index")
  public String getAccountIndexpages(Model model) {
    this.pageno = 1;
    List<PatternResponse> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/pattern/table-pattern.html";
  }
  // crud
  @ModelAttribute("patternRequest")
  public PatternRequest setSignUpForm() {
    return patternRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    patternRequest = new PatternRequest();
    patternRequest.setStatus(true);
    // model.addAttribute("listCustomer", customerService.getComboBox());
    model.addAttribute("patternRequest", patternRequest);
    return "/admin/pages/pattern/form-pattern.html";
  }

  @GetMapping("delete")
  public String deletePattern(Model model, @RequestParam("id") String id) {
    // service.deletePattern(UUID.fromString(id));
    return "redirect:index";
  }

  @GetMapping("edit")
  public String editPattern(Model model, @RequestParam("id") String id) {
    // model.addAttribute("patternRequest",
    // service.getPatternRequetById(UUID.fromString(id)));
    // model.addAttribute("listCustomer", customerService.getAllCustomers());
    return "/admin/pages/pattern/form-pattern.html";
  }

  @PostMapping("store")
  public String storePattern(Model model, @Valid @ModelAttribute("patternRequest") PatternRequest patternRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      // model.addAttribute("listCustomer", customerService.getComboBox());
      return "/admin/pages/pattern/form-pattern.html";
    } else {
      // service.savePattern(patternRequest);
      return "redirect:index";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("patternRequest") PatternRequest patternRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/pattern/form-pattern.html";
    }
    // service.updatePattern(patternRequest);
    return "redirect:index";
  }
}
