package ideam.df.jsescraper.config;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ScdfDataSourceConfig {

    @Autowired
    private Environment env;

    //INJECTED BY SCDF
    //--spring.datasource.url=jdbc:mysql://10.152.183.236:3306/mysql
    //--spring.datasource.driverClassName=org.mariadb.jdbc.Driver
    //--spring.datasource.password=*****
    //--spring.datasource.username=****

    @Profile("prod")
    @Qualifier("scdf-ds")
    @Bean
    @Primary
    //@ConfigurationProperties(prefix = "spring.datasource")
    public DataSource scdfDataSourceProd() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("spring.datasource.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("spring.datasource.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("spring.datasource.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("spring.datasource.password")));

        return dataSource;
    }

    @Profile("dev")
    @Qualifier("scdf-ds")
    @Bean(name="scdfDataSourceDev")
    public DataSource taskDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/scdf-dev");
        dataSource.setUsername("root");
        dataSource.setPassword("Password1");
        return dataSource;
    }
}
