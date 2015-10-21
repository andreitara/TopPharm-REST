import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.task.Task;
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
public class TaskControllerTest {

    public static Calendar calendar ;
    public static Date startDate;
    public static Date endDate;

    static{
        calendar = Calendar.getInstance();
        calendar.set(2014,1,20,13,0,0);
        startDate = calendar.getTime();
        calendar.set(2014,1,20,14,0,0);
        endDate = calendar.getTime();
    }

    public static Task task = new Task("task 5","simple","new",2, startDate, endDate,"description");

    public static void createTaskByAdmin() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(task, headers);
        //Map<String,String> params = new HashMap<>();
        //params.put("doctorID",String.valueOf(doctorID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_TASK_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateTaskByAdmin(int taskID) throws JsonProcessingException {
        task.setId(taskID);
        task.setStatus("update");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(task, headers);
        //Map<String,String> params = new HashMap<>();
        //params.put("doctorID",String.valueOf(doctorID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_TASK_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllTasksByAdmin() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_TASKS_URI, HttpMethod.GET, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getTaskByAdmin(int taskID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_TASK_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteTaskByAdmin(int taskID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_TASK_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }
}
