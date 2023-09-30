package savit.group2.sockstore.model.reponse;

import java.util.UUID;

import groovy.transform.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AccountResponse {
  UUID idCustomer;
  String name;
  String phoneNumber;
  String email;
  int status;
}
