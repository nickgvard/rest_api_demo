package dto.eventdto;

import entity.FileEntity;
import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
@Data
@Builder
public class EventDto {

    private String name;
    private FileEntity fileEntity;
}
