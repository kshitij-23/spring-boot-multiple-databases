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
@EnableJpaRepositories(basePackages = "com.ksh.springbootmultipledatabases.repositories.h2",
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager")
public class H2Configuration {

    @Bean
    @ConfigurationProperties("spring.datasource.h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("h2DataSource")
    @ConfigurationProperties(prefix="spring.datasource.h2")
    public DataSource getH2DataSource() {
        System.out.println("Initialised H2 Datasource.");
        return h2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "h2EntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("h2DataSource") DataSource dataSource,
                                                                       EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.ksh.springbootmultipledatabases.entities.h2")
                .persistenceUnit("testdb")
                .build();
    }

    @Bean(name = "h2TransactionManager")
    public PlatformTransactionManager h2TransactionManager(@Qualifier("h2EntityManager") EntityManagerFactory h2EntityManager) {
        return new JpaTransactionManager(h2EntityManager);
    }
}
