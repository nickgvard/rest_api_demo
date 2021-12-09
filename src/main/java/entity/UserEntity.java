package entity;

import lombok.*;

import java.util.List;

/**
 * @author Nikita Gvardeev 07.12.2021
 * email gvardeev@po-korf.ru
 */

@Data
@Builder
@ToString
public class UserEntity {

    private Long id;

    private String name;

    private List<EventEntity> eventEntities;
}