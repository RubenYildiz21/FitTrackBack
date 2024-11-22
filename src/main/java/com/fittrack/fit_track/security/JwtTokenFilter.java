// src/main/java/com/fittrack/fit_track/security/JwtTokenFilter.java

package com.fittrack.fit_track.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fittrack.fit_track.service.CustomUserDetailsService;
import com.fittrack.fit_track.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/api/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**");

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        System.out.println("Incoming request: " + requestPath);

        // Vérifier si l'URL doit être exclue du filtrage JWT
        for (String pattern : EXCLUDED_URLS) {
            if (pathMatcher.match(pattern, requestPath)) {
                System.out.println("Excluding URL from JWT filter: " + requestPath);
                // Passer la requête au filtre suivant sans traiter le JWT
                filterChain.doFilter(request, response);
                return;
            }
        }

        try {
            String jwt = getJwt(request);
            if (jwt != null) {
                System.out.println("JWT Token found: " + jwt);
            }
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String email = jwtUtils.getUserEmailFromJwtToken(jwt);
                System.out.println("JWT Token valid for user: " + email);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("User authenticated: " + email);
                }
            } else {
                System.out.println("No valid JWT Token found.");
            }
        } catch (UsernameNotFoundException e) {
            System.err.println("Cannot set user authentication: " + e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
