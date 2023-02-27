package io.nology.postcodeRestApi.suburb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.builder().username("editor").password(encoder().encode("editor123")).roles("EDITOR")
				.build();
		UserDetails user2 = User.builder().username("viewer").password(encoder().encode("viewer123")).roles("VIEWER")
				.build();
		return new InMemoryUserDetailsManager(user1, user2);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(
						(authz) -> authz.antMatchers("/", "/suburbs").hasAnyRole("EDITOR", "VIEWER")
								.antMatchers("/suburbs/create").hasRole("EDITOR").anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
