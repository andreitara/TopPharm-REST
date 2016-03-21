import Entities.CreateUser;
import Entities.UpdateUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.util.Response;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by Andrei on 9/22/2015.
 */

public class UserControllerTest {

    static CreateUser user = new CreateUser("user","user","user", Calendar.getInstance().getTime(),"user7","useruseruser5","user@email.com","+698574","126345987");

    static UpdateUser updateUser = new UpdateUser("user","user","user", Calendar.getInstance().getTime(),"user3","user@email.com","+698574","126345987");

    public static void createUserByAdmin() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        user.setInstitutionIds(list);
        HttpEntity entity = new HttpEntity(user, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_USER_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateUserByAdmin(int id) throws JsonProcessingException {
        updateUser.setId(id);
        updateUser.setName("USER Name update");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(updateUser, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_USER_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }


    public static void getAllUsersByAdmin(String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_USERS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getUserByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_USER_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteUserByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_USER_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

}
