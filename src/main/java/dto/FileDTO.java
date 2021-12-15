package dto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

    @SuppressWarnings("ALL")
    public static FileDTO toDTO(FileEntity fileEntity) {
        return FileDTO
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
        return FileEntity
                .builder()
                .id(fileDTO.getId() == null ? null : fileDTO.getId())
                .name(fileDTO.getName() == null ? null : fileDTO.getName())
                .build();
    }
}
