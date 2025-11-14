package za.co.ashtech.stroller.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import za.co.ashtech.stroller.services.StrollUserDetailsService;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class LocalSecurityConfig {
	
	@Autowired
    private StrollUserDetailsService strollUserDetailsService;
	
	@Bean
	@Order(1)
	public SecurityFilterChain basicAuthFilterChain(HttpSecurity http) throws Exception {
	    http
	    	.csrf(csrf -> csrf.disable())	 
	    	.cors(Customizer.withDefaults()) 
	        .securityMatcher("/oauth/**", "/public/**") // ensure matcher covers both paths
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/public/**").permitAll()
	            .requestMatchers("/oauth/access/token").authenticated()
	            .anyRequest().authenticated()
	        )
	        .httpBasic(Customizer.withDefaults())
	        .userDetailsService(strollUserDetailsService);

	    return http.build();
	}
	

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	@Order(2)
	public SecurityFilterChain jwtAuthFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
	    	.cors(Customizer.withDefaults()) 
			.securityMatcher("/api/v1/**")
            .authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/api/v1/stroll").authenticated()
			)
			.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(Customizer.withDefaults())
			);
		
		return http.build();
		
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(List.of("http://localhost:8080"));
	    config.setAllowedMethods(List.of("*")); // GET, POST, PUT, DELETE, OPTIONS
	    config.setAllowedHeaders(List.of("*"));
	    config.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}

}
