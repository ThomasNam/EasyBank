package kr.hanisoft.easybank.config;

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
@Profile ("prod")
public class ProjectSecurityProdConfig
{


	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.requiresChannel ((rc) -> rc.anyRequest ().requiresSecure ());
		http.csrf (AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests ((requests) ->
				requests
						.requestMatchers ("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated ()
						.requestMatchers ("/notices", "/contact", "/error", "/register").permitAll ()
		);

		// http.formLogin (AbstractHttpConfigurer::disable);
		http.formLogin (Customizer.withDefaults ());

		// http.httpBasic (AbstractHttpConfigurer::disable);
		http.httpBasic (Customizer.withDefaults ());

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
