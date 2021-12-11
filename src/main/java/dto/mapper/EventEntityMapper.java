package dto.mapper;

import dto.eventdto.EventCreationDto;
import dto.eventdto.EventDto;
import dto.eventdto.EventIdDto;
import entity.EventEntity;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
public class EventEntityMapper {

    public static EventIdDto toEventIdDto(EventEntity eventEntity) {
        return EventIdDto
                .builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .build();
    }

    public static EventDto toEventDto(EventEntity eventEntity) {
        return EventDto
                .builder()
                .name(eventEntity.getName())
                .fileEntity(eventEntity.getFileEntity())
                .build();
    }

    public static EventEntity toEventEntity(EventCreationDto eventDto) {
        return EventEntity
                .builder()
                .name(eventDto.getName())
                .build();
    }
}
