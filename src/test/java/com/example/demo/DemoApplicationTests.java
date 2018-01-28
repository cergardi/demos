package com.example.demo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.config.JpaConfiguration;
import com.example.demo.config.initializer.YamlFileApplicationContextInitializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfiguration.class, initializers= YamlFileApplicationContextInitializer.class)
@WebAppConfiguration
@SpringBootTest
@ActiveProfiles("local,default")
public class DemoApplicationTests {

	@Autowired
    private WebApplicationContext wac;
 
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;
 
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
          //.addFilter(springSecurityFilterChain).build();
    }
    
    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/user")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized())
		.andExpect(jsonPath("$.error", is("unauthorized")));;
    }
    
    @Test
    public void givenInvalidRole_whenGetSecureRequest_thenForbidden() throws Exception {
        String accessToken = obtainAccessToken("usuario.ricoh", "jwtpass");
        mockMvc.perform(get("/api/user")
          .header("Authorization", "Bearer " + accessToken))
          .andExpect(status().isForbidden());
    }
    
    @Test
    public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
    	String accessToken = obtainAccessToken("admin.admin", "jwtpass");
    	 
        String usuariosResult = "{\"id\":66,\"nombre\":\"pruebatest\",\"apellidos\":\"apetest\",\"edad\":30}";

             
        mockMvc.perform(post("/api/user")
          .header("Authorization", "Bearer " + accessToken)
          .contentType(MediaType.APPLICATION_JSON_UTF8)//"application/json;charset=UTF-8")
          .content(usuariosResult)
          .accept("application/json;charset=UTF-8"))
          .andExpect(status().isCreated());
     
        mockMvc.perform(get("/DemoApplication/api/user/66")
          .header("Authorization", "Bearer " + accessToken)
          .accept("application/json;charset=UTF-8"))
          .andExpect(status().isOk())
          .andExpect(content().contentType("application/json;charset=UTF-8"))
          .andExpect(jsonPath("$.name", is("pruebatest")));
    }
    
    private String obtainAccessToken(String username, String password) throws Exception {

    	String authorization = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTE3MjAzMzg3LCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiI0YjhiMjBhZS0yZDQwLTRmNjktYTUyYS03MTE1OTU1YTM0YWEiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.75cUhE0mRHvi3GcclGU3POArisPvKQ6rhQuIvlkhfh0";
//    			"Bearer "
//				+ new String(Base64Utils.encode("testjwtclientid:XY7kmzoNzl100".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
    	
		String content = mockMvc
				.perform(
						post("/oauth/token")
								.header("Authorization", authorization)
								.contentType(
										MediaType.APPLICATION_JSON_UTF8)
								.param("username", username)
								.param("password", password)
								.param("grant_type", "password")
								.param("scope", "read write")
								.param("client_id", "testjwtclientid")
								.param("client_secret", "XY7kmzoNzl100"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write"))))
				.andReturn().getResponse().getContentAsString();

		// @formatter:on

		return content;

//    	ResultActions result = mockMvc.perform(post("/oauth/token")
//    			.param("username", username)
//				.param("password", password)
//				.param("grant_type", "password")
//				.param("scope", "read write")
//				.param("client_id", "testjwtclientid")
//				.param("client_secret", "XY7kmzoNzl100")
//    			.accept("application/json;charset=UTF-8")
//    			.andExpect(status().isOk())
//    			.andExpect(content().contentType("application/json;charset=UTF-8"));
//    	
//    	String resultString = result.andReturn().getResponse().getContentAsString();
//    	
//    	JacksonJsonParser jsonParser = new JacksonJsonParser();
//    	return jsonParser.parseMap(resultString).get("access_token").toString();
	}

	private Object greaterThan(int i) {
		// TODO Auto-generated method stub
		return null;
	}




}
