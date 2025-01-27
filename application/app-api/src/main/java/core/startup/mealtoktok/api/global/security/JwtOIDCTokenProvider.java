package core.startup.mealtoktok.api.global.security;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import core.startup.mealtoktok.api.auth.exception.ExpiredTokenException;
import core.startup.mealtoktok.domain.auth.OIDCPayload;
import core.startup.mealtoktok.domain.auth.OidcTokenParser;
import core.startup.mealtoktok.domain.auth.exception.OidcTokenInvalidException;

import io.jsonwebtoken.*;

@Slf4j
@Component
public class JwtOIDCTokenProvider implements OidcTokenParser {

    private final String KID = "kid";

    public String getKid(String token, String iss, String aud) {
        return (String) getUnsignedTokenClaims(token, iss, aud).getHeader().get(KID);
    }

    public OIDCPayload getPayload(String token, String modulus, String exponent) {
        Claims body = getOIDCTokenJws(token, modulus, exponent).getBody();
        return new OIDCPayload(
                body.getIssuer(),
                body.getAudience(),
                body.getSubject(),
                body.get("email", String.class));
    }

    private Jws<Claims> getOIDCTokenJws(String token, String modulus, String exponent) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getRSAPublicKey(modulus, exponent))
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            log.error(e.toString());
            throw OidcTokenInvalidException.EXCEPTION;
        }
    }

    private Jwt<Header, Claims> getUnsignedTokenClaims(String token, String iss, String aud) {
        try {
            return Jwts.parserBuilder()
                    .requireAudience(aud)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(getUnsignedToken(token));
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            log.error(e.toString());
            throw OidcTokenInvalidException.EXCEPTION;
        }
    }

    private String getUnsignedToken(String token) {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) {
            throw OidcTokenInvalidException.EXCEPTION;
        }
        return splitToken[0] + "." + splitToken[1] + ".";
    }

    private Key getRSAPublicKey(String modulus, String exponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }
}
