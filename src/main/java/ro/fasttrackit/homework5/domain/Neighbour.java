package ro.fasttrackit.homework5.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Neighbour {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String neighbour;

    public Neighbour(String neighbour) {
        this.neighbour = neighbour;
    }

}
