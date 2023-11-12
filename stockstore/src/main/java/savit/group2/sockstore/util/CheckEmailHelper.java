package savit.group2.sockstore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import savit.group2.sockstore.repository.AccountRepository;
import savit.group2.sockstore.repository.EmployeeRepository;

@Component
public class CheckEmailHelper {
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  AccountRepository accountRepository;

  public boolean isEmailNotExsits(String email) {
    if (!employeeRepository.hasEmailis(email).isEmpty()) {
      return false;
    }
    if (!accountRepository.hasEmailis(email).isEmpty()) {
      return false;
    }
    return true;
  }

}
