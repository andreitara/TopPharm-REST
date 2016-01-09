import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.message.Message;
import md.pharm.util.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c-andrtara on 10/20/2015.
 */
public class MessageControllerTest {

    public static Message messageTO = new Message();
    public static Message messageFROM = new Message();
    static{
        messageTO.setMessage("From Admin to User");
        messageFROM.setMessage("From User to Admin");
    }

    public static void addMessageFromAdmin(Integer toID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(messageTO, headers);
        Map<String,String> params = new HashMap<>();
        params.put("toID",String.valueOf(toID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_MESSAGE, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void addMessageFromUser(Integer toID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(messageFROM, headers);
        Map<String,String> params = new HashMap<>();
        params.put("toID",String.valueOf(toID));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_MESSAGE, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getMessageUniDirectional() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("fromID",String.valueOf(2));
        params.put("toID",String.valueOf(1));
        params.put("start", "20151019");
        params.put("end",   "20151021");
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_MESSAGES_FROM_TO, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getMessageBiDirectional() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("user1ID",String.valueOf(1));
        params.put("user2ID",String.valueOf(1002));
        params.put("start", "20151019");
        params.put("end",   "20151021");
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_MESSAGES_USER_USER, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getMessageBiDirectionalFROM(Integer fromID, Integer firstResult, Integer maxResult) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("fromID",String.valueOf(fromID));
        params.put("firstResult",String.valueOf(firstResult));
        params.put("maxResult",String.valueOf(maxResult));

        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_MESSAGES_FROM, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getLatestMessageBiDirectionalFROM(Integer fromID, Integer maxResult) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("fromID",String.valueOf(fromID));
        params.put("maxResult",String.valueOf(maxResult));

        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_LATEST_MESSAGES_FROM, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getAllRepresentatives(String byField, boolean ascending) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        Map<String,String> params = new HashMap<>();
        params.put("byField",String.valueOf(byField));
        params.put("ascending",String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_REPRESENTATIVES, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getHasUnreadMessages() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(null, headers);
        //Map<String,String> params = new HashMap<>();
        //params.put("byField",String.valueOf(byField));
        //params.put("ascending",String.valueOf(ascending));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_HAS_UNREAD_MESSAGES, HttpMethod.GET, entity, Response.class);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }


}
