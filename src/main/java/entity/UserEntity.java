package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikita Gvardeev 07.12.2021
 * 09.12.2021
 */

@Entity
@Table(name = "users", schema = "local_db")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<EventEntity> eventEntities;
}