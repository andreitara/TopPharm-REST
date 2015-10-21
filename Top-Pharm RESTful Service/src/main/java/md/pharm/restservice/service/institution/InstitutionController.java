package md.pharm.restservice.service.institution;

import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by c-andrtara on 10/5/2015.
 */
@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/medical/institution")
public class InstitutionController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutions();
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            //response.addMapItem("institutions", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestBody Institution institution) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Institution>().getViolations(institution);
        if(violations.size()==0) {
            ManageInstitution manage = new ManageInstitution(country);
            Institution institutionFromDB = manage.getInstitutionByLongName(institution.getLongName());
            if (institutionFromDB == null) {
                Address address = institution.getAddress();
                if (address != null) address.setInstitution(institution);
                Integer id = manage.addInstitution(institution);
                if (id != null) {
                    if (address != null) address.setInstitution(null);
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    response.setObject(institution.getId());
                    //response.addMapItem("institution", institution);
                    return new ResponseEntity<Object>(response, HttpStatus.CREATED);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
                response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
            response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestBody Institution institution) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Institution>().getViolations(institution);
        if(violations.size()==0) {
            ManageInstitution manage = new ManageInstitution(country);
            if (institution.getId() > 0) {
                Institution institutionFromDB = manage.getInstitutionByID(institution.getId());
                if (institutionFromDB != null) {
                    Address addressFromDB = institutionFromDB.getAddress();
                    addressFromDB.setInstitution(institution);
                    if (addressFromDB != null) {
                        int addressID = institutionFromDB.getAddress().getId();
                        Address address = institution.getAddress();
                        if (address != null) {
                            address.setId(addressID);
                            address.setInstitution(institution);
                        } else {
                            institution.setAddress(addressFromDB);
                        }
                    } else {
                        Address address = institution.getAddress();
                        if (address != null) address.setInstitution(institution);
                    }
                    if (manage.updateInstitution(institution)) {
                        response.setResponseCode(ErrorCodes.OK.name);
                        response.setResponseMessage(ErrorCodes.OK.userMessage);
                        response.addMapItem("institution", institution);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    } else {
                        response.setResponseCode(ErrorCodes.InternalError.name);
                        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                    response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        Institution institution = manageInstitution.getInstitutionByID(id);
        if (institution != null) {
            if (manageInstitution.deleteInstitution(institution)) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        Institution institution = manageInstitution.getInstitutionByID(id);
        if (institution != null) {
            Address address = institution.getAddress();
            if (address != null) address.setInstitution(null);
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            response.setObject(institution);
            //response.addMapItem("institution", institution);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }


    //GET INSTITUTIONS
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByName(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                          @PathVariable(value = "name") String name) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByName(name);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCity(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                          @PathVariable(value = "city") String cityName) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByCity(cityName);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/city/{city}/district/{district}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCityAndDistrict(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country
                                                    ,@PathVariable(value = "city") String city
                                                    ,@PathVariable(value = "district") String district) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByCityAndDistrict(city, district);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByState(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "state") String state) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        List<Institution> list = manageInstitution.getInstitutionsByState(state);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }


}
