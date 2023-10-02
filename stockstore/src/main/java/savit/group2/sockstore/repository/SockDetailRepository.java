package savit.group2.sockstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Sock_Detail;
import savit.group2.sockstore.model.reponse.SockDetailResponse;

import java.util.UUID;

@Repository
public interface SockDetailRepository extends JpaRepository<Sock_Detail, UUID> {
  @Query(value = "SELECT new savit.group2.sockstore.model.reponse.SockDetailResponse(detail.id ,detail.quantity ,detail.unit_base_price ,detail.producer.name,detail.category.name,detail.sock.name,detail.pattern.name,detail.material.name,detail.size.name,detail.color.name,detail.image_sock_detail.path ,detail.status) FROM Sock_Detail detail")
  Page<SockDetailResponse> getResposePage(Pageable pageable);
}
// UUID id;
// Integer quantity;
// Double unit_base_price;
// String producer;
// String category;
// String sock;
// String pattern;
// String meterial;
// String size;
// String color;
// String image_sock_detail;
// Boolean status;