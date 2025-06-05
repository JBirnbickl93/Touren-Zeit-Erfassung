package com.birnbickl.Touren_Zeit_Erfassung.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    // Filter, sodass Login und Register ohne JWT erreichbar sind
    // alle anderen Routen sind nur mit Token erreichbar/durchlässig
    // CSRF deaktiviert, da Anwendung über Token gesichert
    @Bean
    public SecurityFilterChain securityFilterChain(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") HttpSecurity http) throws Exception {
    return http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/vorgesetzter/**").hasAnyRole( "VORGESETZTER", "ADMIN")
                    .requestMatchers("/api/user/**").hasAnyRole("USER", "VORGESETZTER", "ADMIN")
                    .anyRequest().authenticated())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}


}
