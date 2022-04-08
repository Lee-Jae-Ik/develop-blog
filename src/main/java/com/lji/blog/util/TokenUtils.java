package com.lji.blog.util;

import com.lji.blog.model.schema.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TokenUtils
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@RequiredArgsConstructor
@Service
public class TokenUtils {

    private final String SECRET_KEY = "secretkey";

    private final String REFRESH_KEY = "refreshkey";

    private final String DATA_KEY = "id";

    public String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserId())
                .setHeader(this.createHeader())
                .setClaims(this.createClaims(user))
                .setExpiration(this.createExpireDate(1000 * 60 * 5))
                .signWith(SignatureAlgorithm.HS256,createSigningKey(SECRET_KEY))
                .compact();
    }

    public String saveRefreshToken(User user) { // Refresh Token 생성
        return Jwts.builder()
                .setSubject(user.getUserId())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDate(1000 * 60 * 10))// Access Token 확인을 위해 길게 생성
                .signWith(SignatureAlgorithm.HS256, createSigningKey(REFRESH_KEY))
                .compact();
    }

    public boolean isValidToken(String token) { //Token 유효성 체크
        try {
            Claims accessClaims = getClaimsFormToken(token);
            return true;
        } catch (ExpiredJwtException exception) {
            return false;
        } catch (JwtException exception) {
            return false;
        } catch (NullPointerException exception) {
            return false;
        }
    }
    public boolean isValidRefreshToken(String token) { // Refresh Token 유효성 체크
        try {
            Claims accessClaims = getClaimsToken(token);
            return true;
        } catch (ExpiredJwtException exception) {
            return false;
        } catch (JwtException exception) {
            return false;
        } catch (NullPointerException exception) {
            return false;
        }
    }

    private Map<String, Object> createHeader() { // Token 생성 시 Header 부분을 저장
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "ACCESS_TOKEN");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Map<String, Object> createClaims(User user) { // Token 생성 시 Payload 부분을 저장
        Map<String, Object> claims = new HashMap<>();
        claims.put(DATA_KEY, user.getId());
        return claims;
    }

    private Date createExpireDate(long expireDate) { // 만료일 생성
        long curTime = System.currentTimeMillis();
        return new Date(curTime + expireDate);
    }

    private Key createSigningKey(String key) { // 해당 Key로 암호화
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Claims getClaimsFormToken(String token) { // 유효성 검색을 위해 token 정보를 읽어온다.
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
    private Claims getClaimsToken(String token) { // 유효성 검색을 위해 token 정보를 읽어온다.
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(REFRESH_KEY))
                .parseClaimsJws(token)
                .getBody();
    }

}
