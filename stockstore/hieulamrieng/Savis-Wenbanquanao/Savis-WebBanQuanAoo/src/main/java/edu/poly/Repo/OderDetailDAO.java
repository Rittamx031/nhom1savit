package edu.poly.Repo;

import edu.poly.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface OderDetailDAO extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findAllByOrder_Id(long id);
}
