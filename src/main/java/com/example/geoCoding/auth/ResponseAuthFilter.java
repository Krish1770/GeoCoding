package com.example.geoCoding.auth;

import com.example.geoCoding.dTO.LogDto;

import com.example.geoCoding.service.ResponseLogService;
import com.example.geoCoding.templateClass.DataDecryption;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


@Component
public class ResponseAuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private DataDecryption dataDecryption;
    @Autowired
    private ResponseLogService responseLogService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        System.out.println(path);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("/company/login");
        arr.add("/subscription/add");
        arr.add("/plan/add");
        arr.add("/shop/add");
//        arr.add("/shop/find");
        return arr.contains(path);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("/shop/find");
        System.out.println("hi");
        String subscriptionId = "";
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        String val = request.getHeader("Authorization");
        byte[] decrypted;
        if (!arrayList.contains(request.getRequestURI())) {
            String cuttedString = val.substring(7);
            Claims claims = jwtService.extractAllClaims(cuttedString);

            subscriptionId = claims.get("subscriptionId").toString();
        } else {
            String decryptedString = dataDecryption.decryption(request);


            subscriptionId = decryptedString.replaceAll(" ", "").split("_")[0];
        }
        filterChain.doFilter(requestWrapper, responseWrapper);

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());

        LogDto logDto = new LogDto();

        logDto.setMethod(request.getMethod());
        logDto.setRequestURI(request.getRequestURI());
        logDto.setResponseBody(responseBody);
        logDto.setRequestBody(requestBody);
        logDto.setStatus(response.getStatus());

        responseLogService.add(logDto, subscriptionId);

        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentsAsByteArray, String characterEncoding) throws UnsupportedEncodingException {
        return new String(contentsAsByteArray, 0, contentsAsByteArray.length, characterEncoding);

    }

}
