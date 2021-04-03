package ro.fasttrackit.homework5.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<Country> getAllCountries(@RequestParam(required = false) String includedNeighbour,
                                         @RequestParam(required = false) String excludedNeighbour) {
        return countryService.getCountriesByNeighbours(includedNeighbour, excludedNeighbour);
    }

    @GetMapping("/names")
    public List<String> getAllCountryNames() {
        return countryService.getAllCountryNames();
    }

    @GetMapping("/{countryId}/capital")
    public String getCapitalOfCountry(@PathVariable Long countryId) {
        return countryService.getCapitalOfCountry(countryId);
    }

    @GetMapping("/{countryId}/population")
    public Long getPopulationOfCountry(@PathVariable Long countryId) {
        return countryService.getPopulationOfCountry(countryId);
    }

    @GetMapping("{countryId}/neighbours")
    public List<String> getNeighboursOfCountry(@PathVariable Long countryId) {
        return countryService.getNeighboursOfCountry(countryId);
    }

    @GetMapping("/population")
    public Map<String, Long> getCountryToPopulationMap(){
        return countryService.getCountryToPopulationMap();
    }
}
