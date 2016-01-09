package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.doctor.attributes.Comment;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 12/21/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/doctor/{doctorID}/institution")
public class DoctorInstitutionController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<Institution>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable(value = "doctorID") Integer doctorID){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if(task!=null){
            Set<Institution> products = task.getInstitutions();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(products);
            return new ResponseEntity<Response<Set<Institution>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestBody List<Integer> list,
                                        @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "doctorID") Integer doctorID) {
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor doctor = manageTask.getDoctorByID(doctorID);
        if (doctor != null) {
            boolean allInstitutionExists = true;
            ManageInstitution manageInstitution = new ManageInstitution(country);
            Set<Institution> set = doctor.getInstitutions();
            if(set==null) set = new HashSet<>();
            for(Integer id : list){
                Institution institution = manageInstitution.getInstitutionByID(id);
                if(institution!=null)
                    set.add(institution);
                else
                    allInstitutionExists = false;
            }
            doctor.setInstitutions(set);
            if(allInstitutionExists) {
                for(Integer id : list){
                    manageTask.addInstitutionDoctor(id, doctorID);
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
                                           @PathVariable(value = "doctorID") Integer doctorID) {
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if (task != null) {
            for (Integer id : list) {
                manageTask.deleteInstitutionDoctor(id, doctorID);
            }
            if (manageTask.updateDoctor(task)) {
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
