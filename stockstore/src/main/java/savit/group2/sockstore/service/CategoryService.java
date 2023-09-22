package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Category;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.repository.CategoryRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    long millis = System.currentTimeMillis();

    public List<Category> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return categoryRepository.getAll(pageable);
    }

    public Category getOne(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public Category add(Category category) {
        Category categorySave = Category.builder()
                .code(category.getCode())
                .name(category.getName())
                .created_at(new Date(millis))
                .created_by("hung")
                .status(true)
                .build();
        return categoryRepository.save(categorySave);
    }

    public Category update(Category category, UUID id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if ((optional.isPresent())) {
            return optional.map(categoryUpdate -> {
                        categoryUpdate.setCode(category.getCode());
                        categoryUpdate.setName(category.getName());
                        categoryUpdate.setUpdated_at(new Date(millis));
                        categoryUpdate.setUpdated_by("hung");
                        categoryUpdate.setStatus(category.getStatus());
                        return categoryRepository.save(categoryUpdate);
                    }
            ).get();
        }
        return null;
    }

    public Boolean delete(UUID id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
