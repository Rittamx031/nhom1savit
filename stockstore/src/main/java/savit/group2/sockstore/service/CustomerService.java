package savit.group2.sockstore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import savit.group2.sockstore.model.entity.Customer;
import savit.group2.sockstore.repository.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  CustomerRepository repository;

  public List<Customer> getAll() {
    return repository.getAllByStatus(true);
  }

  public Customer create(Customer cus) {
    return repository.save(cus);
  }

  public Customer update(Customer cus, UUID id) {
    Optional<Customer> exittCus = repository.findById(id);
    if (exittCus.isPresent()) {
      Customer exitCus = exittCus.get();
      exitCus.setBirthday(cus.getBirthday());
      exitCus.setName(cus.getName());
      exitCus.setPhone(cus.getPhone());
      exitCus.setStatus(cus.getStatus());
      exitCus.setStatus(cus.getStatus());
      exitCus.setAddress(cus.getAddress());
      exitCus.setUpdateAt(LocalDateTime.now());
      return repository.save(exitCus);
    } else {
      return null;
    }
  }

  public HttpStatusCode delete(UUID id) {
    Optional<Customer> exittCus = repository.findById(id);
    if (exittCus.isPresent()) {
      exittCus.get().setDeleted(true);
      repository.delete(exittCus.get());
      return HttpStatus.ACCEPTED;
    } else {
      return HttpStatus.BAD_REQUEST;
    }
  }
}
