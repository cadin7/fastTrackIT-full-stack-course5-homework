package ro.fasttrackit.homework5.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.domain.Neighbour;
import ro.fasttrackit.homework5.reader.CountryReader;
import ro.fasttrackit.homework5.repository.CountryRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableList;

@Service
public class CountryService {
    private final CountryReader countryReader;
    private final CountryRepository countryRepository;

    private CountryService(CountryReader countryReader, CountryRepository countryRepository) {
        this.countryReader = countryReader;
        this.countryRepository = countryRepository;
        saveCountry();
    }

    private void saveCountry() {
        countryReader.readCountries().forEach(countryRepository::save);
    }

    public List<Country> getAllCountries() {
        return unmodifiableList(countryRepository.findAll());
    }

    public Optional<Country> getCountry(long countryId) {
        return countryRepository.findAll()
                .stream()
                .filter(country -> country.getId() == countryId)
                .findFirst();
    }

    public List<String> getAllCountryNames() {
        return countryRepository.findAll()
                .stream()
                .map(Country::getName)
                .collect(toUnmodifiableList());
    }

    public String getCapitalOfCountry(Long countryId) {
        return getCountryByID(countryId).getCapital();
    }

    private Country getCountryByID(Long countryId) {
        return countryRepository.findAll()
                .stream()
                .filter(country -> Objects.equals(country.getId(), countryId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No country found with the ID: " + countryId));
    }

    public long getPopulationOfCountry(Long countryId) {
        return getCountryByID(countryId).getPopulation();
    }

    public List<Country> getCountriesByContinent(String continentName) {
        return getCountriesByContinentName(continentName);
    }

    private List<Country> getCountriesByContinentName(String continentName) {
        return countryRepository.findAll()
                .stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continentName))
                .collect(toUnmodifiableList());
    }

    public List<String> getNeighboursOfCountry(Long countryId) {
        return getCountryByID(countryId).getNeighbours()
                .stream()
                .map(Neighbour::toString)
                .collect(toUnmodifiableList());
    }

    public List<Country> getCountriesOnContinentWithMinPopulation(String continentName, long population) {
        return getCountriesByContinentName(continentName).stream()
                .filter(country -> country.getPopulation() >= population)
                .collect(toUnmodifiableList());
    }

    public List<Country> getCountriesByNeighbours(String includeNeighbour, String excludeNeighbour) {
        return countryRepository.findAll()
                .stream()
                .filter(country -> isNeighbour(country, includeNeighbour, excludeNeighbour))
                .collect(toUnmodifiableList());
    }

    private boolean isNeighbour(Country country, String includeNeighbour, String excludeNeighbour) {
        //TODO: Extract?
        Predicate<Country> countryPredicate =
                c -> c.getNeighbours().contains(includeNeighbour.toUpperCase()) &&
                        !c.getNeighbours().contains(excludeNeighbour.toUpperCase());
        return countryPredicate.test(country);
    }

    public Map<String, Long> getCountryToPopulationMap() {
        return null;
    }

    public Map<String, List<Country>> getContinentToCountriesMap() {
        return null;
    }
}
