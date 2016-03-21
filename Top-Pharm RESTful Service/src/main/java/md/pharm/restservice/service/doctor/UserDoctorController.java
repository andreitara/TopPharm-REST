package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andrei on 12/21/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/user/{userID}/doctor")
public class UserDoctorController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable(value = "userID") Integer userID,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List list = manageTask.getAllDoctorsByUserID(userID, byField, ascending);
        if (list !=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/speciality/{specialityID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAllByType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                    @PathVariable(value = "userID") Integer userID,
                                                                    @PathVariable(value = "specialityID") Integer specialityID,
                                                                    @PathVariable("byField") String byField,
                                                                    @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List list = manageTask.getAllDoctorsByUserIDandSpecialityID(userID, specialityID, byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }

}
