package com.google.loader.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@PropertySource(value = "classpath:/redis.properties")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class SessionConfig {
    @Value("${spring.redis.database}")
    private String database;

    @Value("${spring.redis.host}")
    private String HostName;

    @Value("${spring.redis.port}")
    private int Port;

    @Value("${spring.redis.password}")
    private String Password;

    @Bean(name="AuthRedisFactory")
    @Primary
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setDatabase(Integer.valueOf(database));
        connection.setPort(Port);
        connection.setHostName(HostName);
        connection.setPassword(Password);
        return connection;
    }
}
