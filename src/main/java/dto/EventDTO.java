package dto;

import entity.EventEntity;
import entity.FileEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
@Data
@Builder
public class EventDTO {

    private Long id;
    private String name;
    private FileDTO fileDTO;

    public static EventDTO toDTO(EventEntity eventEntity) {
        return EventDTO
                .builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .fileDTO(FileDTO
                        .builder()
                        .id(eventEntity.getFileEntity().getId())
                        .name(eventEntity.getName())
                        .build())
                .build();
    }

    public static EventEntity toEntity(EventDTO eventDTO) {
        return EventEntity
                .builder()
                .id(eventDTO.getId())
                .name(eventDTO.getName())
                .fileEntity(FileEntity
                        .builder()
                        .id(eventDTO.getId())
                        .name(eventDTO.getName())
                        .build())
                .build();
    }
}
