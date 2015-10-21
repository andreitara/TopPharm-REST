package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/5/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/medical/doctor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DoctorController {

    @RequestMapping(value = "/all", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<Doctor>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response<Doctor>();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctors();
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            //response.addMapItem("doctors", list);
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Integer>> create(@RequestBody Doctor doctor, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response<Integer>();
        Set<Violation> violations = new ValidatorUtil<Doctor>().getViolations(doctor);
        if (violations.size() == 0) {
            ManageDoctor manage = new ManageDoctor(country);
            if (doctor.getId() == null) {
                if (true) {//TODO condition if not exists this doctor in DB
                    Integer id = manage.addDoctor(doctor);
                    if (id != null) {
                        response.setResponseCode(ErrorCodes.Created.name);
                        response.setResponseMessage(ErrorCodes.Created.userMessage);
                        response.setObject(id);
                        //doctor.setId(id);
                        //response.addMapItem("doctor", doctor);
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
            }
            {
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
    public ResponseEntity<Response> update(@RequestBody Doctor doctor, @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Doctor>().getViolations(doctor);
        if (violations.size() == 0) {
            ManageDoctor manage = new ManageDoctor(country);
            if (doctor.getId() != null) {
                Doctor doctorFromDB = manage.getDoctorByID(doctor.getId());
                if (doctorFromDB != null) {
                    if (manage.updateDoctor(doctor)) {
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
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageDoctor manage = new ManageDoctor(country);
        Doctor doctor = manage.getDoctorByID(id);
        if (doctor != null) {
            if (manage.deleteDoctor(doctor)) {
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
    public ResponseEntity<Response<Doctor>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        Doctor doctor = manageDoctor.getDoctorByID(id);
        if (doctor != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(doctor);
            return new ResponseEntity<Response<Doctor>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response<Doctor>>(response, HttpStatus.OK);
        }
    }


    //GET DOCTORS
    @RequestMapping(value = "/speciality/{speciality}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAllBySpeciality(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                @PathVariable(value = "speciality") String speciality) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctorsBySpeciality(speciality);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/institution/{institutionID}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAllBynstitutionID(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                  @PathVariable(value = "institutionID") Integer institutionID) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctorsByInstitutionID(institutionID);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAllPartOfName(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                              @PathVariable(value = "name") String name) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = null;
        String[] names = name.trim().replace("( )+", " ").split(" ");
        if (names.length == 1) {
            list = manageDoctor.getDoctorsByPartOfFirstName(names[0]);
            list.addAll(manageDoctor.getDoctorsByPartOfLastName(names[0]));
        } else if (names.length == 2) {
            list = manageDoctor.getDoctorsByPartOfFirstAndLastName(names[0],names[1]);
            list.addAll(manageDoctor.getDoctorsByPartOfFirstAndLastName(names[1],names[0]));
        } else if (names.length == 3) {
            list = manageDoctor.getDoctorsByPartOfFirstLastAndFatherName(names[0],names[1],names[2]);
            list.addAll(manageDoctor.getDoctorsByPartOfFirstLastAndFatherName(names[1],names[0],names[2]));
        } else {
            list = new ArrayList<>();
        }
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
        }
    }
}
