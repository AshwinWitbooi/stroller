package za.co.ashtech.stroller.config;

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

import za.co.ashtech.stroller.services.StrollUserDetailsService;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private StrollUserDetailsService strollUserDetailsService;
	
	@Bean
	@Order(1)
    public SecurityFilterChain basicAuthFilterChain(HttpSecurity http) throws Exception {
        http
        	.securityMatcher("/oauth/**")
        	.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/oauth/access/token").authenticated()
                .anyRequest().permitAll()
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
			.securityMatcher("/admin/api/**","/api/v1/**")
			.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/admin/api/v1/stroll/**", "/api/v1/stroll").authenticated()
			)
			.oauth2ResourceServer(
						oauth2 -> oauth2.jwt(Customizer.withDefaults())
			);
		
		return http.build();
		
	}
}
