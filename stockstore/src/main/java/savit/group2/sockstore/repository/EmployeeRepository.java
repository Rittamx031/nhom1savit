package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
  @Query(value = "SELECT emp FROM Employee emp WHERE emp.email LIKE :email")
  List<Employee> hasEmailis(@Param("email") String email);
}
