package ideam.df.jsescraper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name="scraped_companies")
@NoArgsConstructor
@AllArgsConstructor
public class ListedCompany {


    @Id
    @Getter @Setter
    private Integer MasterID;

    @Getter @Setter
    private String AlphaCode;

    @Getter @Setter
    private String Branches = null;

    @Getter @Setter
    ArrayList< Object > Contacts = new ArrayList < Object > ();

    @Getter @Setter
    private String CustomerAlphaCode;

    @Getter @Setter
    private String EmailAddress;

    @Getter @Setter
    private String FaxNumber;

    @Getter @Setter
    private String LongName;

    @Getter @Setter
    private String NewBranches = null;

    @Getter @Setter
    private String PhysicalAddress;

    @Getter @Setter
    private String PostalAddress;

    @Getter @Setter
    private String RegistrationNumber;

    @Getter @Setter
    private String RoleDescription;

    @Getter @Setter
    private String Status;

    @Getter @Setter
    private String TelephoneNumber;

    @Getter @Setter
    private String TradingMemberAssetCode = null;

    @Getter @Setter
    private String Website;

    @Getter
    private LocalDateTime extractDate = LocalDateTime.now();

}
