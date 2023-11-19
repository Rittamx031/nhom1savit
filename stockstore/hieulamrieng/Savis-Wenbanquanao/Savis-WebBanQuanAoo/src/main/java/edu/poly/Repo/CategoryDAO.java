package edu.poly.Repo;

import edu.poly.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryDAO extends JpaRepository<Category, String> {
   Category findById(long id);
   Page<Category> findByNameLike(String keywords, Pageable pageable);
   Category deleteById(long id);

}