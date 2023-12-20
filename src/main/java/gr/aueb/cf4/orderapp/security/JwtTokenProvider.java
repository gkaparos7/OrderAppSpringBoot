package gr.aueb.cf4.orderapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import java.util.function.Function;

@Component
public class JwtTokenProvider {
    private final String jwtSecret;
    private final SecretKey secretKey;

    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
        this.secretKey = generateSecretKey(jwtSecret);
    }


    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
        // Log the token payload
        System.out.println("Token Payload: " + Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody());

        return token;
    }

    private SecretKey generateSecretKey(String keyString) {
        try {
            // Use SHA-512 to generate a hash of the key string
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte[] keyBytes = sha512.digest(keyString.getBytes(StandardCharsets.UTF_8));

            // Use the first 512 bits (64 bytes) as the key
            byte[] truncatedKey = new byte[64];
            System.arraycopy(keyBytes, 0, truncatedKey, 0, 64);

            return Keys.hmacShaKeyFor(truncatedKey);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception, for example, log an error
            throw new RuntimeException("Error generating secret key", e);
        }
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // The token is not valid
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String extractUsername(String token) {
        return getUsernameFromToken(token);
    }
}

