package dto.mapper;

import dto.UserCreationDto;
import dto.UserDto;
import dto.UserIdDto;
import entity.UserEntity;

import java.util.ArrayList;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
public class UserMapper {

    public static UserCreationDto userCreationDto(UserEntity userEntity) {
        return UserCreationDto
                .builder()
                .name(userEntity.getName())
                .build();
    }

    public static UserIdDto userIdDto(UserEntity userEntity) {
        return UserIdDto
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }

    public static UserDto userDto(UserEntity userEntity) {
        return UserDto
                .builder()
                .name(userEntity.getName())
                .eventEntities(userEntity.getEventEntities())
                .build();
    }

    public static UserEntity toUser(UserCreationDto userCreationDto) {
        return UserEntity
                .builder()
                .name(userCreationDto.getName())
                .eventEntities(new ArrayList<>())
                .build();
    }
}
