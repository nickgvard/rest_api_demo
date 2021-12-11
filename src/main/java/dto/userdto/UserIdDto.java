package dto.userdto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
public class UserIdDto {

    private Long id;
    private String name;
}