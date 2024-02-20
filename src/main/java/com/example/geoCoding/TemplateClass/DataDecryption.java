package com.example.geoCoding.TemplateClass;


import com.example.geoCoding.exceptionHandling.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
@Data
public class DataDecryption {



    @Value(value = "${PRIVATE_KEY}")
    private String privateString;

    public String decryption(HttpServletRequest request)  {

        byte[] decrypted=null;
        KeyFactory keyFactory = null;
        String apiKeyString="";
        byte[] apiKey;

        try
        {
        keyFactory = KeyFactory.getInstance("RSA");
        apiKeyString = (request.getHeader("Authorization"));
        apiKey = Base64.getDecoder().decode(apiKeyString.getBytes(StandardCharsets.ISO_8859_1));
        byte[] privateData = Base64.getDecoder().decode((privateString));
        PKCS8EncodedKeySpec spec2 = new PKCS8EncodedKeySpec(privateData);
        PrivateKey privateKey = null;
        privateKey = keyFactory.generatePrivate(spec2);
//                  keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = null;
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//              cipher.update(apiKey);

        decrypted = cipher.doFinal(apiKey);
        System.out.println("decrypted: " + new String(decrypted));


    } catch (IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException
                     | NoSuchAlgorithmException|InvalidKeySpecException | BadPaddingException e) {
        throw new BadRequestException(e.toString());
    }

            System.out.println("decrypted: " + new String(decrypted));

    String ans = new String(decrypted);
//

        return ans;
    }
}
