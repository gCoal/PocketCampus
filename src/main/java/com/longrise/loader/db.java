package com.longrise.loader;

import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
public class db {
    @Bean(name="statFilter")
    public StatFilter StatFilter(){
        StatFilter statFilter=new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000);
        statFilter.setLogSlowSql(true);
        return statFilter;
    }

    @Bean(name="logFilter")
    public Log4jFilter LogFilter(){
        Log4jFilter log4jFilter=new Log4jFilter();
        log4jFilter.setResultSetLogEnabled(true);
        log4jFilter.setStatementLogEnabled(true);
        log4jFilter.setStatementExecutableSqlLogEnable(true);
        return log4jFilter;
    }

    @Value("${pg.datasource.url}") String url;
    @Value("${pg.datasource.username}") String username;
    @Value("${pg.datasource.password}") String password;
    @Value("${pg.datasource.driver}") String driver;
    @Value("${pg.datasource.maxActive}") int maxActive;

    private DruidDataSource JerseyInitDB;
    private DruidDataSource lupdp;
    private DruidDataSource LSIP_train;

    @Bean(name = "JerseyInitDB")
    @Qualifier("JerseyInitDB")
    @Primary
    public DruidDataSource getJerseyInitDB() {
        if(JerseyInitDB==null){
            JerseyInitDB = new DruidDataSource();
            JerseyInitDB.setDriverClassName(driver);
            JerseyInitDB.setUrl(url);
            JerseyInitDB.setUsername(username);
            JerseyInitDB.setPassword(password);
            JerseyInitDB.setMaxActive(maxActive);
            return JerseyInitDB;
        }else {
            return JerseyInitDB;
        }

    }

    @Bean(name = "lupdp")
    @Qualifier("lupdp")
    public DruidDataSource lupdp() {
        if(lupdp==null){
            lupdp = new DruidDataSource();
            lupdp.setDriverClassName(driver);
            lupdp.setUrl("jdbc:postgresql://127.0.0.1:5432/LUPDP_TEST");
            lupdp.setUsername(username);
            lupdp.setPassword(password);
            lupdp.setMaxActive(maxActive);
            return lupdp;
        }else {
            return lupdp;
        }
    }

    @Bean(name = "LSIP_train")
    @Qualifier("LSIP_train")
    public DruidDataSource LSIP_train() {
        if(LSIP_train==null){
            LSIP_train = new DruidDataSource();
            LSIP_train.setDriverClassName(driver);
            LSIP_train.setUrl("jdbc:postgresql://127.0.0.1:5432/LSIPTEST_train");
            LSIP_train.setUsername(username);
            LSIP_train.setPassword(password);
            LSIP_train.setMaxActive(maxActive);
            return LSIP_train;
        }else {
            return LSIP_train;
        }
    }

//    @Bean(name = "lupdp")
//    @Qualifier("lupdp")
//    public DbFactory lupdp() {
//        DbFactory dbFactory=new DbFactory();
//        dbFactory.setProject("lupdp");
//        dbFactory.setJerseyInitDB(getJerseyInitDB());
//        dbFactory.setLogFilter(LogFilter());
//        dbFactory.setStatFilter(StatFilter());
//        try {
//            dbFactory.create();
//            return dbFactory;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Bean(name = "lupdpSF")
    @Primary
    public SessionFactory lupdpSF(){
        LocalSessionFactoryBean SessionFactoryBean=new LocalSessionFactoryBean();
        try {
            SessionFactoryBean.setDataSource(lupdp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SessionFactoryBean.setPackagesToScan("com.longrise.entity");

        Properties properties=new Properties();
        properties.setProperty("hibernate.current_session_context_class","thread");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("hibernate.connection.url","jdbc:postgresql://127.0.0.1:5432/LUPDP_TEST");
        properties.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");

        SessionFactoryBean.setHibernateProperties(properties);
        try {
            SessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SessionFactoryBean.getObject();
    }


    @Bean(name = "trainSF")
    public SessionFactory trainSF(){
        LocalSessionFactoryBean SessionFactoryBean=new LocalSessionFactoryBean();
        try {
            SessionFactoryBean.setDataSource(LSIP_train);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SessionFactoryBean.setPackagesToScan("com.longrise.entity");

        Properties properties=new Properties();
        properties.setProperty("hibernate.current_session_context_class","thread");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("hibernate.connection.url","jdbc:postgresql://127.0.0.1:5432/LSIPTEST_train");
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