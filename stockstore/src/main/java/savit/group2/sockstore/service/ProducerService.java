package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Producer;
import savit.group2.sockstore.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProducerService {
    @Autowired
    private ProducerRepository repository;

    public List<Producer> getAll() {
        return repository.getAllByStatus(true);
    }

    public Producer create(Producer producer) {
        producer.setStatus(true);
        return repository.save(producer);
    }

    public Producer update(Producer producer, UUID id) {
        Optional<Producer> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(producer.getCode());
            o.setName(producer.getName());
            return repository.save(producer);
        }).orElse(null);
    }

    public Producer delete(UUID id) {
        Optional<Producer> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
