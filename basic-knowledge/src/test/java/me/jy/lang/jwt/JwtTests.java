package me.jy.lang.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author jy
 */
public class JwtTests {

    @Test
    public void helloWorld() throws Exception {

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload("{\"exp\":1541584668}"));

        byte[] sharedKey = new byte[32];
        ThreadLocalRandom.current().nextBytes(sharedKey);

        String key = "youdonotknownwhatdoesitmean->_<-";
        jwsObject.sign(new MACSigner(key));

        // eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDE1ODQ2Njh9.uCFS9LVh1-FhLA5lGOwwVrTGdQqukdDxMjBN4HlN3oE
        System.out.println(jwsObject.serialize());

    }
}
