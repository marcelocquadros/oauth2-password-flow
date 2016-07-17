package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

//uncomment this to enable @Preauthorize('hasRole('xxx')) annotations
//@EnableGlobalMethodSecurity(prePostEnabled = true) 
@SpringBootApplication
public class Oauth2PasswordFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2PasswordFlowApplication.class, args);
	}
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter{
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
				.antMatchers("/person").hasRole("admin")
				.antMatchers("/person/me").authenticated();
		}
		
	}
	
	
	
	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.allowFormAuthenticationForClients();
		}
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("foo").secret("foosecret")
			.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("openid");
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("marcelo").password("123").roles("admin")
		.and()
		.withUser("user1").password("123").roles("user");

	}
	
	
}
