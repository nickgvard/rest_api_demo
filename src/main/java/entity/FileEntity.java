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
@Table(name = "files", schema = "local_db")
@Data
@Builder
@NoArgsConstructor
@ToString
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ToString.Exclude
    private EventEntity eventEntity;
}