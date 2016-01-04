package md.pharm.restservice.service.task;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.util.Response;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/17/2015.
 */
@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "/toppharm/v1/user/{userID}/doctor/")
public class DoctorUserController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<Doctor>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                        @PathVariable(value = "userID") int userID,
                                                        @PathVariable("byField") String byField,
                                                        @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        List<Doctor> list = manageUser.getDoctorsFromUser(userID, byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<Set<Doctor>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<Doctor>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{doctorID}", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                 @PathVariable(value = "userID") int userID,
                                 @PathVariable(value = "doctorID") int doctorID){
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(userID);
        if(user!=null){
            ManageDoctor manageDoctor = new ManageDoctor(country);
            Doctor doctor = manageDoctor.getDoctorByID(doctorID);
            if(doctor!=null) {
                manageUser.updateUserAddDoctor(userID, doctor);
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{doctorID}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
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
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }else{
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

}
