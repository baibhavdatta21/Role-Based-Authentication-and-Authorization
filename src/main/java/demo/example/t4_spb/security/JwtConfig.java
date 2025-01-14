package demo.example.t4_spb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JwtConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired 
	JwtFilter jwtFilter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf(c->c.disable());
		 http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		 http
		    .authorizeHttpRequests(requests -> requests //->authorizeRequests
		        .requestMatchers("/public/**", "/login", "/h2-console/**,/get/**","/gen/*").permitAll() // Unrestricted access
		        .requestMatchers("/bidding/add").hasAnyAuthority("Bidder", "Approver")  // Adjusted to match authority
		        .requestMatchers("/bidding/update/**").hasAuthority("Approver") // Adjusted to match authority
		        .requestMatchers("/bidding/delete/*").hasAnyAuthority("Bidder", "Approver") // Adjusted to match authority
		    );
		 http.formLogin(Customizer.withDefaults());
		 http.httpBasic(Customizer.withDefaults());
		 http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	  http.exceptionHandling((exception)-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedPage("/error/accedd-denied"));

		 return http.build();
				
	}
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
}
