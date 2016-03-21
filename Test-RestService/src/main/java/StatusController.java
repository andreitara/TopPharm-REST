import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.hibernate.user.attributes.Status;
import md.pharm.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 12/20/2015.
 */
public class StatusController {

    public static Status status = new Status("status 2");

    public static void createStatusByAdmin() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(status, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_STATUS_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateStatusByAdmin(int id) throws JsonProcessingException {
        status.setId(id);
        status.setName("Status Update");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(status, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_STATUS_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllStatusesByAdmin(String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String, String> params = new HashMap<>();
        params.put("byField", String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_STATUS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateUserStatusByAdmin(Integer userID, Integer statusID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String, String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("statusID", String.valueOf(statusID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_USER_STATUS_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getStatusByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_STATUS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteStatusByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_STATUS_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

}
