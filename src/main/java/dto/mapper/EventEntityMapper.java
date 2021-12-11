package dto.mapper;

import dto.eventdto.EventCreationDTO;
import dto.eventdto.EventDTO;
import dto.eventdto.EventIdDTO;
import entity.EventEntity;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
public class EventEntityMapper {

    public static EventIdDTO toEventIdDto(EventEntity eventEntity) {
        return EventIdDTO
                .builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .build();
    }

    public static EventDTO toEventDto(EventEntity eventEntity) {
        return EventDTO
                .builder()
                .name(eventEntity.getName())
                .fileEntity(eventEntity.getFileEntity())
                .build();
    }

    public static EventEntity toEventEntity(EventCreationDTO eventDto) {
        return EventEntity
                .builder()
                .name(eventDto.getName())
                .build();
    }
}
