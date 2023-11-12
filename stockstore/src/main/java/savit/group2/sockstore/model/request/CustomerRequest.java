package savit.group2.sockstore.model.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.entity.Customer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class CustomerRequest {
  public UUID id;
  public String name;
  @Email
  public String email;
  @Pattern(regexp = "^(0[0-9]{9}|\\+84[0-9]{9})$", message = "Invalid Vietnamese phone number")
  public String phone;
  @NotNull
  @Past(message = "Birthday must be in the past")
  public LocalDate birthday;
  @NotNull
  public String address;

  public Boolean deleted;

  public Boolean status;
  @NotNull

  public String wardcode;
  @NotNull

  public int districtcode;
  private String fulladdress;

  public CustomerRequest(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getEmail();
    this.email = customer.getEmail();
    this.phone = customer.getPhone();
    this.birthday = customer.getBirthday();
    this.address = customer.getAddress();
    this.deleted = customer.getDeleted();
    this.status = customer.getStatus();
    this.wardcode = customer.getWardcode();
    this.districtcode = customer.getDistrictcode();
    // this.status = customer.getStatus();
  }
}
