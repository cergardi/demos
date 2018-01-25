//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//import com.example.demo.service.interfaz.UsuarioService;
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//	@Autowired
//	private UsuarioService usuarioService;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Value("${gigy.oauth.tokenTimeout:3600}")
//	private int expiration;
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
//		configurer.authenticationManager(authenticationManager);
//		configurer.userDetailsService(usuarioService);
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient("DemoApplication").secret("secret").accessTokenValiditySeconds(expiration)
//				.scopes("read", "write").authorizedGrantTypes("password", "refresh_token").resourceIds("resource");
//	}
//}
