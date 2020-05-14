package ideam.df.jsescraper.repository;

import ideam.df.jsescraper.model.ListedCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListedCompanyRepository extends CrudRepository<ListedCompany, Integer> {
}
