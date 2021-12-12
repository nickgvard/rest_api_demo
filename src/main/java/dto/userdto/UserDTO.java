package dto.userdto;

import entity.EventEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
public class UserDTO {

    private String name;
    private List<EventEntity> eventEntities;
}
