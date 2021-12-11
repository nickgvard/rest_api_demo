package dto.userdto;

import dto.DTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserCreationDTO extends DTO {

    private String name;
}