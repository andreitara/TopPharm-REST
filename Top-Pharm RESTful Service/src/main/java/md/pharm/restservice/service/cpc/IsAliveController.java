package md.pharm.restservice.service.cpc;

import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andrei on 1/4/2016.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/report")
public class IsAliveController {

    @RequestMapping(value = "/isAlive", method = RequestMethod.GET)
    public ResponseEntity<Integer> getCPC() {
        Response response = new Response();
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        response.setObject(true);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);

    }

}
