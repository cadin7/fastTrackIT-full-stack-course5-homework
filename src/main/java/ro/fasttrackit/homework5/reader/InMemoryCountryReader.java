package ro.fasttrackit.homework5.reader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.domain.Neighbour;

import java.util.List;

@Profile("memory")
@Component
public class InMemoryCountryReader implements CountryReader {
    @Override
    public List<Country> readCountries() {
        return List.of(
                new Country(
                        "Austria",
                        "Vienna",
                        8725931,
                        83871,
                        "Europe",
                        List.of(new Neighbour("CZE"), new Neighbour("DEU"), new Neighbour("HUN"),
                                new Neighbour("ITA"), new Neighbour("LIE"), new Neighbour("SVK"),
                                new Neighbour("SVN"), new Neighbour("CHE"))),
                new Country(
                        "Belgium",
                        "Brussels",
                        11319511,
                        30528,
                        "Europe",
                        List.of(new Neighbour("FRA"), new Neighbour("DEU"), new Neighbour("LUX"),
                                new Neighbour("NLD"))),
                new Country(
                        "Canada",
                        "Ottawa",
                        36155487,
                        9984670,
                        "Americas",
                        List.of(new Neighbour("USA"))),
                new Country(
                        "Chile",
                        "Santiago",
                        18191900,
                        756102,
                        "Americas",
                        List.of(new Neighbour("ARG"), new Neighbour("BOL"), new Neighbour("PER"))),
                new Country(
                        "Cocos (Keeling) Islands",
                        "West Island",
                        550,
                        14,
                        "Oceania",
                        List.of()),
                new Country(
                        "Egypt",
                        "Cairo",
                        91290000,
                        1002450,
                        "Africa",
                        List.of(new Neighbour("ISR"), new Neighbour("LBY"), new Neighbour("SDN"))),
                new Country(
                        "Faroe Islands",
                        "Tórshavn",
                        49376, 1393,
                        "Europe",
                        List.of()),
                new Country(
                        "Hong Kong",
                        "City of Victoria",
                        7324300,
                        1104,
                        "Asia",
                        List.of(new Neighbour("CHN")))
        );
    }
}
