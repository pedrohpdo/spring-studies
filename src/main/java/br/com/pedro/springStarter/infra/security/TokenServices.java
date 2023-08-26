package br.com.pedro.springStarter.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.pedro.springStarter.infra.exception.ExpiredTokenException;
import br.com.pedro.springStarter.models.entities.User;

@Service
public class TokenServices {

	@Value("api.security.token.secret")
	private String secret;

	private Instant instant = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("123456");
			return JWT
					.create()
					.withIssuer("API Voll.med")
					.withSubject(user.getEmail())
					.withClaim("password", user.getPassword())
					.withExpiresAt(this.instant).sign(algorithm);

		} catch (JWTCreationException e) {

			throw new RuntimeException("error: " + e.getMessage());
		}
	}

	public String getUser(String token) {
		try {
			Algorithm alg = Algorithm.HMAC256("123456");
			return JWT
					.require(alg)
					.withIssuer("API Voll.med")
					.build()
					.verify(token)
					.getSubject();

		} catch (JWTVerificationException e) {
			throw new ExpiredTokenException(e.getLocalizedMessage());
		}
	}

}
