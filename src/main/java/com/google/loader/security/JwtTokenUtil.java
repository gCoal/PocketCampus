package com.google.loader.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_PASSWORD = "password";
    private static final String CLAIM_KEY_CREATED = "created";

    //private static final String AUDIENCE_UNKNOWN = "unknown";
    //private static final String AUDIENCE_WEB = "web";
    //private static final String AUDIENCE_MOBILE = "mobile";
    //private static final String AUDIENCE_TABLET = "tablet";

    //通过@value注解获取密钥
    @Value("${jwt.secret:ddzx666}")
    private String secret;

    //通过@value注解获取失效时间
    @Value("${jwt.expiration:7200}")
    private Long expiration;

    @Resource(name="AuthRedisTemplate")
    private RedisTemplate authRedisTemplate;

    /**
     * @param token
     * return username by Token
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_USERNAME);
            //username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @param token
     * return CreatedDate by Token 获取token创建时间
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }
    /**
     * @param token
     * return ExpirationDate by Token  获取token过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
    /**
     * @param token
     * return Password by Token  获取token的密码
     */
    public String getPasswordFromToken(String token) {
        String password;
        try {
            final Claims claims = getClaimsFromToken(token);
            password = (String) claims.get(CLAIM_KEY_PASSWORD);
        } catch (Exception e) {
            password = null;
        }
        return password;
    }
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    //设置签名的密钥
                    .setSigningKey(secret)
                    //把token转换成断言
                    .parseClaimsJws(token)
                    .getBody();
            Date expirationTime = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();

        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    //生成到期日期
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    //判断token是否过期
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    //判断token创建时间是否在密码重置之前
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /*private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }
    //忽略token的过期时间
    private Boolean ignoreTokenExpiration(String token) {
        String audience = getPasswordFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }*/

    //生成token
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    //判断token是否可以刷新
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token)) ;
                /*|| ignoreTokenExpiration(token))*/
    }
    //刷新token
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
    //判读token是否有效
    public  Boolean validateToken(String token, UserDetails userDetails) {
//        JwtUser customer = (JwtUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
//        //final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(customer.getUsername())
//                        && !isTokenExpired(token)
//                        && !isCreatedBeforeLastPasswordReset(created, customer.getLastPasswordResetDate()));
        return false;
    }

    boolean validateTokenByRedis(String token) {
        String username = getUsernameFromToken(token);
        //根据token中的usernama从redis中获取token
        String jwt=authRedisTemplate.opsForValue().get(username).toString();

        //判断客户端传来的token和redis缓存的token是否一致
        return jwt != null && jwt.equals(token);
    }
}