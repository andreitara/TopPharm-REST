package md.pharm.restservice.service.message;

import md.pharm.hibernate.message.ManageMessage;
import md.pharm.hibernate.message.Message;
import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by c-andrtara on 10/19/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/message")
public class MessageController {

    @RequestMapping(value = "from/{fromID}/to/{toID}/add", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                    @PathVariable(value = "fromID") Integer fromID,
                                    @PathVariable(value = "toID") Integer toID,
                                    @RequestBody Message message) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Message>().getViolations(message);
        if (violations.size() == 0) {
            ManageUser manageUser = new ManageUser(country);
            User from = manageUser.getUserByID(fromID);
            User to = manageUser.getUserByID(toID);
            if (from != null && to != null) {
                ManageMessage manage = new ManageMessage(country);
                if (message.getId() == null) {
                    if (true) {//TODO condition if not exists this doctor in DB
                        message.setDate(Calendar.getInstance().getTime());
                        message.setFrom(from);
                        message.setTo(to);
                        Integer id = manage.addMessage(message);
                        if (id != null) {
                            response.setResponseCode(ErrorCodes.Created.name);
                            response.setResponseMessage(ErrorCodes.Created.userMessage);
                            response.setObject(id);
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
                } else {
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "from/{fromID}/to/{toID}/start/{start}/end/{end}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFromStartToEnd(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                  @PathVariable(value = "fromID") Integer fromID,
                                                  @PathVariable(value = "toID") Integer toID,
                                                  @PathVariable(value = "start") @DateTimeFormat(pattern="yyyyMMdd") Date start,
                                                  @PathVariable(value = "end") @DateTimeFormat(pattern="yyyyMMdd") Date end) {
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        List<Message> messages = manageMessage.getMessagesFromDateToDate(fromID, toID, start, end);
        if (messages != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(messages);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "user/{user1ID}/user/{user2ID}/start/{start}/end/{end}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBidirectionalFromStartToEnd(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                               @PathVariable(value = "user1ID") Integer user1ID,
                                                               @PathVariable(value = "user2ID") Integer user2ID,
                                                               @PathVariable(value = "start") @DateTimeFormat(pattern="yyyyMMdd") Date start,
                                                               @PathVariable(value = "end") @DateTimeFormat(pattern="yyyyMMdd") Date end) {
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        List<Message> messages = manageMessage.getMessagesFromDateToDateBidirectional(user1ID, user2ID, start, end);
        if (messages != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(messages);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
