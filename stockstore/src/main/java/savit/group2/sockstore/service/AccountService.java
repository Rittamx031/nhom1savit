package savit.group2.sockstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Customer;
import savit.group2.sockstore.model.request.SingupRequest;
import savit.group2.sockstore.repository.AccountRepository;
import savit.group2.sockstore.repository.CustomerRepository;

@Service
public class AccountService {
  @Autowired
  AccountRepository repository;
  @Autowired
  CustomerRepository cusrepository;
  @Autowired
  PasswordEncoder encoder;

  public Account singup(SingupRequest request) {
    Optional<Customer> customer = cusrepository.findById(request.getId_customer());
    if (!customer.isPresent() || customer == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    System.out.println(request.getId_customer());
    System.out.println(request.getEmail());
    System.out.println(request.getPassword());
    Account account = new Account();
    account.setCustomer(customer.get());
    account.setEmail(request.getEmail());
    account.setPassword(encoder.encode(request.getPassword()));
    account.setStatus(true);
    return repository.save(account);
  }
}
