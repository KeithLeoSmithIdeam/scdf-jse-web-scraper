package ideam.df.jsescraper.service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

@Service
public class WebScrapeExamplesService {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36";

    public void scrapeWebpageGoogle() throws IOException
    {
        final String query = "apple";
        final Document page = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(query))
                .userAgent(USER_AGENT)
                .get();

        for(Element searchResult : page.select("h3.r a"))
        {
            final String title = searchResult.text();
            final String url = searchResult.attr("href");
            System.out.println(title + " -> " + url);
        }

    }

    public void scrapeWebpagePeopleFInderDynamic() throws IOException
    {
        final String lastName = "Smith";
        final String city = "New York";
        final String state = "NY";

        final HttpResponse<String> response = Unirest.get("http://www.peoplefinders.com/GetResults?Latitude=0&Longitude=0&BoundSize=0&MaxRowsSpecified=True&" +
                "MaxRows=80&Rows=10&Start=1&SearchEngineID=2&SearchEngineIDSpecified=True&search=People&" +
                "StateDropDownPlaceholder=State&Widgets=System.Collections.Generic.Dictionary%602%5BSystem.String%2CSystem.Int32%5D&isVoucher=False")
                .queryString("ln", lastName)
                .queryString("LastName", lastName)
                .queryString("city", city)
                .queryString("state", state)
                .asString();

        final Document htmlSnippet = Jsoup.parseBodyFragment(response.getBody());

        for (Element peopleResult : htmlSnippet.select("div.resultRow > div.col-md-12 > div.row > div.col-md-12")) {

            final String name = peopleResult.select("a.name-blue").text();
            final String age = peopleResult.child(1).text();
            final Set<String> relatives = new HashSet<>();

            for (Element relative : peopleResult.child(3).children().select("div > div")) {
                relatives.add(relative.text());
            }

            System.out.println(name + " -> age: " + age + ", relatives: " + String.join(", ", relatives));
        }

    }


    public void scrapeWebpageWikipedia() throws IOException
    {
        System.out.println("Starting web scrape process");

        final Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

        final Elements newsHeadlines = doc.select("#mp-itn b a");

        for(Element headline : newsHeadlines)
        {
            System.out.println(headline.text());
        }

        System.out.println("\n\n---------------------------------------------------------------------------------------\n\n");

        System.out.println(doc.outerHtml());


    }
}
