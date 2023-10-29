package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Size;
import savit.group2.sockstore.repository.SizeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SizeService {
    @Autowired
    private SizeRepository repository;

    public List<Size> getAll() {
        return repository.getAllByStatus(true);
    }

    public Size create(Size size) {
        size.setStatus(true);
        return repository.save(size);
    }

    public Size update(Size size, UUID id) {
        Optional<Size> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(size.getCode());
            o.setName(size.getName());
            return repository.save(size);
        }).orElse(null);
    }

    public Size delete(UUID id) {
        Optional<Size> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
