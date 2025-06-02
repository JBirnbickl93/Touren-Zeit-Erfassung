package com.birnbickl.Touren_Zeit_Erfassung.Security;

import com.birnbickl.Touren_Zeit_Erfassung.Service.JwtService;
import com.birnbickl.Touren_Zeit_Erfassung.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Header prüfen, gibt es überhaupt einen Header?
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Token aus Header extrahieren und Username auslesen
        jwt = authHeader.substring(7); // Bearer hat 7 Zeichen
        username = jwtService.extractUsername(jwt);

        // Wenn kein Nutzer, weitere Prüfung

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Nutzer aus DB holen
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Token validieren
            if(jwtService.isTokenValid(jwt, userDetails)) {
                String role = jwtService.extractUserRole(jwt);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

                //Nutzer im SecurityContext setzen
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Anfrage weiterreichen
        filterChain.doFilter(request, response);
    }

}
