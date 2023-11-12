package savit.group2.sockstore.security.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import savit.group2.sockstore.model.entity.Account;

/**
 * UserInforController
 */
public interface AccountInfoRepository extends JpaRepository<Account, UUID> {
  @Query("SELECT user from Account user WHERE user.email = :username")
  Optional<Account> getuser(@Param("username") String username);

  @Query(value = "SELECT acc FROM Account acc WHERE acc.email LIKE :email")
  List<Account> hasEmailis(@Param("email") String email);
}
