package dto.mapper;

import dto.filedto.FileDTO;
import entity.FileEntity;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class FileEntityMapper {

    public static FileDTO toFileDto(FileEntity fileEntity) {
        return FileDTO
                .builder()
                .name(fileEntity.getName())
                .build();
    }

    public static FileEntity toFileEntity(FileDTO fileDto) {
        return FileEntity
                .builder()
                .name(fileDto.getName())
                .build();
    }
}
