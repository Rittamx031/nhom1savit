package edu.poly.Repo;

import edu.poly.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface ProductDAO extends JpaRepository<Product, Integer> {
       List<Product> findAllByCategoryId(long id);
       Product findById(long id);
       Product deleteById(long id);
       Product findAllById(long id);
//       @Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
       Page<Product> findByNameLike(String keywords, Pageable pageable);
       Page<Product> findByCategoryId(long keywords, Pageable pageable);
       Page<Product> findByPriceBetween(double a ,double b, Pageable pageable);

//       Page<Product> allProductsSortedByName = productRepository.findAll(Sort.by("name"));
//@Query("SELECT new Report(o.category, sum(o.price), count(o)) "
//        + " FROM Product o "
//        + " GROUP BY o.category"
//        + " ORDER BY sum(o.price) DESC")
//List<Report> getInventoryByCategory();
//       @Query(value = "  Select TOP(10) * from products where discount >0" , nativeQuery = true)
//       List<Product> selectTop10();


}

