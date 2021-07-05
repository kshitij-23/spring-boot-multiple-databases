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
@EnableJpaRepositories(basePackages = "com.ksh.springbootmultipledatabases.repositories.mysql",
        entityManagerFactoryRef = "mysqlEntityManager",
        transactionManagerRef = "mysqlTransactionManager")
public class MySQLConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("mysqlDataSource")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSource getMySQLDataSource() {
        System.out.println("Initialised Mysql Datasource.");
        return mysqlDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "mysqlEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("mysqlDataSource") DataSource dataSource,
                                                                       EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.ksh.springbootmultipledatabases.entities.mysql")
                .persistenceUnit("test")
                .build();
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManager") EntityManagerFactory mysqlEntityManager) {
        return new JpaTransactionManager(mysqlEntityManager);
    }
}
