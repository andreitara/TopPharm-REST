package md.pharm.restservice.service.institution;

import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.institution.ManageOffice;
import md.pharm.hibernate.institution.Office;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Andrei on 10/6/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "/toppharm/medical/institution/{institutionID}/address")
public class InstitutionAddressController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Response<Address>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable("institutionID") Integer institutionID){
        Response response = new Response<Address>();
        ManageInstitution manageInstitution = new ManageInstitution(country);
        Institution institution = manageInstitution.getInstitutionByID(institutionID);
        if(institution!=null){
            Address address = manageInstitution.getInstitutionAddressByInstitutionID(institutionID);
            if(address != null) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                response.setObject(address);
                return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
            return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response<Integer>> create(@RequestBody Address address, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable("institutionID") Integer institutionID) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Address>().getViolations(address);
        if(violations.size()==0) {
            ManageInstitution manageInstitution = new ManageInstitution(country);
            Institution institution = manageInstitution.getInstitutionByID(institutionID);
            if (institution != null) {
                Address addressFromDB = manageInstitution.getInstitutionAddressByInstitutionID(institutionID);
                if (addressFromDB == null) {
                    address.setInstitution(institution);
                    Integer id = manageInstitution.addInstitutionAddress(address);
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
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - address exists");
                    return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
                return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
            }
        }else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
            response.setViolations(violations);
            return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Response> update(@RequestBody Address address, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable("institutionID") Integer institutionID) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Address>().getViolations(address);
        if(violations.size()==0) {
            ManageInstitution manageInstitution = new ManageInstitution(country);
            Institution institution = manageInstitution.getInstitutionByID(institutionID);
            if (institution != null) {
                Address addressFromDB = manageInstitution.getInstitutionAddressByInstitutionID(institutionID);
                if (addressFromDB != null) {
                    address.setInstitution(institution);
                    institution.setAddress(null);
                    address.setId(addressFromDB.getId());
                    if (manageInstitution.updateAddress(address)) {
                        response.setResponseCode(ErrorCodes.OK.name);
                        response.setResponseMessage(ErrorCodes.OK.userMessage);
                        return new ResponseEntity<Response>(response, HttpStatus.CREATED);
                    } else {
                        response.setResponseCode(ErrorCodes.InternalError.name);
                        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                        return new ResponseEntity<Response>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - address not exists");
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
            response.setViolations(violations);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }
}
