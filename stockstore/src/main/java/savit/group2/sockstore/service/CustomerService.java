package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.entity.Customer;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.repository.CustomerRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    long millis = System.currentTimeMillis();

    public List<Customer> getALl(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return customerRepository.getALL(pageable);
    }
    public Customer getOne(UUID id){
        return customerRepository.findById(id).orElse(null);
    }

    public Customer add(Customer customer) {
        Customer customerSave = Customer.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .birthday(customer.getBirthday())
                .status(true)
                .build();
        return customerRepository.save(customerSave);
    }

    public Customer update(Customer customer, UUID id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if ((optional.isPresent())) {
            return optional.map(customerUpdate -> {
//                customerUpdate.setCode(material.getCode());
                        customerUpdate.setName(customer.getName());
                        customerUpdate.setEmail(customer.getEmail());
                        customerUpdate.setPhone(customer.getPhone());
                        customerUpdate.setBirthday(customer.getBirthday());
                        customerUpdate.setStatus(customer.getStatus());
                        return customerRepository.save(customerUpdate);
                    }
            ).get();
        }
        return null;
    }

    public Boolean delete(UUID id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            customerRepository.delete(optional.get());
            return true;
        }
        return false;
    }

}
