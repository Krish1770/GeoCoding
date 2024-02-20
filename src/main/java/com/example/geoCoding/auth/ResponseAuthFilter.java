package com.example.geoCoding.auth;

import com.example.geoCoding.DTO.LogDto;
import com.example.geoCoding.TemplateClass.DataDecryption;
import com.example.geoCoding.exceptionHandling.BadRequestException;
import com.example.geoCoding.service.ResponseLogService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;


@Component
public class ResponseAuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

//    @Value("${PRIVATE_KEY}")
//    private String privateString;

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
        ArrayList<String>arr=new ArrayList<>();
       arr.add("/company/login");
        arr.add("/subscription/add");
        arr.add("/plan/add");
        arr.add("/shop/add");
//        arr.add("/shop/find");
        return arr.contains(path);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("/shop/find");
        System.out.println("hi");
        String subscriptionId="";
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        String val = request.getHeader("Authorization");
        byte[] decrypted;
        if(!arrayList.contains(request.getRequestURI())) {
            String cuttedString = val.substring(7);
            Claims claims = jwtService.extractAllClaims(cuttedString);

             subscriptionId = claims.get("subscriptionId").toString();
        }

        else {
              String decryptedString= dataDecryption.decryption(request);
//            String apiKeyString;
//            byte[] apiKey;
//            KeyFactory keyFactory = null;
//
//            try {
//                keyFactory = KeyFactory.getInstance("RSA");
//                apiKeyString = (request.getHeader("Authorization"));
//                apiKey = Base64.getDecoder().decode(apiKeyString.getBytes(StandardCharsets.ISO_8859_1));
//                byte[] privateData = Base64.getDecoder().decode((privateString));
//                PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(privateData);
//                PrivateKey privateKey = null;
//                privateKey = keyFactory.generatePrivate(spec2);
////                  keyFactory = KeyFactory.getInstance("RSA");
//                Cipher cipher = null;
//                cipher = Cipher.getInstance("RSA");
//                cipher.init(Cipher.DECRYPT_MODE, privateKey);
////              cipher.update(apiKey);
//
//                decrypted = cipher.doFinal(apiKey);
//                System.out.println("decrypted: " + new String(decrypted));
////                filterChain.doFilter(request, response);
//
//            } catch (IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException
//                     | NoSuchAlgorithmException|InvalidKeySpecException | BadPaddingException e) {
//                throw new BadRequestException(e.toString());
//            }
//
//            System.out.println("decrypted: " + new String(decrypted));
//
//                String ans = new String(decrypted);
//         ans=ans.substring(63);

                 subscriptionId=decryptedString.replaceAll(" ","").split("_")[0];
            }
//        System.out.println(val.substring(7)+"qwertyuu");
            filterChain.doFilter(requestWrapper, responseWrapper);

            String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
            String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
//
            LogDto logDto = new LogDto();
//
//
            System.out.println("subscriptionId" + subscriptionId);
            logDto.setMethod(request.getMethod());
            logDto.setRequestURI(request.getRequestURI());
            logDto.setResponseBody(responseBody);
            logDto.setRequestBody(requestBody);
            logDto.setStatus(response.getStatus());

            responseLogService.add(logDto, subscriptionId);

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
