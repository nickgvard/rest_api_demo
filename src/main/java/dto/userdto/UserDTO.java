package dto.userdto;

import dto.DTO;
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
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends DTO {

    private String name;
    private List<EventEntity> eventEntities;
}
