package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Customer;
import savit.group2.sockstore.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController("restCustomerController")
@RequestMapping("/rest/customers")
public class CustomerController {
  @Autowired
  private CustomerService service;

  @GetMapping
  public List<Customer> getAll() {
    return service.getAll();
  }

  @PostMapping
  public Customer create(@RequestBody Customer cate) {
    return service.create(cate);
  }

  @PutMapping("{id}")
  public Customer update(@PathVariable("id") UUID id, @RequestBody Customer cate) {
    return service.update(cate, id);
  }

  @DeleteMapping("{id}")
  public HttpStatusCode delete(@PathVariable("id") UUID id) {
    return service.delete(id);
  }
}
