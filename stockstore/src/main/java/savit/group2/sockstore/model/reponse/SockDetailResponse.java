package savit.group2.sockstore.model.reponse;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import savit.group2.sockstore.model.entity.Color;
import savit.group2.sockstore.model.entity.Image_Sock_Detail;
import savit.group2.sockstore.model.entity.Material;
import savit.group2.sockstore.model.entity.Sock;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SockDetailResponse {
  UUID id;
  Integer quantity;
  Double unit_base_price;
  String producer;
  String category;
  String sock;
  String pattern;
  String meterial;
  String size;
  String color;
  String image_sock_detail;
  Boolean status;
}
