package ideam.df.jsescraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ApiRequestPaylod {

    @Getter @Setter
    private String filterLongName;
    @Getter @Setter
    private String filterType;
}
