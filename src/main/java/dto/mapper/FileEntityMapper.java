package dto.mapper;

import dto.filedto.FileDto;
import entity.FileEntity;

/**
 * @author Nikita Gvardeev 10.12.2021
 * email gvardeev@po-korf.ru
 */
public class FileEntityMapper {

    public static FileDto toFileDto(FileEntity fileEntity) {
        return FileDto
                .builder()
                .name(fileEntity.getName())
                .build();
    }

    public static FileEntity toFileEntity(FileDto fileDto) {
        return FileEntity
                .builder()
                .name(fileDto.getName())
                .build();
    }
}
