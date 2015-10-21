import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.restservice.service.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 10/11/2015.
 */
public class DoctorUserControllerTest {

    public static void addDoctorToUser(Integer userID, Integer doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_DOCTOR_USER_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteDoctorFromUser(Integer userID, Integer doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_DOCTOR_USER_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorsFromUser(Integer userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_DOCTORS_USER_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }
}
