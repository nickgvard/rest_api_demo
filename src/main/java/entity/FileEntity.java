package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikita Gvardeev 07.12.2021
 * 09.12.2021
 */
@Entity
@Table(name = "files", schema = "li0kf6qceun7hant")
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

    @OneToMany(mappedBy = "fileEntity", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<EventEntity> eventEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}