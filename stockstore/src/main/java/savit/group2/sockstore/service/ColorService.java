package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Color;
import savit.group2.sockstore.model.request.ColorRequest;
import savit.group2.sockstore.repository.ColorRepository;


import java.util.UUID;

@Service
public class ColorService {

    @Autowired
    ColorRepository repository;

    public Page<Color> listAll(int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        return repository.findAll(pageable);
    }

    public Color add(ColorRequest request) {
        Color color = new Color();
        color.setCode(request.getCode());
        color.setName(request.getName());
        color.setStatus(true);
        return repository.save(color);
    }

    public Color getColorByID(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Color update(ColorRequest request, UUID id) {
        Color color = getColorByID(id);
        if (color != null) {
            color.setCode(request.getCode());
            color.setName(request.getName());
            color.setStatus(request.getStatus());
            return repository.save(color);
        }
        return null;
    }

    public String delete(UUID id) {
        Color color = getColorByID(id);
        if (color != null) {
            repository.delete(color);
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }
}
