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

    @SuppressWarnings("ALL")
    public static EventDTO toDTO(EventEntity eventEntity) {
        return EventDTO
                .builder()
                .id(eventEntity.getId() == null ? null : eventEntity.getId())
                .name(eventEntity.getName() == null ? null : eventEntity.getName())
                .fileDTO(FileDTO
                        .builder()
                        .id(eventEntity.getFileEntity() == null && eventEntity.getFileEntity().getId() == null ? null : eventEntity.getFileEntity().getId())
                        .name(eventEntity.getFileEntity() == null && eventEntity.getFileEntity().getName() == null ? null : eventEntity.getName())
                        .build())
                .build();
    }

    @SuppressWarnings("ALL")
    public static EventEntity toEntity(EventDTO eventDTO) {
        return EventEntity
                .builder()
                .id(eventDTO.getId() == null ? null : eventDTO.getId())
                .name(eventDTO.getName() == null ? null : eventDTO.getName())
                .fileEntity(FileEntity
                        .builder()
                        .id(eventDTO.getFileDTO() == null && eventDTO.getFileDTO().getId() == null ? null : eventDTO.getFileDTO().getId())
                        .name(eventDTO.getFileDTO() == null && eventDTO.getFileDTO().getName() == null ? null : eventDTO.getFileDTO().getName())
                        .build())
                .build();
    }
}
