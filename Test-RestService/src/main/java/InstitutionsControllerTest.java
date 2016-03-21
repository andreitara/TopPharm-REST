import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.attributes.InstitutionType;
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
 * Created by c-andrtara on 10/5/2015.
 */
public class InstitutionsControllerTest {

    public static Institution institution = new Institution("institution 111", "tn1", null, "323", "145", "rezina", "street");
    public static Institution institution2 = new Institution("institution 222", "tn1", null, "323", "145", "rezina", "street");

    public static void getAllInstitutionsByAdmin(String byField, boolean ascending) throws JsonProcessingException {
        InstitutionType type = new InstitutionType();
        type.setId(2);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_INSTITUTIONS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllInstitutionsByCity(String city, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("city",city);
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_INSTITUTIONS_BY_CITY_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllInstitutionsByCityAndType(String city, Integer typeID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("city",city);
        params.put("typeID",String.valueOf(typeID));
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_INSTITUTIONS_BY_CITY_AND_TYPE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllInstitutionsByType(Integer typeID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("typeID", String.valueOf(typeID));
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_INSTITUTIONS_BY_TYPE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllInstitutionsByPartName(String name, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("name",name);
        params.put("byField",String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_INSTITUTIONS_BY_PART_OF_NAME, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }


    public static void getInstitutionsByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_INSTITUTION_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void createInstitutionByAdmin() throws JsonProcessingException {
        InstitutionType institutionType = new InstitutionType();
        institutionType.setId(2);
        institution.setType(institutionType);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(institution, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_INSTITUTION_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void createInstitutionListByAdmin() throws JsonProcessingException {
        InstitutionType institutionType = new InstitutionType();
        institutionType.setId(2);
        institution.setType(institutionType);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Institution> list = new ArrayList<>();
        list.add(institution);
        list.add(institution2);
        HttpEntity entity = new HttpEntity(list, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_INSTITUTION_LIST_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateInstitutionByAdmin(int id) throws JsonProcessingException {
        institution.setId(id);
        institution.setLongName("Name Update 234534");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(institution, headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_INSTITUTION_URI, HttpMethod.POST, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteInstitutionByAdmin(int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("id",String.valueOf(id));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_INSTITUTION_URI, HttpMethod.DELETE, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }


    public static void getAllInstitutionsFromUser(Integer userID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("byField", String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_USER_INSTITUTIONS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllInstitutionsFromUserByType(Integer userID, Integer typeID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("typeID", String.valueOf(typeID));
        params.put("byField", String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_USER_INSTITUTIONS_BY_TYPE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getLastVisitInstitutionsFromUser(Integer userID, Integer institutionID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("institutionID", String.valueOf(institutionID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_LAST_VISIT_INSTITUTIONS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void addInstitutionsToDoctor(Integer userID, Integer instID1, Integer instID2) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> list = new ArrayList<>();
        list.add(instID1);
        list.add(instID2);
        HttpEntity entity = new HttpEntity(list, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_USER_INSTITUTION_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void deleteInstitutionsListToDoctor(Integer userID, Integer instID1) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        List<Integer> list = new ArrayList<>();
        list.add(instID1);
        //list.add(2);
        HttpEntity entity = new HttpEntity(list, headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.DELETE_USER_INSTITUTION_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }


    public static void getAllDoctorsFromUser(Integer userID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("byField", String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_USER_DOCTORS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllDoctorsFromUserByType(Integer userID, Integer specialityID, String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("userID", String.valueOf(userID));
        params.put("specialityID", String.valueOf(specialityID));
        params.put("byField", String.valueOf(byField));
        params.put("ascending", String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_ALL_USER_DOCTORS_BY_TYPE_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }




}
