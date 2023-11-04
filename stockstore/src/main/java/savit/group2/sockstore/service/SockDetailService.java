package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Sock_Detail;
import savit.group2.sockstore.repository.SockDetailRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SockDetailService {
    @Autowired
    private SockDetailRepository repository;

    public List<Sock_Detail> getAll() {
        return repository.getAllByStatus(true);
    }

    public Sock_Detail create(Sock_Detail role) {
        role.setStatus(true);
        return repository.save(role);
    }

    public Sock_Detail update(Sock_Detail sock_detail, UUID id) {
        Optional<Sock_Detail> optional = repository.findById(id);
        return optional.map(o -> {
            o.setQuantity(sock_detail.getQuantity());
            o.setUnit_base_price(sock_detail.getUnit_base_price());
            o.setDescription(sock_detail.getDescription());
            o.setSock(sock_detail.getSock());
            o.setPath(sock_detail.getPath());
            o.setColor(sock_detail.getColor());
            return repository.save(sock_detail);
        }).orElse(null);
    }

    public Sock_Detail delete(UUID id) {
        Optional<Sock_Detail> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
