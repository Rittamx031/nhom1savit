package savit.group2.sockstore.model.reponse;

import java.util.UUID;

import groovy.transform.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class PatternResponse {
  UUID id;
  String code;
  String name;
  Boolean status;
}
