import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.common.Address;
import md.pharm.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 10/8/2015.
 */
public class InstitutionAddressControllerTest {

    public static Address address = new Address("street34666", "district", "city2", "state", "country", "code");

    public static void getInstitutionAddressByAdmin(int institutionID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<>();
        params.put("institutionID",String.valueOf(institutionID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_INSTITUTION_ADDRESS_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void createInstitutionAddressByAdmin(int institutionID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(address, headers);
        Map<String,String> params = new HashMap<>();
        params.put("institutionID",String.valueOf(institutionID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.CREATE_INSTITUTION_ADDRESS_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updateInstitutionAddressByAdmin(int institutionID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(address, headers);
        Map<String,String> params = new HashMap<>();
        params.put("institutionID",String.valueOf(institutionID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_INSTITUTION_ADDRESS_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }
}
