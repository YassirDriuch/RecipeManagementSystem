package recipes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotNull
    @NotBlank
    private String name, category, description;

    @CreationTimestamp
    private LocalDateTime date;

    @NotNull
    @NotEmpty
    @ElementCollection
    private List<String> ingredients;

    @NotNull
    @NotEmpty
    @ElementCollection
    private List<String> directions;

}
