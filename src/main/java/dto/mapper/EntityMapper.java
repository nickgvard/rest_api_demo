package dto.mapper;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
public interface EntityMapper<Entity, DTO> {

    Entity toEntity(DTO dto);
    DTO toDto(Entity entity);
}
