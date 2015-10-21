package md.pharm.restservice.service.user;

import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

/**
 * Created by Andrei on 10/18/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/user/{userID}/task/")
public class TaskUserController {

    @RequestMapping(value = "/from/{fromDate}/to/{toDate}",method = RequestMethod.GET)
    public ResponseEntity<?> getTasksByDates(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                     @PathVariable(value = "userID") Integer userID,
                                     @PathVariable(value = "fromDate") @DateTimeFormat(pattern="yyyyMMdd") Date fromDate,
                                     @PathVariable(value = "toDate")   @DateTimeFormat(pattern="yyyyMMdd") Date toDate){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        List<Task> list = manageUser.getTasksFromDateToDate(userID, fromDate, toDate);
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        response.setObject(list);
        return  new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/{status}",method = RequestMethod.GET)
    public ResponseEntity<?> getTasksByStatus(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                             @PathVariable(value = "userID") Integer userID,
                                             @PathVariable(value = "status") String status){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        List<Task> list = manageUser.getTasksByStatus(userID, status);
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        response.setObject(list);
        return  new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/type/{type}",method = RequestMethod.GET)
    public ResponseEntity<?> getTasksByType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                              @PathVariable(value = "userID") Integer userID,
                                              @PathVariable(value = "type") String type){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        List<Task> list = manageUser.getTasksByType(userID, type);
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        response.setObject(list);
        return  new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
