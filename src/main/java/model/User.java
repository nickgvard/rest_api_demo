package model;

import lombok.*;

import java.util.List;

/**
 * @author Nikita Gvardeev 07.12.2021
 * email gvardeev@po-korf.ru
 */

@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class User {

    private Long id;

    private String name;

    private List<Event> events;
}