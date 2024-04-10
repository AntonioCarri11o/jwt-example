package mx.edu.utex.jwt.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
@Component
public class JwtService {
    @Autowired
    UserAuthService authService;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES", authService.loadUserByUsername(username).getAuthorities());
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> clamis, String username) {
        return Jwts.builder()
                .setClaims(clamis)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getStartKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getStartKey() {
        byte[] key = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }

    public String getUsername(String token) {
        return getClamis(token, Claims::getSubject);
    }

    public Date getExpiration(String token) {
        return getClamis(token, Claims::getExpiration);
    }

    public <T> T getClamis(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getStartKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isExpiredToken(String token) {
        return getExpiration(token).before(new Date());
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isExpiredToken(token));
    }

}
