package com.example.denimshop.service;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtServiceImpl implements JwtService {
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Override
    public String getToken(String key, Object value) {
//        LocalDateTime expTime = LocalDateTime.now().plusSeconds(60 * 5);

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 5);

        byte[] secretByteKey = secretKey.getEncoded(); //DatatypeConverter.parseBase64Binary(secretKey.toString());
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder jwtBuilder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return jwtBuilder.compact();
    }
}
