package ideam.df.jsescraper.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("scraper.db")
@AllArgsConstructor
@NoArgsConstructor
public class JseScraperDbProperties {

    /**
     * Jdbc url for the target database to connect to
     */
    @Getter
    @Setter
    private String url;

    /**
     * Username for the connection to the database
     */
    @Getter
    @Setter
    private String username;

    /**
     * Passowrd for the connection to the database
     */
    @Getter
    @Setter
    private String password;

    /**
     * The classname for the drive rot be used to make the connection. The driver must be loaded in the app as a dependency
     */
    @Getter
    @Setter
    private String driverClassName;
}
