package ideam.df.jsescraper;

import ideam.df.jsescraper.config.properties.JseScraperDbProperties;
import ideam.df.jsescraper.config.properties.JseScraperProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
@EnableConfigurationProperties({ JseScraperProperties.class, JseScraperDbProperties.class  })
public class JseScraperTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(JseScraperTaskApplication.class, args);
    }
}
