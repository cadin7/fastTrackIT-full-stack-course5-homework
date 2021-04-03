package ro.fasttrackit.homework5.reader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homework5.config.CountryFileConfig;
import ro.fasttrackit.homework5.domain.Country;
import ro.fasttrackit.homework5.domain.Neighbour;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.nio.file.Files.lines;
import static java.nio.file.Path.of;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Profile("file")
@Component
public class FileCountryReader implements CountryReader {
    private final CountryFileConfig fileConfig;

    public FileCountryReader(CountryFileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Override
    public List<Country> readCountries() {
        try {
            return lines(of(fileConfig.getFilePath()))
                    .map(this::readCountry)
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Country readCountry(String line) {
        String[] countryInfo = line.split("\\|");
        return new Country(
                countryInfo[0],
                countryInfo[1],
                parseLong(countryInfo[2]),
                parseLong(countryInfo[3]),
                countryInfo[4],
                countryInfo.length > 5 ? parseNeighbours(countryInfo[5]) : List.of()
        );
    }

    private List<Neighbour> parseNeighbours(String neighbours) {
        if (neighbours != null) {
            return stream(neighbours.split("~"))
                    .map(Neighbour::new)
                    .collect(toList());
        } else {
            return List.of();
        }
    }
}
