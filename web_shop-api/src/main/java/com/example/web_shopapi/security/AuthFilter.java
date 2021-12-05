package com.example.web_shopapi.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

	private final static String AUTHORIZATION_HEADER = "Authorization";

	private final UserDetailsService userService;
	private final TokenUtils tokenUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String s = request.getRequestURI();
		String s1 = request.getPathInfo();
		String s2 = request.getPathTranslated();
		String token = request.getHeader(AUTHORIZATION_HEADER);
		if (token != null && !token.trim().isEmpty()) {
			token = token.substring(7);
			UserDetails user = this.userService.loadUserByUsername(this.tokenUtils.getEmail(token));
			if (user != null && this.tokenUtils.validateToken(user, token)) {
				AuthToken authToken = new AuthToken(user, token);
				SecurityContextHolder.getContext().setAuthentication(authToken); // tu
			}
		}
		filterChain.doFilter(request, response);		
		
	}
	
}