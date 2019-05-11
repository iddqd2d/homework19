package com.homework18.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.homework18.repository")
@ComponentScan("com.homework18")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Value("${driver}")
    private String DRIVER;

    @Value("${url}")
    private String URL;

    @Value("${login}")
    private String USER_NAME;

    @Value("${password}")
    private String PASSWORD;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL;

    @Value("${hibernate.format_sql}")
    private String FORMAT_SQL;

    @Bean
    DriverManagerDataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(DRIVER);
        managerDataSource.setUrl(URL);
        managerDataSource.setUsername(USER_NAME);
        managerDataSource.setPassword(PASSWORD);
        return managerDataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource());
        managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        managerFactoryBean.setJpaProperties(jpaProperties());
        managerFactoryBean.setPackagesToScan("com.homework18.model");
        return managerFactoryBean;
    }

    Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        jpaProperties.setProperty("hibernate.show_sql", SHOW_SQL);
        jpaProperties.setProperty("hibernate.format_sql", FORMAT_SQL);
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", HBM2DDL);
        return jpaProperties;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }
}