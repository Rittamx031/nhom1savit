package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Category;
import savit.group2.sockstore.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll() {
        return repository.getAllByStatus(true);
    }

    public Category create(Category cate) {
        cate.setStatus(true);
        return repository.save(cate);
    }

    public Category update(Category cate, UUID id) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> {
            o.setCode(cate.getCode());
            o.setName(cate.getName());
            return repository.save(cate);
        }).orElse(null);
    }

    public Category delete(UUID id) {
        Optional<Category> optional = repository.findById(id);
        return optional.map(o -> {
            o.setStatus(false);
            return repository.save(o);
        }).orElse(null);
    }
}

