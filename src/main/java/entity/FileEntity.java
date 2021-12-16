package entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Nikita Gvardeev 07.12.2021
 * 09.12.2021
 */
@Entity
@Table(name = "files", schema = "local_db")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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