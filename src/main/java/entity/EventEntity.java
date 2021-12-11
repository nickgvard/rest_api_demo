package entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Nikita Gvardeev 07.12.2021
 * email gvardeev@po-korf.ru
 */

@Entity
@Table(name = "events", schema = "local_db")
@Data
@Builder
@NoArgsConstructor
@ToString
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "eventEntity", fetch = FetchType.EAGER)
    private FileEntity fileEntity;
}