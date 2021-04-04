package ro.fasttrackit.homework5.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String capital;

    @NotNull
    private long population;
    @NotNull
    private long area;

    @NotBlank
    private String continent;

    @OneToMany(cascade = ALL)
    private List<Neighbour> neighbours;

    public Country(String name, String capital, long population, long area, String continent,
                   List<Neighbour> neighbours) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = ofNullable(neighbours)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }
}

