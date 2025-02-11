package com.mall_management.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtToken {
    private static final String CLAIM_KEY_USERNAME="sub";   //定义静态用户名
    private static final String CLAIM_KEY_CREATED="created";//定义静态生成token的时间

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(); // 定义一个荷载，用于存储用户名和 Token 的生成时间
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateTokenByClaims(claims);
    }

    //    根据荷载JWT生成token
    public String generateTokenByClaims(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expiration*1000)) //存储失效时间
                .signWith(SignatureAlgorithm.HS256,secret) //使用算法，和密钥
                .compact();
    }

    //    从token中获取登录名
    public String getUserNameByToken(String token){
        String userName;
        try{
            Claims claims=getClaimsFromToken(token);
            userName=claims.getSubject();
        }catch (Exception e){
            userName=null;
        }
        return userName;
    }

    //    刷新token
    public String refreshToken(String token){
        Claims claims=getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateTokenByClaims(claims);
    }

    //    验证token是否有效
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameByToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    //    判断token是否可以被刷新
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }



    //    验证token是否失效
    private boolean isTokenExpired(String token) {
        Date expireDate=getClaimsFromToken(token).getExpiration();
        return  expireDate.before(new Date());
    }
    //    从token中获取失效时间
    private Date getExpiredDateFromToken(String token) {
        Claims claims=getClaimsFromToken(token); //从token中获取荷载，荷载中存储了失效时间
        return claims.getExpiration();
    }

    //    从token中获取荷载
    private Claims getClaimsFromToken(String token) {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }
}
