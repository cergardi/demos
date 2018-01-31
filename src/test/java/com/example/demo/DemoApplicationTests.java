package com.example.demo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.OrdersController;
import com.example.demo.dto.UserDto;

// TODO Pendiente de realizar los tests
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@ActiveProfiles("local,default")
public class DemoApplicationTests {

    @Autowired
    private OrdersController ordersController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ordersController)
                .build();
    }
    @Test
    public void sinTest() {
    	
    }

//    @Test
//    public void givenNoToken_whenGetSecureRequest_thenUnauthorized()
//            throws Exception {
//        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.error", is("unauthorized")));
//    }
//
//    @Test
//    public void givenInvalidRole_whenGetSecureRequest_thenForbidden()
//            throws Exception {
//        String accessToken = obtainAccessToken("usuario.ricoh", "jwtpass");
//        mockMvc.perform(get("/api/user/").header("Authorization",
//                "Bearer " + accessToken)).andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
//        String accessToken = obtainAccessToken("admin.admin", "jwtpass");
//
//        String usuariosResult = "{\"id\":66,\"nombre\":\"pruebatest\",\"apellidos\":\"apetest\",\"edad\":30}";
//
//        mockMvc.perform(post("/api/user/")
//                .header("Authorization", "Bearer " + accessToken)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(usuariosResult)
//                .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(get("/api/user/66")
//                .header("Authorization", "Bearer " + accessToken)
//                .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isOk())
//                .andExpect(
//                        content().contentType("application/json;charset=UTF-8"))
//                .andExpect(jsonPath("$.name", is("pruebatest")));
//    }

    private String obtainAccessToken(String username, String password)
            throws Exception {
        String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";

        String content = mockMvc
                .perform(post("/oauth/token")
                        .header("Authorization", getOauthTestAuthentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("username", username).param("password", password)
                        .param("grant_type", "password")
                        .param("scope", "read write")
                        .param("client_id", "testjwtclientid")
                        .param("client_secret", "XY7kmzoNzl100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.access_token", is(notNullValue())))
                .andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
                .andExpect(jsonPath("$.refresh_token", is(notNullValue())))
                .andExpect(jsonPath("$.scope", is(equalTo("read write"))))
                .andReturn().getResponse().getContentAsString();

        return content;
    }

    private Authentication getOauthTestAuthentication() {
        return new OAuth2Authentication(getOauth2Request(),
                getAuthentication());
    }

    private OAuth2Request getOauth2Request() {
        String clientId = "oauth-client-id";
        Map<String, String> requestParameters = Collections.emptyMap();
        boolean approved = true;
        String redirectUrl = "http://my-redirect-url.com";
        Set<String> responseTypes = Collections.emptySet();
        Set<String> scopes = Collections.emptySet();
        Set<String> resourceIds = Collections.emptySet();
        Map<String, Serializable> extensionProperties = Collections.emptyMap();
        List<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList("Everything");

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters,
                clientId, authorities, approved, scopes, resourceIds,
                redirectUrl, responseTypes, extensionProperties);

        return oAuth2Request;
    }

    private Authentication getAuthentication() {
        List<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList("Everything");

        UserDto userPrincipal = new UserDto();
        userPrincipal.setUsername("username");
        userPrincipal.setPassword("password");

        HashMap<String, String> details = new HashMap<String, String>();
        details.put("user_name", "bwatkins");
        details.put("email", "bwatkins@test.org");
        details.put("name", "Brian Watkins");

        TestingAuthenticationToken token = new TestingAuthenticationToken(
                userPrincipal, null, authorities);
        token.setAuthenticated(true);
        token.setDetails(details);

        return token;
    }
}
