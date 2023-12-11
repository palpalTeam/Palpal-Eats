package com.sparta.palpaleats.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            // 유효하지 않은 토큰
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_INVALID_SIGNATURE);
        } catch (ExpiredJwtException e) {
            // 유효기간 만료이 만료된 토큰
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 토큰
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            // 잘못된 토큰
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_INVALID);
        } catch (NullPointerException e) {
            // 토큰이 없음
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_IS_NULL);
        } catch (JwtException e) {
            // 토큰 처리 중 오류 발생 (포괄적)
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_GENERIC_ERROR);
        }
    }

    private void setErrorResponse(
            HttpServletResponse response,
            ExceptionCode exceptionCode
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(exceptionCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        ErrorResponse errorResponse = new ErrorResponse(exceptionCode.getHttpStatus().value(),
                exceptionCode.getMessage());
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    public static class ErrorResponse {

        private final Integer status;
        private final String msg;
    }
}