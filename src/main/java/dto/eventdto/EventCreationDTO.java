package dto.eventdto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev
 * 11.12.2021
 */
@Data
@Builder
public class EventCreationDTO {

    private String name;
}
