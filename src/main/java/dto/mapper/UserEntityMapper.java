package dto.mapper;

import dto.userdto.UserCreationDto;
import dto.userdto.UserDto;
import dto.userdto.UserIdDto;
import entity.UserEntity;

import java.util.ArrayList;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
public class UserEntityMapper {

    public static UserIdDto userIdDto(UserEntity userEntity) {
        return UserIdDto
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }

    public static UserDto toUserDto(UserEntity userEntity) {
        return UserDto
                .builder()
                .name(userEntity.getName())
                .eventEntities(userEntity.getEventEntities())
                .build();
    }

    public static UserEntity userEntity(UserCreationDto userCreationDto) {
        return UserEntity
                .builder()
                .name(userCreationDto.getName())
                .eventEntities(new ArrayList<>())
                .build();
    }
}