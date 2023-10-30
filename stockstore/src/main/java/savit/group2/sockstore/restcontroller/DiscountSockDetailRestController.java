package savit.group2.sockstore.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import savit.group2.sockstore.model.entity.Discount_SockDetail;
import savit.group2.sockstore.service.DiscountSockDetailService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/discount-sock")
public class DiscountSockDetailRestController {
    @Autowired
    DiscountSockDetailService service;

    @RequestMapping("/getAll")
    public List<Discount_SockDetail> getAll() {
        return service.getAll();
    }

    @RequestMapping("/discount/{id}")
    public List<Discount_SockDetail> getAllByIdDiscount(@PathVariable("id") UUID id) {
        return service.getAllByIdDiscount(id);
    }

    @GetMapping("{id}")
    public List<Discount_SockDetail> getAllByDiscount(@PathVariable("id")UUID id) {
        return service.getAllByDiscount(id, true);
    }

    @PostMapping
    public Discount_SockDetail create(@RequestBody Discount_SockDetail discount_sockDetail) {
        return service.create(discount_sockDetail);
    }

    @PutMapping
    public Discount_SockDetail update(@RequestBody Discount_SockDetail discount_sockDetail) {
        return service.update(discount_sockDetail.getDiscount(), discount_sockDetail.getSock_detail(), discount_sockDetail.isStatus());
    }

}
