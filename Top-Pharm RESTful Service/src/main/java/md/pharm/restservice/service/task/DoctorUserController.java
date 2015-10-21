package md.pharm.restservice.service.task;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Andrei on 10/17/2015.
 */
@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "/toppharm/user/{userID}/doctor/")
public class DoctorUserController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                    @PathVariable(value = "userID") int userID){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(userID);
        if(user!=null){
            Set<Doctor> doctors = user.getDoctors();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(doctors);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{doctorID}", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                 @PathVariable(value = "userID") int userID,
                                 @PathVariable(value = "doctorID") int doctorID){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(userID);
        if(user!=null){
            ManageDoctor manageDoctor = new ManageDoctor(country);
            Doctor doctor = manageDoctor.getDoctorByID(doctorID);
            if(doctor!=null) {
                user.getDoctors().add(doctor);
                manageUser.updateUser(user);
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{doctorID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                    @PathVariable(value = "userID") int userID,
                                    @PathVariable(value = "doctorID") int doctorID){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(userID);
        if(user!=null){
            ManageDoctor manageDoctor = new ManageDoctor(country);
            Doctor doctor = manageDoctor.getDoctorByID(doctorID);
            if(doctor!=null) {
                if(manageUser.deleteDoctorUser(userID,doctorID)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }else{
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

}
