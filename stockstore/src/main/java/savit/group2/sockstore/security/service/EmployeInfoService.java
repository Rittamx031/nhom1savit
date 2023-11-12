package savit.group2.sockstore.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import savit.group2.sockstore.model.entity.Employee;
import savit.group2.sockstore.model.security.UserInfo;
import savit.group2.sockstore.model.security.UserInfoUserDetails;
import savit.group2.sockstore.security.repo.EmployeeInfoRepository;

@Component
@RequiredArgsConstructor
public class EmployeInfoService implements UserDetailsService {
  private final EmployeeInfoRepository repository;
  @Autowired
  PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Employee> employee = repository.getuser(username);
    if (!employee.isPresent() || employee == null) {
      throw new UsernameNotFoundException("Not found user with username is" + username);
    }
    UserInfo userinfo = new UserInfo(employee.get().getEmail(), employee.get().getPassword(),
        employee.get().getRole().getRoles());
    return new UserInfoUserDetails(userinfo);
  }
}
