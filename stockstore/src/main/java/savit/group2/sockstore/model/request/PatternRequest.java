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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatternRequest {
  UUID id;
  String code;
  String name;
  Boolean status;
}
