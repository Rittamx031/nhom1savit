package savit.group2.sockstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import savit.group2.sockstore.model.entity.Employee;
import savit.group2.sockstore.model.entity.Role;
import savit.group2.sockstore.model.request.EmployeeSignupRequest;
import savit.group2.sockstore.repository.EmployeeRepository;
import savit.group2.sockstore.repository.RoleRepository;
import savit.group2.sockstore.security.service.SercurityService;
import savit.group2.sockstore.util.CheckEmailHelper;

@Service
public class EmployeeService {
  @Autowired
  EmployeeRepository repository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  CheckEmailHelper emailHelper;
  @Autowired
  SercurityService sercurityService;

  public Employee signup(EmployeeSignupRequest request) {
    if (emailHelper.isEmailNotExsits(request.getEmail())) {
      Employee employee = new Employee();
      employee.setAddress(request.getAddress());
      employee.setBirthday(request.getBirthDay());
      employee.setEmail(request.getEmail());
      employee.setName(request.getName());
      employee.setWardcode("");
      employee.setDistrictcode(0);
      employee.setName(request.getName());
      employee.setPassword(encoder.encode(request.getPassword()));
      employee.setPhone(request.getPhone());
      employee.setStatus(false);
      Optional<Role> roleOp = roleRepository.getEmployeeRoles();
      if (roleOp.isPresent()) {
        employee.setRole(roleOp.get());
      } else {
        Role role = new Role();
        role.setName("EMPLOYEE");
        role.setRoles("ADMIN");
        role.setPermissions("NEW EMPLOYEE");
        role.setStatus(true);
        employee.setRole(roleRepository.save(role));
      }
      employee = repository.save(employee);
      return employee;
    } else {
      // throws Email already exists
      return null;
    }
  }

}
