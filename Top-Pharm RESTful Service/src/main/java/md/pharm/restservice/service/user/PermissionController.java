package md.pharm.restservice.service.user;

import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Andrei on 9/28/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/user/permission/")
public class PermissionController {

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Response<Permission>> getUserPermission(@PathVariable(value = "userID") Integer userID, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        User user = new ManageUser(country).getUserByID(userID);
        if (user != null) {
            Permission permission = user.getPermission();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(permission);
            return new ResponseEntity<Response<Permission>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Response<Permission>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<Response<Permission>> getMyPermission(@RequestHeader(value = StaticStrings.HEADER_USERNAME) String username, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        User user = new ManageUser(country).getUserByUsername(username);
        if (user != null) {
            Permission permission = user.getPermission();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(permission);
            return new ResponseEntity<Response<Permission>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Response<Permission>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/update/{userID}", method = RequestMethod.POST)
    public ResponseEntity<Response> addRightsToUser(@RequestBody Permission permission, @PathVariable(value = "userID") Integer userID, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Permission>().getViolations(permission);
        if (violations.size() == 0) {
            User user = new ManageUser(country).getUserByID(userID);
            if (user != null) {
                permission.setId(user.getPermission().getId());
                permission.setUser(user);
                user.setPermission(permission);
                if (new ManageUser(country).updateUser(user)) {
                    response.setResponseCode(ErrorCodes.Updated.name);
                    response.setResponseMessage(ErrorCodes.Updated.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                response.setResponseCode(ErrorCodes.ResourceNotFound.name);
                response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }
    }
}
