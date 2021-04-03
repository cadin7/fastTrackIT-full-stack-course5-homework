package ro.fasttrackit.homework5.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("continents")
public class ContinentController {
    private final CountryService countryService;

    public ContinentController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{continentName}/countries")
    public List<Country> getCountriesOnContinentWithMinPopulation(@PathVariable String continentName,
                                                                  @RequestParam(required = false) Long minPopulation) {
        return countryService.getContinentCountries(continentName, minPopulation);
    }

    @GetMapping("/countries")
    public Map<String, List<Country>> getContinentToCountriesMap() {
        return countryService.getContinentToCountriesMap();
    }
}