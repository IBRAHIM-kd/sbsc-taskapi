package assignment.sbsc.com.websecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}password").roles("USER")
				.and()
				.withUser("admin").password("{noop}booking").roles("USER", "ADMIN");

	}
	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				//HTTP Basic authentication
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/employees/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
				.and()
				.csrf().disable()
				.formLogin().disable();
	}

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("booking").roles("USER", "ADMIN").build());
        return manager;
    }*/

}