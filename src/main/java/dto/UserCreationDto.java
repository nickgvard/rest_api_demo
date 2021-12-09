package dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev
 * 09.12.2021
 */
@Data
@Builder
public class UserCreationDto {
    private String name;
}