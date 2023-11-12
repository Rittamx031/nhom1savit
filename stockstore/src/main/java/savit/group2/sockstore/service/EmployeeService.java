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

  public Employee signup(EmployeeSignupRequest request) {
    if (emailHelper.isEmailNotExsits(request.getEmail())) {
      Employee employee = new Employee();
      employee.setAddress(request.getAddress());
      employee.setBirthday(request.getBirthDay());
      employee.setEmail(request.getEmail());
      employee.setName(request.getName());
      employee.setPassword(encoder.encode(request.getPassword()));
      employee.setPhone(request.getPhone());
      Optional<Role> roleOp = roleRepository.getEmployeeRoles();
      if (roleOp.isPresent()) {
        employee.setRole(roleOp.get());
        return repository.save(employee);
      } else {

      }
      return null;
    } else {
      // throws Email already exists
      return null;
    }
  }

}
