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
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/report")
public class CPCController {

    @RequestMapping(value = "/cpc/{userID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<CPCCustomer>>> getCPC(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable("userID") Integer userID,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List<CPCCustomer> list = manageTask.getCPC(userID, byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<CPCCustomer>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<CPCCustomer>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/statistics/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<CPCRepresentative>>> getUserStatistics(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        List<CPCCustomer> list = manageTask.getUserStatistics(byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<CPCRepresentative>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<CPCRepresentative>>>(response, HttpStatus.OK);
        }
    }


}
