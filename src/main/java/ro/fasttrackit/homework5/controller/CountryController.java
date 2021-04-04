package ro.fasttrackit.homework5.controller;

import org.springframework.http.HttpStatus;
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
    public List<Country> getAllCountries(@RequestParam(required = false) String includeNeighbour,
                                         @RequestParam(required = false) String excludeNeighbour) {
        return countryService.getCountries(includeNeighbour, excludeNeighbour);
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

    @GetMapping(value = "/mine")
    @ResponseStatus(value = HttpStatus.OK)
    public Country client(@RequestHeader(value="X-Country", defaultValue = "Romania") String country) {
        return countryService.getMyCountry(country);
    }
}
