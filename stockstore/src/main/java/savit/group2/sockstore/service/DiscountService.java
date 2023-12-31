package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Discount;
import savit.group2.sockstore.repository.DiscountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository repository;


    public List<Discount> getAll() {
        return repository.findAll();
    }

    public List<Discount> getAllByStatus() {
        return repository.getAllByStatus(true);
    }

    public Discount create(Discount discount) {
        discount.setStatus(true);
        return repository.save(discount);
    }

    public Discount update(Discount discount, UUID id) {
        Optional<Discount> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCoupon_code(discount.getCoupon_code());
            o.setName(discount.getName());
            o.setDescription(discount.getDescription());
            o.setValid_from(discount.getValid_from());
            o.setValid_until(discount.getValid_until());
            if (discount.getDiscount_type().equals("%")) {
                o.setPercent_discount(discount.getPercent_discount());
            } else {
                o.setCash_discount(discount.getCash_discount());
            }
            o.setDiscount_type(discount.getDiscount_type());
            o.setStatus(discount.getStatus());
            return repository.save(discount);
        }).orElse(null);
    }

}
