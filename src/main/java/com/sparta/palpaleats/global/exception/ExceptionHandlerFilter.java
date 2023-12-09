package com.sparta.palpaleats.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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

        try{
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            //토큰의 유효기간 만료
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_EXPIRED);
        }catch (JwtException | IllegalArgumentException e){
            //유효하지 않은 토큰
            setErrorResponse(response, ExceptionCode.UNAUTHORIZED_TOKEN_INVALID);
        }
    }
    private void setErrorResponse(
            HttpServletResponse response,
            ExceptionCode exceptionCode
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(exceptionCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = new ErrorResponse(exceptionCode.getHttpStatus().value(), exceptionCode.getMessage());
        try{
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Data
    public static class ErrorResponse{
        private final Integer code;
        private final String message;
    }
}