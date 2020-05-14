package ideam.df.jsescraper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomTaskConfigurer extends DefaultTaskConfigurer
{
    @Autowired
    public CustomTaskConfigurer(@Qualifier("scdf-ds") DataSource dataSource) {
        super(dataSource);
    }
}
