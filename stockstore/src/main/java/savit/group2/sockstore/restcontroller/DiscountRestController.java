package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Discount;
import savit.group2.sockstore.service.DiscountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/discounts")
public class DiscountRestController {
    @Autowired
    private DiscountService service;

    @GetMapping
    public List<Discount> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Discount create(@RequestBody Discount discount) {
        return service.create(discount);
    }

    @PutMapping("{id}")
    public Discount update(@PathVariable("id") UUID id, @RequestBody Discount discount) {
        return service.update(discount, id);
    }

}
