package savit.group2.sockstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savit.group2.sockstore.model.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    List<Role> getAllByStatus(boolean status);

    @Query(value = "SELECT role FROM Role role WHERE role.name LIKE 'EMPLOYEE'")
    public Optional<Role> getEmployeeRoles();
}
