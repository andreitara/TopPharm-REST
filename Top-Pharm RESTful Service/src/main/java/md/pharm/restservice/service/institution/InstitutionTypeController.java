package md.pharm.restservice.service.institution;

import md.pharm.hibernate.doctor.attributes.ManageSpeciality;
import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.hibernate.institution.attributes.InstitutionType;
import md.pharm.hibernate.institution.attributes.ManageInstitutionType;
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
 * Created by Andrei on 12/23/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/institutiontype/")
public class InstitutionTypeController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<InstitutionType>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                  @PathVariable("byField") String byField,
                                                                  @PathVariable("ascending") boolean ascending) {
        Response response = new Response<>();
        ManageInstitutionType manageDoctor = new ManageInstitutionType(country);
        List<InstitutionType> list = manageDoctor.getInstitutions(byField, ascending);
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
    public ResponseEntity<Response<Integer>> create(@RequestBody InstitutionType speciality,
                                                    @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response<Integer>();
        Set<Violation> violations = new ValidatorUtil<InstitutionType>().getViolations(speciality);
        if (violations.size() == 0) {
            ManageInstitutionType manage = new ManageInstitutionType(country);
            if (speciality.getId() == null) {
                if (true) {//TODO condition if not exists this doctor in DB
                    Integer id = manage.addInstitution(speciality);
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
    public ResponseEntity<Response> update(@RequestBody InstitutionType speciality,
                                           @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<InstitutionType>().getViolations(speciality);
        if (violations.size() == 0) {
            ManageInstitutionType manage = new ManageInstitutionType(country);
            if (speciality.getId() != null) {
                InstitutionType doctorFromDB = manage.getInstitutionByID(speciality.getId());
                if (doctorFromDB != null) {
                    if (manage.updateInstitution(speciality)) {
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
        ManageInstitutionType manage = new ManageInstitutionType(country);
        InstitutionType doctor = manage.getInstitutionByID(id);
        if (doctor != null) {
            if (manage.deleteInstitution(doctor)) {
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
    public ResponseEntity<Response<InstitutionType>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                         @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageInstitutionType manageDoctor = new ManageInstitutionType(country);
        InstitutionType doctor = manageDoctor.getInstitutionByID(id);
        if (doctor != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(doctor);
            return new ResponseEntity<Response<InstitutionType>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response<InstitutionType>>(response, HttpStatus.OK);
        }
    }
}
