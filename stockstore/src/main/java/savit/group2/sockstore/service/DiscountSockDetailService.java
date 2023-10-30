package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Discount;
import savit.group2.sockstore.model.entity.Discount_SockDetail;
import savit.group2.sockstore.model.entity.Sock_Detail;
import savit.group2.sockstore.repository.DiscountSockDetailRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DiscountSockDetailService {
    @Autowired
    private DiscountSockDetailRepository repository;

    public List<Discount_SockDetail> getAll() {
        return repository.findAll();
    }

    public List<Discount_SockDetail> getAllByDiscount(UUID id, boolean status) {
        return repository.getAllByDiscount_IdAndStatus(id, status);
    }

    public List<Discount_SockDetail> getAllByIdDiscount(UUID id) {
        return repository.getAllByDiscount_Id(id);
    }

    public Discount_SockDetail create(Discount_SockDetail entity) {
        entity.setUnit_base_price(entity.getSock_detail().getUnit_base_price());
        if (entity.getDiscount().getDiscount_type().equals("VND")) {
            entity.setMoney_return(entity.getUnit_base_price() - entity.getDiscount().getCash_discount());
        } else {
            entity.setMoney_return((entity.getUnit_base_price() * (100 - entity.getDiscount().getPercent_discount()) / 100));
        }
        entity.setStatus(true);
        return repository.save(entity);
    }

    public Discount_SockDetail update(Discount discount, Sock_Detail sock_detail, boolean newStatus) {
        Discount_SockDetail discount_sockDetail = repository.getByDiscountAndSock(discount, sock_detail);
        discount_sockDetail.setStatus(newStatus);
        return repository.save(discount_sockDetail);
    }
}
