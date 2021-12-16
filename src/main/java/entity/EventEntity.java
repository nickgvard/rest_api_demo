package entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Nikita Gvardeev 07.12.2021
 * 09.12.2021
 */

@Entity
@Table(name = "events", schema = "local_db")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "eventEntity", fetch = FetchType.EAGER)
    private FileEntity fileEntity;
}