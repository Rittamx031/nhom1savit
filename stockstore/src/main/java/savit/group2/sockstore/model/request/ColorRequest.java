package savit.group2.sockstore.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColorRequest {

    private UUID id;

    @NotBlank(message = "Not null is code")
    private String code;

    @NotBlank(message = "Not null is name")
    private String name;

    private Boolean status;

}
