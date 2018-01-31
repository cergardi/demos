package com.example.demo.config;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                      
          .build()
          .apiInfo(apiInfo());    
    }
    
//	@Bean
//  public Docket api() { 
//	return new Docket(DocumentationType.SWAGGER_2)
//	        .select()
//	          .apis(RequestHandlerSelectors.any())
//	          .paths(PathSelectors.any())
//	          .build()
//	        .pathMapping("/")
//	        .directModelSubstitute(LocalDate.class,
//	            String.class)
//	        .genericModelSubstitutes(ResponseEntity.class)
//	        .alternateTypeRules(
//	            newRule(typeResolver.resolve(DeferredResult.class,
//	                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//	                typeResolver.resolve(WildcardType.class)))
//	        .useDefaultResponseMessages(false)
//	        .globalResponseMessage(RequestMethod.GET,
//	            newArrayList(new ResponseMessageBuilder()
//	                .code(500)
//	                .message("500 message")
//	                .responseModel(new ModelRef("Error"))
//	                .build()))
//	        .securitySchemes(newArrayList(apiKey()))
//	        .securityContexts(newArrayList(securityContext()))
//	        .enableUrlTemplating(true).apiInfo(apiInfo());
//	  }

//	  @Autowired
//	  private TypeResolver typeResolver;
//
//	  private ApiKey apiKey() {
//	    return new ApiKey("mykey", "api_key", "header");
//	  }
//
//	  private SecurityContext securityContext() {
//	    return SecurityContext.builder()
//	        .securityReferences(defaultAuth())
//	        .forPaths(PathSelectors.regex("/swagger.*"))
//	        .build();
//	  }
//
//	  List<SecurityReference> defaultAuth() {
//	    AuthorizationScope authorizationScope
//	        = new AuthorizationScope("global", "accessEverything");
//	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//	    authorizationScopes[0] = authorizationScope;
//	    return newArrayList(
//	        new SecurityReference("mykey", authorizationScopes));
//	  }
//	  private OAuth securitySchema() {
//		    //AuthorizationScope authorizationScope = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobal);
//		    LoginEndpoint loginEndpoint = new LoginEndpoint("http://localhost:8080/DemoApplication/swagger-ui.html");
//		    GrantType grantType = new ImplicitGrant(loginEndpoint, "access_token");
//		    new Password
//		    List s = new ArrayList();
//		    //s.add(authorizationScope);
//		    List d = new ArrayList();
//		    d.add(grantType);
//		    return new OAuth(securitySchemaOAuth2, s, d);
//		}
//	  @Bean
//	  SecurityConfiguration security() {
//	    return SecurityConfigurationBuilder.builder()
//	        .clientId("test-app-client-id")
//	        .clientSecret("test-app-client-secret")
//	        .realm("test-app-realm")
//	        .appName("test-app")
//	        .scopeSeparator(",")
//	        .additionalQueryStringParams(null)
//	        .useBasicAuthenticationWithAccessCodeGrant(false)
//	        .build();
//	  }

    private ApiInfo apiInfo() {
        String description = "Aplicación que gestiona usuarios";
        return new ApiInfoBuilder()
                .title("Gestión de usuarios")
                .description(description)
                .termsOfServiceUrl("github")
                .license("cergardi")
                .licenseUrl("")
                .version("1.0")
                .build();
    }
}