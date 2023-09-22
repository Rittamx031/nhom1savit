package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.repository.MaterialRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return materialRepository.getAll(pageable);
    }

    public Material getOne(UUID id) {
        return materialRepository.findById(id).get();
    }

    public Material add(Material material) {
        Material materialSave = Material.builder()
                .code(material.getCode())
                .name(material.getName())
                .created_at(new Date(0))
                .updated_at(new Date(0))
                .created_by("hung")
                .updated_by("hung")
                .build();
        return materialRepository.save(materialSave);
    }

    public Material update(Material material, UUID id) {
        Optional<Material> optional = materialRepository.findById(id);
        if ((optional.isPresent())) {
            return optional.map(materialUpdate -> {
                        materialUpdate.setCode(material.getCode());
                        materialUpdate.setName(material.getName());
                        materialUpdate.setCreated_at(new Date(0));
                        materialUpdate.setUpdated_at(new Date(0));
                        materialUpdate.setCreated_by("hung");
                        materialUpdate.setUpdated_by("hung");
                        return materialRepository.save(materialUpdate);
                    }
            ).get();
        }
        return null;
    }

    public Boolean delete(UUID id) {
        Optional<Material> optional = materialRepository.findById(id);
        if (optional.isPresent()) {
            materialRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}