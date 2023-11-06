package savit.group2.sockstore.model.request;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import savit.group2.sockstore.model.entity.Customer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class CustomerRequest {
  public UUID id;

  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String email;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String name;

  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String address;
  @NotBlank(message = "Vui lòng nhập trường này!")
  @NotNull(message = "Vui lòng nhập trường này!")
  String phone;
  @NotNull(message = "trường này không thể để trống")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(message = "Ngày sinh không hợp lệ")
  LocalDate birthDay;

  public Boolean status;
  @NotNull

  public String wardcode;
  @NotNull

  public int districtcode;
  @NotNull

  private String fulladdress;

  public CustomerRequest(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
    this.email = customer.getEmail();
    this.phone = customer.getPhone();
    this.birthDay = customer.getBirthday();
    this.address = customer.getAddress();
    this.status = customer.getStatus();
    this.wardcode = customer.getWardcode();
    this.districtcode = customer.getDistrictcode();
    this.fulladdress = customer.getFulladdress();
  }

  public static Customer mapToRequest(CustomerRequest customerRequest) {
    Customer cus = new Customer();
    cus.setId(customerRequest.getId());
    cus.setName(customerRequest.getName());
    cus.setEmail(customerRequest.getEmail());
    cus.setPhone(customerRequest.getPhone());
    cus.setBirthday(customerRequest.getBirthDay());
    cus.setAddress(customerRequest.getAddress());
    cus.setStatus(customerRequest.getStatus());
    cus.setWardcode(customerRequest.getWardcode());
    cus.setDistrictcode(customerRequest.getDistrictcode());
    cus.setFulladdress(customerRequest.getFulladdress());
    return cus;
  }
}
