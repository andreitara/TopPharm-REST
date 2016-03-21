package md.pharm.restservice.service.institution;

import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.util.Response;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by c-andrtara on 10/5/2015.
 */
@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/institution")
public class InstitutionController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutions(byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/latestVisit/{institutionID}/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Response<String>> getLatestVisit(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                           @PathVariable(value = "institutionID") int institutionID,
                                                           @PathVariable(value = "userID") int userID) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        Object date = manageDoctor.getLatestVisitAtInstitution(userID, institutionID);
        if (date != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(date);
            return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response<Integer>> create(@RequestBody Institution institution,
                                                    @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Institution>().getViolations(institution);
        if(violations.size()==0) {
            ManageInstitution manage = new ManageInstitution(country);
            Institution institutionFromDB = manage.getInstitutionByLongName(institution.getLongName());
            if (institutionFromDB == null) {
                Integer id = manage.addInstitution(institution);
                if (id != null) {
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    response.setObject(institution.getId());
                    //response.addMapItem("institution", institution);
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
        }else{
            response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
            response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create/list", method = RequestMethod.POST)
    public ResponseEntity<Response<Map<Integer, Institution>>> createList(@RequestBody List<Institution> list,
                                                        @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Map<Integer, Institution> map = new HashMap<>();
        Map<Institution, Set<Violation>> viol = new HashMap<>();

        boolean flag = true;
        for (Institution institution : list) {
            Set<Violation> violations = new ValidatorUtil<Institution>().getViolations(institution);
            if (violations.size() > 0 || institution.getId() != null) {
                flag = false;
                viol.put(institution, violations);
            }
        }

        if (flag) {
            ManageInstitution manage = new ManageInstitution(country);
            for (Institution institution : list) {
                Institution institutionFromDB = manage.getInstitutionByLongName(institution.getLongName());
                if (institutionFromDB == null) {
                    Integer id = manage.addInstitution(institution);
                    if (id != null)
                        map.put(id, institution);
                }
            }
            response.setResponseCode(ErrorCodes.Created.name);
            response.setResponseMessage(ErrorCodes.Created.userMessage);
            response.setObject(map);
            return new ResponseEntity<Response<Map<Integer, Institution>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
            response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
            response.setObject(viol);
            return new ResponseEntity<Response<Map<Integer, Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Response> createUser(@RequestBody Institution institution,
                                               @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Institution>().getViolations(institution);
        if(violations.size()==0) {
            ManageInstitution manage = new ManageInstitution(country);
            if (institution.getId() > 0) {
                Institution institutionFromDB = manage.getInstitutionByID(institution.getId());
                if (institutionFromDB != null) {
                    if (manage.updateInstitution(institution)) {
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
        }else{
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
        ManageInstitution manageInstitution = new ManageInstitution(country);
        Institution institution = manageInstitution.getInstitutionByID(id);
        if (institution != null) {
            ManageUser manageUser = new ManageUser(country);
            manageUser.deleteInstitutionUserByInstitutionID(id);
            manageUser.deleteInstitutionDoctorByInstitutionID(id);
            if (manageInstitution.deleteInstitution(institution)) {
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
    public ResponseEntity<Response<Institution>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                     @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        Institution institution = manageInstitution.getInstitutionByID(id);
        if (institution != null) {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            response.setObject(institution);
            //response.addMapItem("institution", institution);
            return new ResponseEntity<Response<Institution>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response<Institution>>(response, HttpStatus.OK);
        }
    }


    //GET INSTITUTIONS
    @RequestMapping(value = "/name/{name}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAllByName(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                    @PathVariable(value = "name") String name,
                                                                    @PathVariable("byField") String byField,
                                                                    @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByName(name, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/city/{city}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAllByCity(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                    @PathVariable(value = "city") String city,
                                                                    @PathVariable("byField") String byField,
                                                                    @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByCity(city, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/type/{typeID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAllByType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                    @PathVariable(value = "typeID") Integer typeID,
                                                                    @PathVariable("byField") String byField,
                                                                    @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByType(typeID, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/city/{city}/type/{typeID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Institution>>> getAllByCityAndType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                           @PathVariable(value = "city") String city,
                                                                           @PathVariable(value = "typeID") Integer typeID,
                                                                           @PathVariable("byField") String byField,
                                                                           @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByCityAndType(city, typeID, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Institution>>>(response, HttpStatus.OK);
        }
    }



}
