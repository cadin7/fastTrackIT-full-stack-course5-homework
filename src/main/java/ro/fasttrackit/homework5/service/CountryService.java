package ro.fasttrackit.homework5.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.domain.Neighbour;
import ro.fasttrackit.homework5.reader.CountryReader;
import ro.fasttrackit.homework5.repository.CountryRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.*;

@Service
public class CountryService {
    private final CountryReader countryReader;
    private final CountryRepository countryRepository;

    private CountryService(CountryReader countryReader, CountryRepository countryRepository) {
        this.countryReader = countryReader;
        this.countryRepository = countryRepository;
        saveCountries();
    }

    private void saveCountries() {
        countryReader.readCountries().forEach(countryRepository::save);
    }

    private List<Country> getAllCountries() {
        return unmodifiableList(countryRepository.findAll());
    }

    public List<String> getAllCountryNames() {
        return getAllCountries().stream()
                .map(Country::getName)
                .collect(toUnmodifiableList());
    }

    public String getCapitalOfCountry(Long countryId) {
        return getCountry(countryId).getCapital();
    }

    private Country getCountry(Long countryId) {
        return getAllCountries()
                .stream()
                .filter(country -> Objects.equals(country.getId(), countryId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No country found with the ID: " + countryId));
    }

    public long getPopulationOfCountry(Long countryId) {
        return getCountry(countryId).getPopulation();
    }

    public List<Country> getContinentCountries(String continentName, Long minPopulation) {
        return minPopulation == null ?
                getCountriesByContinentName(continentName) :
                getCountriesOnContinentWithMinPopulation(continentName, minPopulation);
    }

    private List<Country> getCountriesByContinentName(String continentName) {
        return getAllCountries()
                .stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continentName))
                .collect(toUnmodifiableList());
    }

    private List<Country> getCountriesOnContinentWithMinPopulation(String continentName, Long minPopulation) {
        return getCountriesByContinentName(continentName).stream()
                .filter(country -> country.getPopulation() >= minPopulation)
                .collect(toUnmodifiableList());
    }

    public List<String> getNeighboursOfCountry(Long countryId) {
        return getCountry(countryId).getNeighbours()
                .stream()
                .map(Neighbour::getNeighbour)
                .collect(toUnmodifiableList());
    }

    public List<Country> getCountries(String includedNeighbour, String excludedNeighbour) {
        return includedNeighbour == null && excludedNeighbour == null ?
                getAllCountries() : getCountriesByNeighbours(includedNeighbour, excludedNeighbour);
    }

    private List<Country> getCountriesByNeighbours(String includedNeighbour, String excludedNeighbour) {
        return getAllCountries()
                .stream()
                .filter(country -> isNeighbour(country, includedNeighbour, excludedNeighbour))
                .collect(toUnmodifiableList());
    }

    private boolean isNeighbour(Country country, String includedNeighbour, String excludedNeighbour) {
        List<String> neighboursOfCountry = getNeighboursOfCountry(country.getId());
        return neighboursOfCountry.contains(includedNeighbour.toUpperCase()) &&
                !neighboursOfCountry.contains(excludedNeighbour.toUpperCase());
    }

    public Map<String, Long> getCountryToPopulationMap() {
        return getAllCountries().stream()
                .collect(toMap(
                        Country::getName,
                        Country::getPopulation,
                        Math::addExact,
                        TreeMap::new
                ));
    }

    public Map<String, List<Country>> getContinentToCountriesMap() {
        return getAllCountries().stream()
                .collect(groupingBy(
                        Country::getContinent,
                        TreeMap::new,
                        toUnmodifiableList()
                ));
    }

    public Country getMyCountry(String countryName){
        return getAllCountries().stream()
                .filter(country -> country.getName().equalsIgnoreCase(countryName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No country found with name: " + countryName));
    }
}
