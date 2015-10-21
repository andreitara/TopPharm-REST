import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.institution.Office;
import md.pharm.restservice.service.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 10/10/2015.
 */
public class DoctorOfficeControllerTest {

    public static Office office = new Office("block", 3, "34B", "Cabinetul ultimul din dreapta");

    public static void createOfficeByAdmin(int doctorID, int institutionID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(office, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("institutionID",String.valueOf(institutionID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_OFFICE_FOR_DOCTOR_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorOfficesByAdmin(int doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_OFFICES_OF_DOCTOR_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getDoctorOfficesByAdmin(int doctorID, int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_OFFICE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateDoctorOfficesByAdmin(int doctorID, int institutionID) throws JsonProcessingException {
        office.setId(3);
        office.setDescription("Update");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(office, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("institutionID",String.valueOf(institutionID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_OFFICE_FOR_DOCTOR_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteDoctorOfficeByAdmin(int doctorID, int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(office, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_OFFICE_FOR_DOCTOR_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

}
