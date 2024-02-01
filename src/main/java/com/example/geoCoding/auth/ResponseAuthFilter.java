package com.example.geoCoding.auth;

import com.example.geoCoding.DTO.LogDto;
import com.example.geoCoding.DTO.PayloadDto;
import com.example.geoCoding.model.ResponseLog;
import com.example.geoCoding.service.ResponseLogService;
import com.nimbusds.jose.shaded.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


@Configuration
public class ResponseAuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);


    @Autowired
    private ResponseLogService responseLogService;

    @Autowired
    private JwtService jwtService;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
    {
        String path=request.getRequestURI();
        System.out.println(path);
        return "/company/login".equals(path);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {



        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
String val=request.getHeader("Authorization");

        System.out.println("anssssss");

        String cuttedString=val.substring(7);
//        JsonParser jsonParser= JsonParserFactory.getJsonParser();
//        Map<String,?> tokenData=jsonParser.parseMap(.d);

//        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor("eyJhbGciOiJIUzI1NiJ9eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwNjUyNDE4MiwiaWF0IjoxNzA2NTI0MTgyfQJfhY1a3tmN3UT99ORZw8ZVIRjMHCt2oNXIgH6PbKY".getBytes()))
//                .build().
//                parseClaimsJws(cuttedString);
//        System.out.println(claimsJws.getBody());
//        System.out.println(claimsJws.getBody().get("subscriptionId"));
//        Base64.Decoder decoder = Base64.getUrlDecoder();
// String[] chunks=cuttedString.split("\\.");
//        String header = new String(decoder.decode(chunks[0]));
//        String payload = new String(decoder.decode(chunks[1]));
//        System.out.println(payload);
//        Gson gson = new Gson();
//        PayloadDto payloadDto=gson.fromJson(payload,PayloadDto.class);
//        System.out.println(payloadDto.getSubscriptionId());

       Claims claims= jwtService.extractAllClaims(cuttedString);

       String subscriptionId=claims.get("subscriptionId").toString();


//        System.out.println(val.substring(7)+"qwertyuu");
        filterChain.doFilter(requestWrapper, responseWrapper);

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
//
        LogDto logDto=new LogDto();
//
//
      logDto.setMethod(request.getMethod());
        logDto .setRequestURI(request.getRequestURI());
        logDto.setResponseBody(responseBody);
        logDto.setRequestBody(requestBody);
//        logDto.setSubscriptionId(payloadDto.getSubscriptionId());
        logDto.setStatus(response.getStatus());
//
        responseLogService.add(logDto,subscriptionId);

//        System.out.println(responseLog);



//        logger.info(
//                "FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}",
//                request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody
//                );
        responseWrapper.copyBodyToResponse();


    }

    private String getStringValue(byte[] contentsAsByteArray, String characterEncoding) throws UnsupportedEncodingException {


        return new String(contentsAsByteArray, 0, contentsAsByteArray.length, characterEncoding);

    }


}
