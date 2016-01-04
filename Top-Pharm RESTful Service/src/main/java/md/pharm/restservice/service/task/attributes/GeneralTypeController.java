package md.pharm.restservice.service.task.attributes;

import md.pharm.hibernate.doctor.attributes.GeneralType;
import md.pharm.hibernate.doctor.attributes.ManageGeneralType;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 12/20/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/generaltype/")
public class GeneralTypeController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<GeneralType>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending) {
        Response response = new Response<>();
        ManageGeneralType manageDoctor = new ManageGeneralType(country);
        List<GeneralType> list = manageDoctor.getAll(byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response<Integer>> create(@RequestBody GeneralType generalType,
                                                    @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response<Integer>();
        Set<Violation> violations = new ValidatorUtil<GeneralType>().getViolations(generalType);
        if (violations.size() == 0) {
            ManageGeneralType manage = new ManageGeneralType(country);
            if (generalType.getId() == null) {
                if (true) {//TODO condition if not exists this doctor in DB
                    Integer id = manage.add(generalType);
                    if (id != null) {
                        response.setResponseCode(ErrorCodes.Created.name);
                        response.setResponseMessage(ErrorCodes.Created.userMessage);
                        response.setObject(id);
                        return new ResponseEntity<Response<Integer>>(response, HttpStatus.CREATED);
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
            response.setViolations(violations);
            return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Response> update(@RequestBody GeneralType generalType,
                                           @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<GeneralType>().getViolations(generalType);
        if (violations.size() == 0) {
            ManageGeneralType manage = new ManageGeneralType(country);
            if (generalType.getId() != null) {
                GeneralType doctorFromDB = manage.getByID(generalType.getId());
                if (doctorFromDB != null) {
                    if (manage.update(generalType)) {
                        response.setResponseCode(ErrorCodes.OK.name);
                        response.setResponseMessage(ErrorCodes.OK.userMessage);
                        return new ResponseEntity<Response>(response, HttpStatus.OK);
                    } else {
                        response.setResponseCode(ErrorCodes.InternalError.name);
                        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                        return new ResponseEntity<Response>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                    response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageGeneralType manage = new ManageGeneralType(country);
        GeneralType doctor = manage.getByID(id);
        if (doctor != null) {
            if (manage.delete(doctor)) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<GeneralType>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                     @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageGeneralType manageDoctor = new ManageGeneralType(country);
        GeneralType doctor = manageDoctor.getByID(id);
        if (doctor != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(doctor);
            return new ResponseEntity<Response<GeneralType>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response<GeneralType>>(response, HttpStatus.OK);
        }
    }

}
