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
import savit.group2.sockstore.model.reponse.SockDetailResponse;
import savit.group2.sockstore.model.request.SockDetailRequest;
import savit.group2.sockstore.service.SockDetailService;

@Controller
@RequestMapping("/admin/sockdetail")
public class SockDetailController {
  @Autowired
  private SockDetailService service;
  // @Autowired
  // private CustomerService customerService;
  @Autowired
  private SockDetailRequest sockDetailRequest;
  private SockDetailResponse sockDetailResponse;
  public int rowcount = 10;
  public int[] pagenumbers;
  public String sortBy = "unit_base_price";
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
    List<SockDetailResponse> list = service.getPageNo(1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/sock/table-sock-detail.html"; // Redirect back to the form page
  }

  @GetMapping("sort")
  public String getPageSort(Model model, @RequestParam("sortBy") String sortby,
      @RequestParam("sortDir") boolean sordir) {
    this.sortBy = sortby;
    this.sortDir = sordir;
    this.pageno = 1;
    List<SockDetailResponse> list = service.getPageNo(this.pageno, rowcount, this.sortBy, this.sortDir);
    totalpage = service.getPageNumber(rowcount);
    pagenumbers = service.getPanigation(rowcount, pageno);
    model.addAttribute("list", list);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/sock/table-sock-detail.html";
  }

  @GetMapping("index")
  public String getAccountIndexpages(Model model) {
    this.pageno = 1;
    List<SockDetailResponse> list = service.getPageNo(this.pageno, rowcount, sortBy, sortDir);
    pagenumbers = service.getPanigation(rowcount, pageno);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    model.addAttribute("list", list);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", pageno);
    return "/admin/pages/sock/table-sock-detail.html";
  }

  @GetMapping("/page")
  public String getPageNo(Model model, @RequestParam("pageno") int pageno) {
    if (pageno <= 1) {
      this.pageno = 1;
      pageno = 1;
    }
    this.pageno = pageno;
    List<SockDetailResponse> list = service.getPageNo(this.pageno - 1, rowcount, sortBy, sortDir);
    totalpage = service.getPageNumber(rowcount);
    model.addAttribute("totalpage", totalpage);
    pagenumbers = service.getPanigation(rowcount, this.pageno);
    model.addAttribute("pagenumber", pagenumbers);
    model.addAttribute("crpage", this.pageno);
    model.addAttribute("list", list);
    return "/admin/pages/sock/table-sock-detail.html";
  }

  @ModelAttribute("sockDetailRequest")
  public SockDetailRequest setSignUpForm() {
    return sockDetailRequest;
  }

  @GetMapping("create")
  public String goToCreateForm(Model model) {
    sockDetailRequest = new SockDetailRequest();
    sockDetailRequest.setStatus(true);
    model.addAttribute("sockDetailRequest", sockDetailRequest);
    return "/admin/pages/voucher/form-voucher.html";
  }

  @GetMapping("delete")
  public String deleteVoucher(Model model, @RequestParam("id") String id) {
    // service.deleteVoucher(UUID.fromString(id));
    return "redirect:table-voucher";
  }

  @GetMapping("edit")
  public String editVoucher(Model model, @RequestParam("id") String id) {
    // model.addAttribute("sockDetailRequest",
    // service.getVoucherRequetById(UUID.fromString(id)));
    return "/admin/pages/voucher/form-voucher.html";
  }

  @PostMapping("store")
  public String storeVoucher(Model model,
      @Valid @ModelAttribute("sockDetailRequest") SockDetailRequest sockDetailRequest,
      BindingResult theBindingResult) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/voucher/form-voucher.html";
    } else {
      // service.saveVoucher(sockDetailRequest);
      return "redirect:table-voucher";
    }
  }

  @PostMapping("update")
  public String update(@Valid @ModelAttribute("sockDetailRequest") SockDetailRequest sockDetailRequest,
      BindingResult theBindingResult, Model model) {
    if (theBindingResult.hasErrors()) {
      return "/admin/pages/voucher/form-voucher.html";
    }
    // service.updateVoucher(sockDetailRequest);
    return "redirect:table-voucher";
  }
}
