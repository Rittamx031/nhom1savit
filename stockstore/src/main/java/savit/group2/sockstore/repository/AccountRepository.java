package savit.group2.sockstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.reponse.AccountResponse;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
  @Query(value = "SELECT savit.group2.sockstore.model.reponse.AccountResponse(acc.id,acc.customer.name ,acc.customer.phone, acc.email, acc.status) FROM Account acc")
  Page<AccountResponse> getPageAccountRepose(Pageable page);
}
