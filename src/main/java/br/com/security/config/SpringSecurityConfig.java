package br.com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private WsEmpregadoDetailsService wsEmpregadoDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/recovery/empregado/**").permitAll()
		.antMatchers("/app-clientes/**").permitAll()
		.antMatchers("/app-mdata/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(authEntryPoint)
		.and()
		.userDetailsService(wsEmpregadoDetailsService);
	}
	
}
