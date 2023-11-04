package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Sock;
import savit.group2.sockstore.repository.SockRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SockService {
    @Autowired
    private SockRepository repository;

    public List<Sock> getAll() {
        return repository.getAllByStatus(true);
    }

    public Sock getSockByID(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<Sock> getAllByOutstanding() {
        return repository.getAllByOutstanding(true);
    }

    public Sock create(Sock sock) {
        sock.setStatus(true);
        return repository.save(sock);
    }

    public Sock update(Sock sock, UUID id) {
        Optional<Sock> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(sock.getCode());
            o.setName(sock.getName());
            o.setSize(sock.getSize());
            o.setCategory(sock.getCategory());
            o.setProducer(sock.getProducer());
            o.setMaterial(sock.getMaterial());
            o.setOutstanding(sock.getOutstanding());
            return repository.save(sock);
        }).orElse(null);
    }

    public Sock delete(UUID id) {
        Optional<Sock> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
