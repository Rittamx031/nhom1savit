package savit.group2.sockstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Customer;
import savit.group2.sockstore.model.reponse.AccountResponse;
import savit.group2.sockstore.model.request.AccountRequest;
import savit.group2.sockstore.model.request.UserSingupRequest;
import savit.group2.sockstore.repository.AccountRepository;
import savit.group2.sockstore.repository.CustomerRepository;
import savit.group2.sockstore.security.service.SercurityService;
import savit.group2.sockstore.service.interfaceservice.PanigationInterface;
import savit.group2.sockstore.util.CheckEmailHelper;

@Service
public class AccountService implements PanigationInterface<AccountResponse> {
  @Autowired
  AccountRepository repository;
  @Autowired
  CustomerRepository cusrepository;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  CheckEmailHelper emailHelper;
  @Autowired
  SercurityService sercurityService;

  public Account singup(UserSingupRequest request) {
    if (emailHelper.isEmailNotExsits(request.getEmail())) {
      Customer customer = new Customer();
      customer.setBirthday(request.getBirthDay());
      customer.setEmail(request.getEmail());
      customer.setPhone(request.getPhone());
      customer.setName(request.getName());
      customer.setStatus(true);
      customer.setUpdateAt(LocalDateTime.now());
      customer = cusrepository.save(customer);
      Account account = new Account();
      account.setCustomer(customer);
      account.setEmail(request.getEmail());
      account.setPassword(encoder.encode(request.getPassword()));
      account.setStatus(true);
      account.setUpdateAt(LocalDateTime.now());
      account = repository.save(account);
      sercurityService.setAuthentichByEmail(account.getEmail());
      return account;
    }
    return null;
  }

  // admin
  public Account updateAccount(AccountRequest account) {
    Optional<Account> accountupdate = repository.findById(account.idCustomer);
    if (accountupdate.isPresent()) {
      Account account2 = accountupdate.get();
      account2.setId(account.idCustomer);
      account2.setEmail(account.email);
      return repository.save(account2);
    } else {
      throw new RuntimeException();
    }
  }

  public Account createAccout(AccountRequest request) {
    Account newAcc = new Account();
    newAcc.setEmail(request.email);
    newAcc.setPassword(encoder.encode(request.password));
    newAcc.setStatus(true);
    Account saveAccout = repository.save(newAcc);
    return saveAccout;
  }

  public Account changePassword(AccountRequest request) {
    Optional<Account> accountExits = repository.findById(request.idCustomer);
    if (accountExits.isPresent()) {
      Account newAcc = accountExits.get();
      newAcc.setPassword(encoder.encode(request.password));
      Account saveAccout = repository.save(newAcc);
      return saveAccout;
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not find Account with id ");
    }
  }
  // panigation
  @Override
  public List<AccountResponse> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    if (pageNo > this.getPageNumber(pageSize)) {

    }
    List<AccountResponse> ChiTietSanPhams;
    ChiTietSanPhams = new ArrayList<>();
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
      System.out.println("tang");
    } else {
      sort = Sort.by(sortBy).descending();
      System.out.println("giam");
    }
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    // findAll method and pass pageable instance
    Page<AccountResponse> page = repository.getPageAccountRepose(pageable);
    ChiTietSanPhams = page.getContent();
    return ChiTietSanPhams;
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<AccountResponse> page = repository.getPageAccountRepose(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  // panigation end
  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(0, rowcount);
    Page<AccountResponse> page = repository.getPageAccountRepose(pageable);
    int totalPage = page.getTotalPages();
    int[] rs;
    if (totalPage <= 1) {
      return new int[] { 1 };
    }
    if (totalPage <= 3) {
      rs = new int[totalPage];
      for (int i = 1; i <= totalPage; i++) {
        rs[i] = i;
      }
      return rs;
    } else {
      rs = new int[3];
      if (pageno <= 2) {
        int[] rs1 = { 1, 2, 3 };
        rs = rs1;
      }
      if (pageno > 2) {
        if (pageno < totalPage - 1) {
          int[] rs1 = { pageno - 1, pageno, pageno + 1 };
          rs = rs1;
        }
        if (pageno >= totalPage - 1) {
          int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
          rs = rs1;
        }
      }
      return rs;
    }
  }
}
