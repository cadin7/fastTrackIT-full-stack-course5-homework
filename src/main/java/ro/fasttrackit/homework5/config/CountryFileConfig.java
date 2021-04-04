package ro.fasttrackit.homework5.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "country")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class CountryFileConfig {
    @NotBlank
    private final String filePath;

}
