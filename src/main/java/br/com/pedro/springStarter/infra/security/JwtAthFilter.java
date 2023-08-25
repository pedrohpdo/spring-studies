package br.com.pedro.springStarter.infra.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAthFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenServices tokenService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		final String REQUEST_HEADER = request.getHeader(AUTHORIZATION);
		
		if (REQUEST_HEADER != null ) {
			final String JWTTOKEN = REQUEST_HEADER.substring(7);
			final String SUBJECT = tokenService.getUser(JWTTOKEN);			
		
			UserDetails userDetails = userDetailsService.loadUserByUsername(SUBJECT);
		
			UsernamePasswordAuthenticationToken tokenAuth = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			
			tokenAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(tokenAuth);
		}

		filterChain.doFilter(request, response);
	}
}
