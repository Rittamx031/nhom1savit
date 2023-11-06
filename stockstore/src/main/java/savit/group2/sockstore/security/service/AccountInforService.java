package savit.group2.sockstore.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.security.UserInfo;
import savit.group2.sockstore.model.security.UserInfoUserDetails;
import savit.group2.sockstore.security.repo.AccountInfoRepository;

@Component
@RequiredArgsConstructor
public class AccountInforService implements UserDetailsService {
  private final AccountInfoRepository repository;
  @Autowired
  PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<Account> account = repository.getuser(username);
    if (!account.isPresent() || account == null) {
      throw new UsernameNotFoundException("can not find nhan vien with username khachhang");
    }
    String Roles;
    if (account.get().getActived()) {
      Roles = "USER";
    }
    {
      Roles = "NOT_ACCTIVE";
    }
    UserInfo UserInfo = new UserInfo(account.get().getEmail(), account.get().getPassword(), Roles);
    return new UserInfoUserDetails(UserInfo);
  }
}
