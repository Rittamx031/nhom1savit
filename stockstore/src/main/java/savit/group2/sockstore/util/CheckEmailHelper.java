package savit.group2.sockstore.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Employee;
import savit.group2.sockstore.repository.AccountRepository;
import savit.group2.sockstore.repository.EmployeeRepository;

@Component
public class CheckEmailHelper {
  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  AccountRepository accountRepository;

  public boolean isEmailNotExsits(String Email) {
    List<Employee> employees = employeeRepository.hasEmailis(Email);
    List<Account> accounts = accountRepository.hasEmailis(Email);
    if (employees == null) {
      if (accounts == null) {
        return true;
      }
    }
    if (employees.size() == 0) {
      if (accounts.size() == 0) {
        return true;
      }
    }
    return false;
  }
}
