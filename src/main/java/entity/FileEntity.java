package entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nikita Gvardeev 07.12.2021
 * email gvardeev@po-korf.ru
 */
@Data
@Builder
public class FileEntity {

    private Long id;
    private String name;
}