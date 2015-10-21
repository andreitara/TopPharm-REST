package md.pharm.restservice.service.user;

import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.connection.ManageConnection;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.login.UserLogin;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 9/24/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/user")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Response<String>> createUser(@RequestBody UserLogin user){
        Response response = new Response();
        if(user!=null) {
            ManageUser manageUser = new ManageUser("MD");
            ManageConnection manageConnection = new ManageConnection("MD");
            User userFromDB = manageUser.getUserByUsername(user.getUsername());
            if(userFromDB == null){
                manageUser = new ManageUser("RO");
                manageConnection = new ManageConnection("RO");
                userFromDB = manageUser.getUserByUsername(user.getUsername());
            }
            if(userFromDB!=null) {
                if (userFromDB.getPassword().equals(user.getPassword())) {
                    Connection connection = new Connection();
                    userFromDB.setConnection(connection);
                    connection.setUser(userFromDB);
                    manageConnection.addConnection(connection);
                    response.setObject(connection.getConnectionKey());
                    response.setResponseCode(ErrorCodes.ValidAuthenticationInfo.name);
                    response.setResponseMessage(ErrorCodes.ValidAuthenticationInfo.userMessage);
                    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
                } else {
                    response.setResponseCode(ErrorCodes.InvalidAuthenticationInfo.name);
                    response.setResponseMessage(ErrorCodes.InvalidAuthenticationInfo.userMessage + ". Password is incorect");
                    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.InvalidAuthenticationInfo.name);
                response.setResponseMessage(ErrorCodes.InvalidAuthenticationInfo.userMessage + ". User do not exists");
                return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InvalidAuthenticationInfo.name);
            response.setResponseMessage(ErrorCodes.InvalidAuthenticationInfo.userMessage + ". Send user is null");
            return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<Response> getUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestHeader(StaticStrings.HEADER_USERNAME) String username){
        Response response = new Response();
        User user = new ManageUser(country).getUserByUsername(username);
        if(user!=null) {
            Connection connection = user.getConnection();
            if (connection != null && new ManageConnection(country).deleteConnection(connection) == false) {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
