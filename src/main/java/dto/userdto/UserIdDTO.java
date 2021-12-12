package dto.userdto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
public class UserIdDTO {

    private Long id;
    private String name;
}