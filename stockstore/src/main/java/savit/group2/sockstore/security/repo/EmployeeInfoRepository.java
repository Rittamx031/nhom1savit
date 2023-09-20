package savit.group2.sockstore.security.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import savit.group2.sockstore.model.entity.Employee;

public interface EmployeeInfoRepository extends JpaRepository<Employee, UUID> {
  @Query("SELECT emp from Employee emp WHERE emp.email = :username")
  Optional<Employee> getuser(@Param("username") String username);
}
