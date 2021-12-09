package dto;

import entity.EventEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
public class UserDto {

    private String name;
    private List<EventEntity> eventEntities;
}
