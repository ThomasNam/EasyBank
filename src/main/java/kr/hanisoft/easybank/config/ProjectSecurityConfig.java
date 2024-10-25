package kr.hanisoft.easybank.config;

import kr.hanisoft.easybank.exceptionhandling.CustomAccessDeniedHandler;
import kr.hanisoft.easybank.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig
{


	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf (AbstractHttpConfigurer::disable);
		http.sessionManagement ((smc) -> smc.invalidSessionUrl ("/invalidSession").maximumSessions (1).maxSessionsPreventsLogin (true));

		http.authorizeHttpRequests ((requests) ->
				requests
						.requestMatchers ("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated ()
						.requestMatchers ("/notices", "/contact", "/error", "/register", "/invalidSession").permitAll ()
		);

		// http.formLogin (AbstractHttpConfigurer::disable);
		http.formLogin (Customizer.withDefaults ());

		// http.httpBasic (AbstractHttpConfigurer::disable);
		http.httpBasic ((hbc) -> hbc.authenticationEntryPoint (new CustomBasicAuthenticationEntryPoint ()));
		http.exceptionHandling ((ehc) -> ehc
				.accessDeniedHandler (new CustomAccessDeniedHandler ())
				// .accessDeniedPage ("/denied")
		);
		// http.exceptionHandling ((ehc) -> ehc.authenticationEntryPoint (new CustomBasicAuthenticationEntryPoint ()));

		return http.build ();
	}

	/*@Bean
	public UserDetailsService userDetailsService(DataSource dataSource)
	{
//		UserDetails user = User.withUsername ("user").password ("{noop}12345").authorities ("read").build ();
//		UserDetails admin = User.withUsername ("admin").password ("{bcrypt}$2a$12$zlBgj4fjoeTrqHFCCHpaVeyn27E4nMQJYACmNU3wqHXUziXqGZngC").authorities ("admin").build ();
//
//		return new InMemoryUserDetailsManager (user, admin);


		return new JdbcUserDetailsManager (dataSource);
	}
*/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder ();
	}

	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker()
	{
		return new HaveIBeenPwnedRestApiPasswordChecker ();
	}
}
