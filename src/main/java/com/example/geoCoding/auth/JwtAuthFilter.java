package com.example.geoCoding.auth;

import com.example.geoCoding.templateClass.DataDecryption;
import com.example.geoCoding.exceptionHandling.BadRequestException;
import com.example.geoCoding.service.CompanyService;
import com.example.geoCoding.service.ResponseLogService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.KeyFactory;

@Component
@Slf4j

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ResponseLogService responseLogService;

//    @Value("${PRIVATE_KEY}")
//    private String privateString;

    @Autowired
    private DataDecryption dataDecryption;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//        System.out.println( SecurityContextHolder.getContext().getAuthentication().toString()+"yesssss");
        String apiKeyString;
        byte[] apiKey;

        if (request.getRequestURI().equals("/shop/find")) {
            KeyFactory keyFactory = null;

            String decryptedString = dataDecryption.decryption(request);
//                  keyFactory = KeyFactory.getInstance("RSA");
//                  apiKeyString = (request.getHeader("Authorization"));
//                  apiKey = Base64.getDecoder().decode(apiKeyString.getBytes(StandardCharsets.ISO_8859_1));
//                  byte[] privateData = Base64.getDecoder().decode((privateString));
//                  PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(privateData);
//                  PrivateKey privateKey = null;
//                  privateKey = keyFactory.generatePrivate(spec2);
////                  keyFactory = KeyFactory.getInstance("RSA");
//                  Cipher cipher = null;
//                  cipher = Cipher.getInstance("RSA");
//                  cipher.init(Cipher.DECRYPT_MODE, privateKey);
////              cipher.update(apiKey);
//                  byte[] decrypted;
//                  decrypted = cipher.doFinal(apiKey);
//                  System.out.println("decrypted: " + new String(decrypted));
//
//                  String test=new String(decrypted);
            if (!responseLogService.isSubscriptionValid(decryptedString.split("_")[0])) {
                throw new BadRequestException("request limit exceeded");
            }
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String companyName;
        System.out.println("do something");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        System.out.println("Authorized");
        companyName = jwtService.extractUserName(jwt);

        if (companyName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = companyService.loadByCompanyName(companyName);
            if (jwtService.isTokenValid(jwt, companyName)) {

                log.info(jwt);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails
                        , null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println(SecurityContextHolder.getContext());
                System.out.println(authToken);
            }

            String val = request.getHeader("Authorization");

            System.out.println("anssssss");
            String cuttedString = val.substring(7);
            System.out.println("before");
            Claims claims = jwtService.extractAllClaims(cuttedString);

            String subscriptionId = claims.get("subscriptionId").toString();

            if (!responseLogService.isSubscriptionValid(subscriptionId)) {
                throw new BadRequestException("request limit exceeded");
            }
            System.out.println("after");
        }
        filterChain.doFilter(request, response);

    }
}


