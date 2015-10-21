package md.pharm.restservice.service;

import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Andrei on 10/4/2015.
 */
@RestController
@RequestMapping("**/error/")
public class ErrorController {

    @RequestMapping("/InsufficientAccountPermissions")
    public ResponseEntity<?> insufficientAccountPermissions() {
        Response response = new Response();
        response.setResponseCode(ErrorCodes.InsufficientAccountPermissions.name);
        response.setResponseMessage(ErrorCodes.InsufficientAccountPermissions.userMessage);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping("/InvalidAuthenticationInfo")
    public ResponseEntity<?> invalidAuthenticationInfo() {
        Response response = new Response();
        response.setResponseCode(ErrorCodes.InvalidAuthenticationInfo.name);
        response.setResponseMessage(ErrorCodes.InvalidAuthenticationInfo.userMessage);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
