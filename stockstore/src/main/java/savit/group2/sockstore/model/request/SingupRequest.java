package savit.group2.sockstore.model.request;

import java.util.UUID;

import groovy.transform.ToString;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SingupRequest {
  @NotBlank
  @NotNull
  UUID id_customer;
  @NotBlank
  @NotNull
  String email;
  @NotBlank
  @NotNull
  String password;
}
