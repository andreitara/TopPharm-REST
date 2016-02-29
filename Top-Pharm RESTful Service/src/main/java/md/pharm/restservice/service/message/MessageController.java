package md.pharm.restservice.service.message;

import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.message.ManageMessage;
import md.pharm.hibernate.message.Message;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.cpc.CPCCustomer;
import md.pharm.util.Response;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by c-andrtara on 10/19/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/message")
public class MessageController {

    @RequestMapping(value = "/representative/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Representative>>> getRepresentatives(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                            @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                                                            @PathVariable("byField") String byField,
                                                                            @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByUsername(username);
        LocalDate localDate = new LocalDate();
        LocalTime localTime = new LocalTime();
        List<CPCCustomer> list = manageMessage.getAllRepresentatives(user.getId(), localDate.toString("yyyy-MM-dd" + " " + localTime.toString("HH:mm:ss")) , byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Representative>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Representative>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/representative/unreadMessages", method = RequestMethod.GET)
    public ResponseEntity<Response<BigInteger>> hasUnreadMessages(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                               @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        ManageMessage manageMessage = new ManageMessage(country);
        User user = manageUser.getUserByUsername(username);
        BigInteger number = manageMessage.getNumberOfUnreadMessages(user.getId());
        if(number!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(number);
            return new ResponseEntity<Response<BigInteger>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<BigInteger>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "to/{toID}/add", method = RequestMethod.POST)
    public ResponseEntity<Response<Integer>> create(@RequestBody Message message,
                                                    @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                    @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                                    @PathVariable(value = "toID") Integer toID) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Message>().getViolations(message);
        if (violations.size() == 0) {
            ManageUser manageUser = new ManageUser(country);
            User from = manageUser.getUserByUsername(username);
            User to = manageUser.getUserByID(toID);
            if (from != null && to != null) {
                ManageMessage manage = new ManageMessage(country);
                if (message.getId() == null) {
                    if (true) {//TODO condition if not exists this doctor in DB
                        if(message.getDate()==null)
                            message.setDate(Calendar.getInstance().getTime());
                        message.setFrom(from);
                        message.setTo(to);
                        message.setUnread(true);
                        Integer id = manage.addMessage(message);
                        if (id != null) {
                            to.setHasUnreadMessages(true);
                            if(manageUser.updateUser(to)) {
                                response.setResponseCode(ErrorCodes.Created.name);
                                response.setResponseMessage(ErrorCodes.Created.userMessage);
                                response.setObject(id);
                                return new ResponseEntity<Response<Integer>>(response, HttpStatus.CREATED);
                            }else{
                                response.setResponseCode(ErrorCodes.InternalError.name);
                                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                                return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
                            }
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
                } else {
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                    return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "from/{fromID}/{firstResult}/{maxResult}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Message>>> getAllMess(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                                              @PathVariable(value = "fromID") Integer fromID,
                                                              @PathVariable(value = "firstResult") Integer firstResult,
                                                              @PathVariable(value = "maxResult") Integer maxResult) {
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        ManageUser manageUser = new ManageUser(country);
        User to = manageUser.getUserByUsername(username);
        List<Message> messages = manageMessage.getMessagesBidirectional(fromID, to.getId(), firstResult, maxResult);
        for(Message m : messages){
            m.setUnread(false);
            manageMessage.updateMessage(m);
        }
        if (messages != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(messages);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "from/{fromID}/latest/{maxResult}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Message>>> getLatestMess(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                                              @PathVariable(value = "fromID") Integer fromID,
                                                              @PathVariable(value = "maxResult") Integer maxResult) {
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        ManageUser manageUser = new ManageUser(country);
        User to = manageUser.getUserByUsername(username);
        List<Message> messages = manageMessage.getLatestMessagesBidirectional(fromID, to.getId(),maxResult);
        for(Message m : messages){
            m.setUnread(false);
            manageMessage.updateMessage(m);
        }
        if (messages != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(messages);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        }
    }


    //@RequestMapping(value = "from/{fromID}/to/{toID}/start/{start}/end/{end}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Message>>> getAllFromStartToEnd(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                  @PathVariable(value = "fromID") Integer fromID,
                                                  @PathVariable(value = "toID") Integer toID,
                                                  @PathVariable(value = "start") @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date start,
                                                  @PathVariable(value = "end") @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date end) {
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        List<Message> messages = manageMessage.getMessagesFromDateToDate(fromID, toID, start, end);
        if (messages != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(messages);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        }
    }

    //@RequestMapping(value = "user/{user1ID}/user/{user2ID}/start/{start}/end/{end}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Message>>> getAllBidirectionalFromStartToEnd(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                               @PathVariable(value = "user1ID") Integer user1ID,
                                                               @PathVariable(value = "user2ID") Integer user2ID,
                                                               @PathVariable(value = "start") @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date start,
                                                               @PathVariable(value = "end") @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date end) {
        Response response = new Response();
        ManageMessage manageMessage = new ManageMessage(country);
        List<Message> messages = manageMessage.getMessagesFromDateToDateBidirectional(user1ID, user2ID, start, end);
        if (messages != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(messages);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Message>>>(response, HttpStatus.OK);
        }
    }
}
