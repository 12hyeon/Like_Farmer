package likelion.Spring_Like_Farmer.security;

import io.jsonwebtoken.*;
import likelion.Spring_Like_Farmer.config.AppProperties;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@PropertySource("classpath:application.yml")
@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private UserRepository userRepository;

    public String createToken(Authentication authentication, boolean refresh) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        int time;
        if (refresh) {
            time = (int) (appProperties.getAuth().getTokenExpirationDay() * 3 * 60 * 24); // 15일
        } else {
            time = (int) (appProperties.getAuth().getTokenExpirationDay() * 60 * 24); // 5일
        }

        return Jwts.builder()
                .setSubject(String.valueOf(userPrincipal.getUserId()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusDays(time).toInstant()))
                .signWith( SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(appProperties.getAuth().getTokenSecret())
                    .parseClaimsJws(token)
                    .getBody();

            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date());
        } catch (ExpiredJwtException ex) {
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
