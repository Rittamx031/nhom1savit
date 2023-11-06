package savit.group2.sockstore.security.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Employee;
import savit.group2.sockstore.security.repo.AccountInfoRepository;
import savit.group2.sockstore.security.repo.EmployeeInfoRepository;

@Service
public class SercurityService {
  @Autowired
  AccountInfoRepository accountInfoRepository;
  @Autowired
  EmployeeInfoRepository employeInfoRepository;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  PasswordEncoder encoder;

  EmployeInfoService nhanVienService() {
    return new EmployeInfoService(employeInfoRepository);
  }

  AccountInforService accountService() {
    return new AccountInforService(accountInfoRepository);
  }

  public void setAuthentichByEmail(String email) {
    UserDetails userDetails;
    if (employeInfoRepository.hasEmailis(email) != null) {
      userDetails = nhanVienService().loadUserByUsername(email);
    } else if (accountInfoRepository.hasEmailis(email) != null) {
      userDetails = accountService().loadUserByUsername(email);
    } else {
      userDetails = null;
      return;
    }
    Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null,
        Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
    SecurityContextHolder.getContext().setAuthentication(auth);
    return;
  }

  public void updatePassword(String password) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      try {
        Optional<Employee> employee = employeInfoRepository.getuser(username);
        Optional<Account> account = accountInfoRepository.getuser(username);
        if (employee.isPresent()) {
          employee.get().setPassword(encoder.encode(password));
          employeInfoRepository.save(employee.get());
        } else if (account.isPresent()) {
          account.get().setPassword(encoder.encode(password));
          accountInfoRepository.save(account.get());
        }
      } catch (Exception e) {
        // throw exeption
      }
    } else {
      // throw exeption
    }
  }

  public void ActiveAccount() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      try {
        Optional<Employee> employee = employeInfoRepository.getuser(username);
        Optional<Account> account = accountInfoRepository.getuser(username);
        if (employee.isPresent()) {
          employee.get().setActived(true);
          employeInfoRepository.save(employee.get());
        } else if (account.isPresent()) {
          account.get().setActived(true);
          accountInfoRepository.save(account.get());
        }
      } catch (Exception e) {
        // throw exeption
      }
    } else {
      // throw exeption
    }
  }
}
