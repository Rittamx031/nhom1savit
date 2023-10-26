package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.repository.MaterialRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository repository;

    public List<Material> getAll() {
        return repository.getAllByStatus(true);
    }

    public Material create(Material material) {
        material.setStatus(true);
        return repository.save(material);
    }

    public Material update(Material material, UUID id) {
        Optional<Material> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(material.getCode());
            o.setName(material.getName());
            return repository.save(material);
        }).orElse(null);
    }

    public Material delete(UUID id) {
        Optional<Material> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}
