package com.ksh.springbootmultipledatabases.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ksh.springbootmultipledatabases.repositories.postgre",
        entityManagerFactoryRef = "postgreEntityManager",
        transactionManagerRef = "postgreTransactionManager")
public class PostGreConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.postgre")
    public DataSourceProperties postgreDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("postgreDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgre")
    public DataSource getPostGreDataSource() {
        System.out.println("Initialised Postgre Datasource.");
        return postgreDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "postgreEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("postgreDataSource") DataSource dataSource,
                                                                       EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.ksh.springbootmultipledatabases.entities.postgre")
                .persistenceUnit("test_db")
                .build();
    }

    @Bean(name = "postgreTransactionManager")
    public PlatformTransactionManager postgreTransactionManager(@Qualifier("postgreEntityManager") EntityManagerFactory postgreEntityManager) {
        return new JpaTransactionManager(postgreEntityManager);
    }

}
