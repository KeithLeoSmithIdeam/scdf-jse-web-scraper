package ideam.df.jsescraper.config;

import com.zaxxer.hikari.HikariDataSource;
import ideam.df.jsescraper.config.properties.JseScraperDbProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories("ideam.df.jsescraper.repository")
@EnableTransactionManagement
public class AppDataSourceConfig {

    @Autowired
    private JseScraperDbProperties scraperDbProps;

   @Profile("prod")
    @Bean(name="dataSourceProd")
    @Qualifier("app-ds")
    public DataSource dataSourceProd() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(scraperDbProps.getDriverClassName());
        dataSource.setUrl(scraperDbProps.getUrl());
        dataSource.setUsername(scraperDbProps.getUsername());
        dataSource.setPassword(scraperDbProps.getPassword());
        return dataSource;
    }

/*
    @Profile("prod")
    @Bean(name="dataSourceProd")
    @Qualifier("app-ds")
    @ConfigurationProperties(prefix="scraper.db")
    public DataSource dataSourceProd() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
*/

    @Profile("dev")
    @Bean(name="dataSourceDev")
    @Qualifier("app-ds")
    public DataSource dataSourceDev() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dataplatform");
        dataSource.setUsername("root");
        dataSource.setPassword("Password1");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory( @Qualifier("app-ds")DataSource ds) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds);
        em.setPackagesToScan(new String[] { "ideam.df.jsescraper.model" });
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.jdbc.batch_size","50");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
