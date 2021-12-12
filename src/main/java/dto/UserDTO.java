package dto;

import entity.EventEntity;
import entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private List<EventDTO> events;

    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .events(userEntity
                        .getEventEntities()
                        .stream()
                        .map(EventDTO::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        return UserEntity
                .builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .eventEntities(userDTO
                        .getEvents()
                        .stream()
                        .map(EventDTO::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
