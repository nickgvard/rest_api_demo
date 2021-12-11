package dto.mapper;

import dto.userdto.UserCreationDTO;
import dto.userdto.UserDTO;
import dto.userdto.UserIdDTO;
import entity.UserEntity;

import java.util.ArrayList;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
public class UserEntityMapper {

    public static UserIdDTO userIdDto(UserEntity userEntity) {
        return UserIdDTO
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }

    public static UserDTO toUserDto(UserEntity userEntity) {
        return UserDTO
                .builder()
                .name(userEntity.getName())
                .eventEntities(userEntity.getEventEntities())
                .build();
    }

    public static UserEntity userEntity(UserCreationDTO userCreationDto) {
        return UserEntity
                .builder()
                .name(userCreationDto.getName())
                .eventEntities(new ArrayList<>())
                .build();
    }
}