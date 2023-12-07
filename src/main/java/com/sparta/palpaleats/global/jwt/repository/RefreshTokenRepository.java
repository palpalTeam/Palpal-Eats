package com.sparta.palpaleats.global.jwt.repository;

import com.sparta.palpaleats.global.jwt.JwtUtil;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveRefreshToken(String username, String refreshToken) {
        String value = refreshToken.substring(7);
        long time = JwtUtil.REFRESH_TOKEN_TIME;

        redisTemplate.opsForValue().set(username, value, time, TimeUnit.MILLISECONDS);
    }

    public boolean existsByUsername(String username) {

        String refreshToken = redisTemplate.opsForValue().get(username);
        return refreshToken != null && !refreshToken.isEmpty();
    }
}
