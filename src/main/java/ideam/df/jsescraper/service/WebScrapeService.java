package ideam.df.jsescraper.service;

import ideam.df.jsescraper.config.properties.JseScraperProperties;
import ideam.df.jsescraper.model.ApiRequestPaylod;
import ideam.df.jsescraper.model.CompanyCategory;
import ideam.df.jsescraper.model.ListedCompany;
import ideam.df.jsescraper.repository.ListedCompanyRepository;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class WebScrapeService {

    private static final Logger logger = LoggerFactory.getLogger(WebScrapeService.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";

    @Autowired
    private JseScraperProperties jseScraperProperties;

    @Autowired
    private ListedCompanyRepository listedCompanyRepository;

    public WebScrapeService() {
    }

    public void scrapeWebpage() throws IOException
    {
        logger.info("STARTING");
        logger.info("JseScraperProperties truncateBefore set to " + jseScraperProperties.isTruncateBefore());
        truncatebasedOnProps();
        scrapeWebpageJse();
    }

    private void truncatebasedOnProps()
    {
        if(jseScraperProperties.isTruncateBefore())
        {
            listedCompanyRepository.deleteAll();
            logger.info("table truncated ........");
        }
        else
            logger.info("table NOT truncated .......");
    }

    /**
     * Scrapes the given url and returns a list of CompanyCategory objects that have been web scraped using the given selctor
     * @param url
     * @param selector
     * @return
     * @throws IOException
     */
    private List<CompanyCategory> scrapeCategoriesFromHtml(String url, String selector) throws IOException
    {
        List<CompanyCategory> returnList = new ArrayList<CompanyCategory>();

        logger.info(String.format("Executing scrape to static web page [%s]",url));

        final Document page = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .get();

        for(Element category : page.select(selector))
        {
           String htmlId = category.id();
           String dataIssuerType = category.attr("data-issuertype");
           String dataTitle = category.attr("data-title");
           String text = category.text();
           returnList.add(new CompanyCategory(htmlId,dataIssuerType,dataTitle,text));
        }
        logger.info(String.format("Found [%d] categories to search for data via api calls ",returnList.size()));
        return returnList;
    }

    public void scrapeWebpageJse() throws IOException
    {
        final String url = "https://www.jse.co.za/current-companies/companies-and-financial-instruments";
        final String selectorForCategories = "ul#filter__tab1 li";
        final String restApiUrl = "https://www.jse.co.za/_vti_bin/JSE/CustomerRoleService.svc/GetAllIssuers";

        final Map<String,String> restHeaders = new HashMap<String,String>(){{
            put("User-Agent", USER_AGENT);
            put("Host", "www.jse.co.za");
            put("Origin", "https://www.jse.co.za");
            put("Referer", "https://www.jse.co.za/current-companies/companies-and-financial-instruments");
            put("Content-Type", "application/json");
            put("Accept", "application/json, text/javascript, */*; q=0.01");
        }};

        List<CompanyCategory> categoryList = scrapeCategoriesFromHtml(url,selectorForCategories);

        logger.info("Api request header set for subsequent api calls to dynamic endpoints");
        logger.info(restHeaders.toString());

        for (CompanyCategory c: categoryList) {

            logger.info(String.format("Initiating scrape to api endpoint for category - %s ", c.getDataIssuerType()));

            final List<ListedCompany> companies = Unirest.post(restApiUrl)
                    .headers(restHeaders)
                    .body(new ApiRequestPaylod("",c.getDataIssuerType()))
                    .asObject(new GenericType<List<ListedCompany>>(){})
                    .getBody()
                    ;

            logger.info("");
            if(companies.size() > 0) {
                saveBatchToDb(companies);
            }

        }
        Unirest.shutDown();
    }

    private void saveBatchToDb(List<ListedCompany> companies)
    {
        logger.info(String.format("Saving batch of [%d] listed companies to database",companies.size()));
        listedCompanyRepository.saveAll(companies);
        logger.info("Commit to database successful");
    }

}
