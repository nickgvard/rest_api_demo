package dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.UserEntity;
import lombok.Builder;
import lombok.Data;

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

    @SuppressWarnings("ALL")
    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO
                .builder()
                .id(userEntity.getId() == null ? null : userEntity.getId())
                .name(userEntity.getName() == null ? null : userEntity.getName())
                .events(userEntity.getEventEntities() == null ? null : userEntity
                        .getEventEntities()
                        .stream()
                        .map(EventDTO::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static UserDTO toDTO(String fromJson) {
        JsonObject jsonObject = new Gson().fromJson(fromJson, JsonObject.class);
        return UserDTO
                .builder()
                .name(jsonObject.get("name").toString()
                        .replaceAll("\"+", ""))
                .build();
    }

    @SuppressWarnings("ALL")
    public static UserEntity toEntity(UserDTO userDTO) {
        return UserEntity
                .builder()
                .id(userDTO.getId() == null ? null : userDTO.getId())
                .name(userDTO.getName() == null ? null : userDTO.getName())
                .eventEntities(userDTO.getEvents() == null ? null : userDTO
                        .getEvents()
                        .stream()
                        .map(EventDTO::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}