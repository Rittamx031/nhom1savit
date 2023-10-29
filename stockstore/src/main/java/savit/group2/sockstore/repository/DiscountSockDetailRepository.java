package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import savit.group2.sockstore.model.entity.Discount;
import savit.group2.sockstore.model.entity.Discount_SockDetail;
import savit.group2.sockstore.model.entity.Sock_Detail;

import java.util.List;
import java.util.UUID;

public interface DiscountSockDetailRepository extends JpaRepository<Discount_SockDetail, UUID> {

    List<Discount_SockDetail> getAllByDiscount_IdAndStatus(UUID id, boolean status);

    List<Discount_SockDetail> getAllByDiscount_Id(UUID id);

    @Query("SELECT ds FROM Discount_SockDetail ds WHERE ds.discount = :discount AND ds.sock_detail = :sock_detail")
    Discount_SockDetail getByDiscountAndSock(@Param("discount") Discount discount, @Param("sock_detail") Sock_Detail sock_detail);


}
