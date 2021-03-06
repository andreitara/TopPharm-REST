package md.pharm.restservice.service.user;

import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.attributes.ManageStatus;
import md.pharm.hibernate.user.attributes.Status;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.util.Response;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Andrei on 9/7/2015.
 */
@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/user/")
public class UserController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<User>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                        @PathVariable("byField") String byField,
                                                        @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        List<User> list = manageUser.getUsers(byField, ascending);
        if(list!=null){
            ManageDoctor manageDoctor = new ManageDoctor(country);
            for(int i=0 ; i<list.size() ; i++){
                User user = list.get(i);
                List<Institution> institutions = manageDoctor.getInstitutionsByUserID(user.getId());
                List<Integer> ids = new ArrayList<>();
                Map<Integer, String> map = new HashMap<>();
                for(Institution institution : institutions){
                    ids.add(institution.getId());
                    map.put(institution.getId(), institution.getLongName());
                }
                user.setInstitutionIds(ids);
                user.setInstitutionIdsNames(map);
            }
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<User>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<User>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response<Integer>> createUser(@RequestBody User user,
                                                        @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country){
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
                user.setCountry(country);
                manageUser = new ManageUser(country);
                Integer id = manageUser.addUser(user);
                if (id != null) {
                    ManageDoctor manageDoctor = new ManageDoctor(country);
                    for(Integer institutionID : user.getInstitutionIds()){
                        manageDoctor.addInstitutionUser(institutionID, id);
                    }
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    response.setObject(id);
                    return new ResponseEntity<Response<Integer>>(response, HttpStatus.CREATED);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
                response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
                return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Response<User>> getUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                  @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(id);
        if (user != null) {
            ManageDoctor manageDoctor = new ManageDoctor(country);
            List<Institution> institutions = manageDoctor.getInstitutionsByUserID(user.getId());
            List<Integer> ids = new ArrayList<>();
            Map<Integer, String> map = new HashMap<>();
            for (Institution institution : institutions) {
                ids.add(institution.getId());
                map.put(institution.getId(), institution.getLongName());
            }
            user.setInstitutionIds(ids);
            user.setInstitutionIdsNames(map);

            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(user);
            return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public ResponseEntity<Response<User>> getMyUser(@RequestHeader(StaticStrings.HEADER_COUNTRY) String country,
                                                    @RequestHeader(StaticStrings.HEADER_USERNAME) String username){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByUsername(username);
        if(user!=null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(user);
            return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/username/{username}",method = RequestMethod.GET)
    public ResponseEntity<Response<User>> getUserByUserName(@RequestHeader(StaticStrings.HEADER_COUNTRY) String country,
                                                            @PathVariable(value = "username") String username){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByUsername(username);
        if(user!=null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(user);
            return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Response<User>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                               @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(id);
        if (user != null) {
            manageUser.deleteInstitutionUserByUserID(user.getId());
            if (manageUser.deleteUser(user)) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Response> updateUser(@RequestBody User user,
                                               @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        if (user.getId() > 0) {
            User userFromDB = manageUser.getUserByID(user.getId());
            if (userFromDB != null) {
                user.setCountry(country);
                user.setPassword(userFromDB.getPassword());
                user.setHasUnreadMessages(userFromDB.isHasUnreadMessages());
                Set<Violation> violations = new ValidatorUtil<User>().getViolations(user);
                if (violations.size() == 0) {
                    user.setStatus(userFromDB.getStatus());
                    if (manageUser.updateUser(user)) {
                        response.setResponseCode(ErrorCodes.OK.name);
                        response.setResponseMessage(ErrorCodes.OK.userMessage);
                        return new ResponseEntity<Response>(response, HttpStatus.OK);
                    } else {
                        response.setResponseCode(ErrorCodes.InternalError.name);
                        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                        return new ResponseEntity<Response>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                    response.setViolations(violations);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/update/{userID}/status/{statusID}", method = RequestMethod.POST)
    public ResponseEntity<Response> updateStatusUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                     @PathVariable(value = "userID") Integer userID,
                                                     @PathVariable(value = "statusID") Integer statusID) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        ManageStatus manageStatus = new ManageStatus(country);
        User user = manageUser.getUserByID(userID);
        Status status = manageStatus.getByID(statusID);
        if(user!=null && status!=null){
            user.setStatus(status);
            if(manageUser.updateUser(user)){
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }
}
