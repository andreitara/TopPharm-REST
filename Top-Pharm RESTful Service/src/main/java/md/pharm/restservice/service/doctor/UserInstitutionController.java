package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.Doctor;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 12/21/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/user/{userID}/institution")
public class UserInstitutionController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable(value = "userID") Integer userID,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List list = manageTask.getInstitutionsByUserIDWithLastVisitDate(userID, byField, ascending);
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

    @RequestMapping(value = "/all/type/{typeID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAllByType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                    @PathVariable(value = "userID") Integer userID,
                                                                    @PathVariable(value = "typeID") Integer typeID,
                                                                    @PathVariable("byField") String byField,
                                                                    @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List list = manageTask.getInstitutionsByUserIDWithLastVisitDateByType(userID, typeID, byField, ascending);
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestBody List<Integer> list,
                                        @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "userID") Integer userID) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByID(userID);
        if (user != null) {
            boolean allInstitutionExists = true;
            ManageInstitution manageInstitution = new ManageInstitution(country);
            ManageDoctor manageDoctor = new ManageDoctor(country);
            //List<Institution> set = manageDoctor.getInstitutionsByUserID(userID);
            //if(set==null) set = new ArrayList<>();
            for(Integer id : list){
                Institution institution = manageInstitution.getInstitutionByID(id);
                if(institution!=null) {
                    //set.add(institution);
                }else
                    allInstitutionExists = false;
            }
            if(allInstitutionExists) {
                for(Integer id : list){
                    manageDoctor.addInstitutionUser(id, userID);
                }
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }else {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Response> delete(@RequestBody List<Integer> list,
                                           @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "userID") Integer userID) {
        Response response = new Response();
        ManageUser manageUser = new ManageUser(country);
        ManageDoctor manageTask = new ManageDoctor(country);
        User user = manageUser.getUserByID(userID);
        if (user != null) {
            for (Integer institutionID : list) {
                manageTask.deleteInstitutionUser(institutionID, userID);
            }
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
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }
}
