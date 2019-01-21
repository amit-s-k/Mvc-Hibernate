package com.stackroute.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "com.stackroute")
public class HibernateConfig {
    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setPackagesToScan("com.stackroute.model");
        return sessionFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties=new Properties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/userprofile");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");
        return dataSource;
    }
    @Bean
    @Autowired
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
