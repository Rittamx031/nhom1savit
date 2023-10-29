package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Pattern;
import savit.group2.sockstore.repository.PatternRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatternService {
    @Autowired
    private PatternRepository repository;

    public List<Pattern> getAll() {
        return repository.getAllByStatus(true);
    }

    public Pattern create(Pattern pattern) {
        pattern.setStatus(true);
        return repository.save(pattern);
    }

    public Pattern update(Pattern pattern, UUID id) {
        Optional<Pattern> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(pattern.getCode());
            o.setName(pattern.getName());
            return repository.save(pattern);
        }).orElse(null);
    }

    public Pattern delete(UUID id) {
        Optional<Pattern> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(true);
            return repository.save(o);
        }).orElse(null);
    }
}
