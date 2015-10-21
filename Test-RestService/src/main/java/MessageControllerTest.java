import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.pharm.hibernate.message.Message;
import md.pharm.restservice.service.Response;
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

    public static Message message = new Message();
    static{
        message.setMessage("Message 3");
    }

    public static void addMessage() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(message, headers);
        Map<String,String> params = new HashMap<>();
        params.put("fromID",String.valueOf(2));
        params.put("toID",String.valueOf(1));
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.ADD_MESSAGE, HttpMethod.POST, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }

    public static void getMessageUniDirectional() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", StaticStrings.ADMIN_AUTH_TOKEN);
        HttpEntity entity = new HttpEntity(message, headers);
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
        HttpEntity entity = new HttpEntity(message, headers);
        Map<String,String> params = new HashMap<>();
        params.put("user1ID",String.valueOf(2));
        params.put("user2ID",String.valueOf(1));
        params.put("start", "20151019");
        params.put("end",   "20151021");
        HttpEntity<Response> response = restTemplate.exchange(StaticStrings.GET_MESSAGES_USER_USER, HttpMethod.GET, entity, Response.class, params);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBody()));
    }


}
