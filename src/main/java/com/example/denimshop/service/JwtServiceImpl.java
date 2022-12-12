package com.example.denimshop.service;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;


@Service
public class JwtServiceImpl implements JwtService {
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String getToken(String key, Object value) {
        LocalDateTime expTime = LocalDateTime.now().plusSeconds(3600 * 5);

        Key signKey = new SecretKeySpec(secretKey.getEncoded(), SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder jwtBuilder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(Timestamp.valueOf(expTime))
                .signWith(signKey, SignatureAlgorithm.HS256);

        return jwtBuilder.compact();
    }

    @Override
    public Optional<Claims> getClaims(String token) {
        try {
            isTokenBlank(token);
            Key signKey = new SecretKeySpec(secretKey.getEncoded(), HS512.getJcaName());
            return Optional.of(
                    Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody()
            );
        } catch (JwtException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isValidToken(String token) {
        return getClaims(token).isPresent();
    }

    @Override
    public long getId(String token) {
        Optional<Claims> optionalClaims = getClaims(token);
        if (optionalClaims.isEmpty()) {
            return 0;
        }
        return Long.parseLong(optionalClaims.get().get("id").toString());
    }

    private void isTokenBlank(String token) {
        if (token == null || "".equals(token)) {
            throw new MalformedJwtException("토큰이 없습니다.");
        }
    }
}
