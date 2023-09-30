package savit.group2.sockstore.model.request;


import java.util.UUID;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import savit.group2.sockstore.model.entity.Account;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccountRequest {
  @NotNull(message = "Hãy chọn khách hàng")
  UUID idCustomer;
  @NotNull(message = "Hãy nhập Email")
  @NotBlank(message = "Hãy nhập Email")
  @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message="Email Định dạng không đúng")
  String email;
  @NotNull(message = "Hãy nhập Mật Khẩu")
  @NotBlank(message = "Hãy nhập Mật Khẩu")
  @Size(min = 8, max = 25, message = "Mật khẩu từ 8 đến 25 ký tự ")
  String password;
  @NotNull(message = "Hãy nhập Lại Mật khẩu")
  @NotBlank(message = "Hãy nhập Lại Mật khẩu")
  @Size(min = 8, max = 25, message = "Mật khẩu từ 8 đến 25 ký tự ")
  String passwordRepeat;
  @Range(min = -1, max = 1)
  int status;

  public AccountRequest(Account account) {
    this.idCustomer = account.getId();
    this.email = account.getEmail();
    // this.status = account.getStatus();
  }
}
