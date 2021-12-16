package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Nikita Gvardeev 07.12.2021
 * 09.12.2021
 */

@Entity
@Table(name = "events", schema = "li0kf6qceun7hant")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private FileEntity fileEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}