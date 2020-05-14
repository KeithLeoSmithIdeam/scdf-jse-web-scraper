package ideam.df.jsescraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class CompanyCategory
{
    @Getter @Setter
    private String htmlId;

    @Getter @Setter
    private String dataIssuerType;

    @Getter @Setter
    private String dataTitle;

    @Getter @Setter
    private String text;

}
