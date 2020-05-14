package ideam.df.jsescraper.task;

import ideam.df.jsescraper.config.properties.JseScraperProperties;
import ideam.df.jsescraper.service.WebScrapeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleCommandLineRunner implements CommandLineRunner
{
    private final Log logger = LogFactory.getLog(SampleCommandLineRunner.class);

    @Autowired
    private JseScraperProperties config;

    @Autowired
    private WebScrapeService webScrapeService;

    public SampleCommandLineRunner() {

    }

    @Override
    public void run(String... args) throws Exception
    {
        logger.info("JseScraperTask Started");

        webScrapeService.scrapeWebpage();

        logger.info("JseScraperTask Completed");
    }
}
