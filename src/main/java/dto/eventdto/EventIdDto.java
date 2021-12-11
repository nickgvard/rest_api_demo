package dto.eventdto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
@Data
@Builder
public class EventIdDto {

    private Long id;
    private String name;
}
