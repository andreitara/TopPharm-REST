package md.pharm.restservice.service.user;

import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 9/7/2015.
 */
@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/user/")
public class UserController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
     public ResponseEntity<?> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        List<User> list = manageUser.getUsers();
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            //response.addMapItem("users", list);
            response.setObject(list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestBody User user){
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<User>().getViolations(user);
        if(violations.size()==0) {
            ManageUser manageUser = new ManageUser("MD");
            User existUser = manageUser.getUserByUsername(user.getUsername());
            if (existUser == null) {
                manageUser = new ManageUser("RO");
                existUser = manageUser.getUserByUsername(user.getUsername());
            }
            if (existUser == null) {
                if (user.getPermission() == null) {
                    user.createDefaultPermission();
                }
                user.setCountry(country);
                manageUser = new ManageUser(country);
                Integer id = manageUser.addUser(user);
                if (id != null) {
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    response.setObject(id);
                    //response.addMapItem("user", user);
                    return new ResponseEntity<Object>(response, HttpStatus.CREATED);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
                response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") Integer id){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(id);
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        //response.addMapItem("user", user);
        response.setObject(user);
        return  new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(id);
        if (user != null) {
            if (manageUser.deleteUser(user)) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestBody User user){
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<User>().getViolations(user);
        if(violations.size()==0) {
            ManageUser manageUser = new ManageUser(country);
            if (user.getId() > 0) {
                User userFromDB = manageUser.getUserByID(user.getId());
                if (userFromDB != null) {
                    if (manageUser.updateUser(user)) {
                        response.setResponseCode(ErrorCodes.OK.name);
                        response.setResponseMessage(ErrorCodes.OK.userMessage);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    } else {
                        response.setResponseCode(ErrorCodes.InternalError.name);
                        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                    response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
