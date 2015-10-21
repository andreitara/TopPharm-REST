import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.restservice.service.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 10/3/2015.
 */
public class PermissionControllerTest {

    public static Permission fullPermissions = new Permission(null,true,true,true,true,true,true,true);
    public static Permission defaultPermissions = new Permission(null,true,false,true,false,true,false,false);

    public static void getAdminUserPermission(Integer userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        Map<String,String> params = new HashMap<String, String>();
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_USER_PERMISSION_URI, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAdminMyPermission() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_MY_PERMISSION_URI, HttpMethod.GET, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void updatePermissionsByAdmin(int userID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(defaultPermissions, headers);
        Map<String,String> params = new HashMap<String, String>();
        params.put("userID",String.valueOf(userID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.UPDATE_PERMISSION_URI, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

}
