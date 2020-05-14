package ideam.df.jsescraper.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("scraper")
@AllArgsConstructor
@NoArgsConstructor
public class JseScraperProperties
{

    /**
     * Flag to indicate whether the table should be truncated before each scrape
     */
    @Getter @Setter
    private boolean truncateBefore = true;

}
