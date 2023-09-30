package savit.group2.sockstore.model.request;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SockDetailRequest {
  UUID id;
  Integer quantity;
  Double unit_base_price;
  UUID producer;
  UUID category;
  UUID sock;
  UUID pattern;
  UUID meterial;
  UUID size;
  UUID color;
  UUID image_sock_detail;
  Boolean status;
}
