import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrei on 10/11/2015.
 */
public class DoctorTaskControllerTest {

    public static void addDoctorToTask(Integer taskID, Integer doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("taskID",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_DOCTOR_TASK_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void addDoctorToTaskList(Integer taskID, Integer id1, Integer id2) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> list = new ArrayList<>();
        list.add(id1);
        list.add(id2);
        HttpEntity entity = new HttpEntity(list, headers);
        Map<String,String> params = new HashMap<>();
        params.put("taskID",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_DOCTOR_TASK_LIST_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteDoctorToTask(Integer taskID, Integer doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("taskID",String.valueOf(taskID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_DOCTOR_TASK_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorsTask(Integer taskID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("taskID",String.valueOf(taskID));
        params.put("byField",String.valueOf(byField));
        params.put("ascending",String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_DOCTORS_TASK_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }
}
