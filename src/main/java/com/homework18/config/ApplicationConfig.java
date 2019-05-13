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
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dll;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName(driver);
        managerDataSource.setUrl(url);
        managerDataSource.setUsername(login);
        managerDataSource.setPassword(password);
        return managerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource());
        managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        managerFactoryBean.setJpaProperties(jpaProperties());
        managerFactoryBean.setPackagesToScan("com.homework18.model");
        return managerFactoryBean;
    }

    public Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", hibernateDialect);
        jpaProperties.setProperty("hibernate.show_sql", showSql);
        jpaProperties.setProperty("hibernate.format_sql", formatSql);
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", hbm2dll);
        return jpaProperties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public JavaMailSender javaMailSender(){
        return new JavaMailSenderImpl();
    }
}
