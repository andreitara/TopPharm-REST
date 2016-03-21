import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.attributes.GeneralType;
import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by Andrei on 10/8/2015.
 */
public class DoctorControllerTest {

    public static Doctor doctor = new Doctor("doctor 333", "chisinau", "address", Calendar.getInstance().getTime(), "123", "133", "123", "email@email.md", null, "A", "A1");
    public static Doctor doctor2 = new Doctor("doctor 444", "chisinau", "address", Calendar.getInstance().getTime(), "123", "133", "123", "email@email.md", null, "A", "A1");

    public static Speciality speciality = new Speciality();

    public static void createDoctorByAdmin() throws JsonProcessingException {
        //speciality.setId(2);
        //doctor.setSpeciality(speciality);
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(doctor, headers);
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity.getBody()));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_DOCTOR_URI, HttpMethod.POST, entity, Response.class);
        mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void createDoctorListByAdmin() throws JsonProcessingException {
        //speciality.setId(2);
        //doctor.setSpeciality(speciality);
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> ids = new ArrayList<>();
        ids.add(17);
        ids.add(18);
        doctor.setInstitutionIds(ids);
        doctor2.setInstitutionIds(ids);
        List<Doctor> list = new ArrayList<>();
        list.add(doctor);
        list.add(doctor2);
        HttpEntity entity = new HttpEntity(list, headers);
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity.getBody()));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_DOCTOR_LIST_URI, HttpMethod.POST, entity, Response.class);
        mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity.getBody()));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateDoctorByAdmin(int id) throws JsonProcessingException {
        doctor.setId(id);
        doctor.setName("Valera");
        speciality.setId(1);
        doctor.setSpeciality(speciality);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(doctor, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_DOCTOR_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorsByAdmin(String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_DOCTORS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllCPCByAdmin(Integer userID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_CPC_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllStatisticsByAdmin( String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_STATISTICS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getDoctorByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_DOCTOR_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getLastDateDoctorByAdmin(Integer doctorID, Integer userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_LAST_DATE_DOCTOR_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteDoctorByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_DOCTOR_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorsBySpecialityByAdmin(Integer specialityID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("specialityID",String.valueOf(specialityID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_DOCTORS_BY_SPECIALITY_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }



    public static void addInstitutionsToDoctor(Integer doctorID, Integer instID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> list = new ArrayList<>();
        list.add(instID);
        HttpEntity entity = new HttpEntity(list, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_DOCTOR_INSTITUTION_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteInstitutionsListToDoctor(Integer doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        //list.add(2);
        HttpEntity entity = new HttpEntity(list, headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID",String.valueOf(doctorID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_DOCTOR_INSTITUTION_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllInstitutionsToDoctor(Integer doctorID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("doctorID", String.valueOf(doctorID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_DOCTOR_INSTITUTIONS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorsWithInstitutionID(Integer institutionID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("institutionID", String.valueOf(institutionID));
        params.put("byField", String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_DOCTORS_BY_INSTITUTION_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }



}
