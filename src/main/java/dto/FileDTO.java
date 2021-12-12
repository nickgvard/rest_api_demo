package dto;

import entity.FileEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */

@Data
@Builder
public class FileDTO {

    private Long id;
    private String name;

    public static FileDTO toDTO(FileEntity fileEntity) {
        return FileDTO
                .builder()
                .id(fileEntity.getId())
                .name(fileEntity.getName())
                .build();
    }

    public static FileEntity toEntity(FileDTO fileDTO) {
        return FileEntity
                .builder()
                .id(fileDTO.getId())
                .name(fileDTO.getName())
                .build();
    }
}
