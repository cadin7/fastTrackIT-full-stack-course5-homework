package ro.fasttrackit.homework5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.homework5.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
