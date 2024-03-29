package com.kimilm.expert.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kimilm.expert.security.UserDetailsImpl;

import java.util.Date;

public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final Long JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000L;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String JWT_SECRET = "jwt_secret_!@#$%";
    public static final String AUTH_TYPE = "Bearer ";

    public static String generateJwtToken(UserDetailsImpl userDetails) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("sparta")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return AUTH_TYPE + token;
    }

    // 여기에 Refrash Token 생성
    // Refresh Token은 DB에 저장됨
    public static String generateRefreshToken(UserDetailsImpl userDetails) {
        // 구현 자체는 엑세스 토큰과 동일, 서버에 저장될뿐임
        // 레디스 서버에 저장한 조가 있었음
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("sparta")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간), 30일
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC * 10))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return AUTH_TYPE + token;
    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
