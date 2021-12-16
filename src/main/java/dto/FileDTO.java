package dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.FileEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev 10.12.2021
 * 09.12.2021
 */

@Data
@Builder
public class FileDTO {

    private Long id;
    private String name;

    @SuppressWarnings("ALL")
    public static FileDTO toDTO(FileEntity fileEntity) {
        return (fileEntity == null) ? null : FileDTO
                .builder()
                .id(fileEntity.getId() == null ? null : fileEntity.getId())
                .name(fileEntity.getName() == null ? null : fileEntity.getName())
                .build();
    }

    public static FileDTO toDTO(String fromJson) {
        JsonObject jsonObject = new Gson().fromJson(fromJson, JsonObject.class);
        return FileDTO
                .builder()
                .name(jsonObject.get("name").toString()
                        .replaceAll("\"+", ""))
                .build();
    }

    @SuppressWarnings("ALL")
    public static FileEntity toEntity(FileDTO fileDTO) {
        return (fileDTO == null) ? null : FileEntity
                .builder()
                .id(fileDTO.getId() == null ? null : fileDTO.getId())
                .name(fileDTO.getName() == null ? null : fileDTO.getName())
                .build();
    }
}
