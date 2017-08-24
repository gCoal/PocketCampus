package com.google.loader;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
public class db {
    @Value("${pg.datasource.url}") String url;
    @Value("${pg.datasource.username}") String username;
    @Value("${pg.datasource.password}") String password;
    @Value("${pg.datasource.driver}") String driver;
    @Value("${pg.datasource.maxActive}") int maxActive;

    @Bean(name = "lupdp")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean SessionFactoryBean=new LocalSessionFactoryBean();
        SessionFactoryBean.setDataSource(dataSource());
        SessionFactoryBean.setPackagesToScan("com.google.entity");

        Properties properties=new Properties();
        properties.setProperty("hibernate.current_session_context_class","thread");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("hibernate.connection.url",url);
        properties.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");

        SessionFactoryBean.setHibernateProperties(properties);
        try {
            SessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SessionFactoryBean.getObject();

    }
}
