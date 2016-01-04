package md.pharm.restservice.service.cpc;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.doctor.attributes.Comment;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 1/4/2016.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/cpc/")
public class CPCController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<Comment>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                         @PathVariable("byField") String byField,
                                                         @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List<Doctor> list = manageTask.getDoctors(byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<Set<Comment>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<Comment>>>(response, HttpStatus.OK);
        }
    }
}
