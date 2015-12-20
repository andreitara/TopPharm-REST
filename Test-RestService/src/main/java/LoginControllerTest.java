import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.login.UserChangePassword;
import md.pharm.hibernate.user.login.UserLogin;
import md.pharm.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Created by Andrei on 9/24/2015.
 */
public class LoginControllerTest {

    public static UserChangePassword userChangePassword = new UserChangePassword("user1","useruseruser1","useruseruser5");

    public static void loginAdmin() throws JsonProcessingException {
        UserLogin user = new UserLogin();
        user.setUsername("adminmd");
        user.setPassword("admin1234");
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.postForObject(StaticStrings.LOGIN_URI, user, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

    public static void changePasswordAdmin() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity entity = new HttpEntity(userChangePassword, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CHANGE_PASSWORD_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

    public static void logoutAdmin() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.LOGOUT_URI, HttpMethod.GET, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void loginUser() throws JsonProcessingException {
        UserLogin user = new UserLogin();
        user.setUsername("user1");
        user.setPassword("useruseruser3");
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.postForObject(StaticStrings.LOGIN_URI, user, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

    public static void logoutUser() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.USER_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.LOGOUT_URI, HttpMethod.GET, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

}
