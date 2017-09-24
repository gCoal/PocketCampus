package com.google.loader.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.Resource;

@Configuration
@PropertySource(value = "classpath:/redis.properties")
@EnableCaching
@EnableRedisHttpSession
public class RedisControl extends CachingConfigurerSupport {

    @Resource(name="AuthRedisFactory")
    private JedisConnectionFactory factory;

    //主键?
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }

    //类似于JedisPool
//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory();
//        factory=lettuceConnectionFactory;
//        return lettuceConnectionFactory;
//    }



    //Jedis客户端
    @Bean(name="AuthRedisTemplate")
    public RedisTemplate<String, String> AuthRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }



//    //原始办法手动生成
//    private static JedisPool jedisPool;
//    private static Jedis jedis;
//
//
//    private String maxtotal;
//
//    @PostConstruct
//    public void init() throws Exception{
//        if(jedisPool==null){
//            try {
//                JedisPoolConfig config = new JedisPoolConfig();
//                config.setMaxTotal(JedisConf.getInt("maxtotal"));
//                config.setMaxIdle(JedisConf.getInt("maxidle"));
//                config.setMaxWaitMillis(JedisConf.getLong("maxwaitmillis"));
//                config.setTestOnBorrow(JedisConf.getBoolean("testonborrow"));
//
//                String host=JedisConf.getString("host");
//                int port=JedisConf.getInt("port");
//                int timeout=JedisConf.getInt("timeout");
//                String password=JedisConf.getString("password");
//                int database=JedisConf.getInt("database");
//
//                jedisPool=new JedisPool(config,host,port,timeout,password,database);
//            } catch (SQLException e) {
//                Log.error("initDB发生异常");
//                throw e;
//            }
//        }
//    }
//
//    public Jedis getJedis(){
//        if(jedisPool!=null){
//            jedis=jedisPool.getResource();
//            return jedis;
//        }else{
//            return null;
//        }
//    }
//
//    public void closeJedis(){
//        if(jedis!=null)
//        {
//            jedis.close();
//        }
//    }

}
