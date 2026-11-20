
package com.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception
	{
		//Cross-Site Request Forgery
		http.csrf(csrf -> csrf.disable())
		// Do not create HTTP sessions for authentication
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// Authorization rules
		.authorizeHttpRequests(requests -> requests.requestMatchers("/", "/auth/login").permitAll()
		.requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN").requestMatchers("/admin/**")
		.hasRole("ADMIN").anyRequest().authenticated())

		.exceptionHandling(
			ex -> ex.authenticationEntryPoint((request, response, authException) -> response.sendError(401, "Unauthorized"))
				.accessDeniedHandler((request, response, accessDeniedException) -> response.sendError(403, "Forbidden"))
			)
		// Disable browser form-based login
		.formLogin(form -> form.disable())
		// Disable HTTP Basic authentication
		.httpBasic(basic -> basic.disable())

		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

		provider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(provider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService,
			UserDetailsService userDetailsService) {

		return new JwtAuthenticationFilter(jwtService, userDetailsService);
	}
}
