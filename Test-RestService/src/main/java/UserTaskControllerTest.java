import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.restservice.service.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 10/11/2015.
 */
public class UserTaskControllerTest {

    public static Calendar calendar ;
    public static Date startDate;
    public static Date endDate;

    static{
        calendar = Calendar.getInstance();
        calendar.set(2015,6,20,13,0,0);
        startDate = calendar.getTime();
        calendar.set(2015,11,29,14,0,0);
        endDate = calendar.getTime();
    }

    public static void addUserToTask(Integer taskID, Integer userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        params.put("taskID",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_USER_TASK_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteUserToTask(Integer taskID, Integer userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        params.put("taskID", String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_USER_TASK_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllUsersTask(Integer taskID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("taskID",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_USERS_TASK_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getUserTasksByStartAndEndDate(Integer userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        params.put("fromDate","20150101");
        params.put("toDate","20151230");
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_USER_TASKS_BY_DATE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getUserTasksByStatus(Integer userID, String status) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        params.put("status",status);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_USER_TASKS_BY_STATUS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getUserTasksByType(Integer userID, String type) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("type", type);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_USER_TASKS_BY_TYPE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }
}
