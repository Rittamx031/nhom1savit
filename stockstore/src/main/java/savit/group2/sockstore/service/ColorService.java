package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Color;
import savit.group2.sockstore.repository.ColorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ColorService {
    @Autowired
    private ColorRepository repository;

    public List<Color> getAll() {
        return repository.getAllByStatus(true);
    }

    public Color create(Color color) {
        color.setStatus(true);
        return repository.save(color);
    }

    public Color update(Color color, UUID id) {
        Optional<Color> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(color.getCode());
            o.setName(color.getName());
            return repository.save(color);
        }).orElse(null);
    }

    public Color delete(UUID id) {
        Optional<Color> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
