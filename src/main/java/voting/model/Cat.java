package voting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "cat")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cat {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "bytes_of_image", columnDefinition = "blob")
    private byte[] imageBytes;
    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    public Cat(String name, byte[] imageBytes) {
        this.name = name;
        this.imageBytes = imageBytes;
    }
}
