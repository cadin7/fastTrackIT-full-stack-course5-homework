package ro.fasttrackit.homework5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.homework5.domain.Neighbour;

public interface NeighbourRepository extends JpaRepository<Neighbour, Long> {
}
