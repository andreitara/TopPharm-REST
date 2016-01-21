package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.doctor.attributes.ManageSpeciality;
import md.pharm.hibernate.doctor.attributes.Speciality;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.util.Response;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/5/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/doctor")
public class DoctorController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                         @PathVariable("byField") String byField,
                                                         @PathVariable("ascending") boolean ascending) {
        Response response = new Response<List<Doctor>>();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctors(byField, ascending);
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
    public ResponseEntity<Response<Integer>> create(@RequestBody Doctor doctor,
                                                    @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response<Integer>();
        Set<Violation> violations = new ValidatorUtil<Doctor>().getViolations(doctor);
        if (violations.size() == 0) {
            ManageDoctor manage = new ManageDoctor(country);
            ManageSpeciality manageSpeciality = new ManageSpeciality(country);
            if (doctor.getId() == null) {
                Speciality speciality = doctor.getSpeciality();
                if (speciality==null || (speciality != null && speciality.getId()!=null && manageSpeciality.getByID(speciality.getId())!=null)) {
                    Integer id = manage.addDoctor(doctor);
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
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
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
    public ResponseEntity<Response> update(@RequestBody Doctor doctor,
                                           @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Doctor>().getViolations(doctor);
        if (violations.size() == 0) {
            ManageDoctor manage = new ManageDoctor(country);
            if (doctor.getId() != null) {
                Doctor doctorFromDB = manage.getDoctorByID(doctor.getId());
                if (doctorFromDB != null) {
                    doctor.setPersonalInfos(doctorFromDB.getPersonalInfos());
                    doctor.setHabits(doctorFromDB.getHabits());
                    doctor.setDoctorComments(doctorFromDB.getDoctorComments());
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
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "id") int id) {
        Response response = new Response();
        ManageDoctor manage = new ManageDoctor(country);
        Doctor doctor = manage.getDoctorByID(id);
        if (doctor != null) {
            manage.deleteInstitutionDoctorforDoctorID(id);
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
    public ResponseEntity<Response<Doctor>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                @PathVariable(value = "id") int id) {
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

    @RequestMapping(value = "/latestVisit/{doctorID}/{userID}", method = RequestMethod.GET)
    public ResponseEntity<Response<Doctor>> getLatestVisit(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                           @PathVariable(value = "doctorID") int doctorID,
                                                           @PathVariable(value = "userID") int userID) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        Object doctor = manageDoctor.getLatestVisit(userID, doctorID);
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
    @RequestMapping(value = "/speciality/{specialityID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAllBySpeciality(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                     @PathVariable(value = "specialityID") Integer specialityID,
                                                                     @PathVariable("byField") String byField,
                                                                     @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctorsBySpecialityID(specialityID, byField, ascending);
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

    @RequestMapping(value = "/institution/{institutionID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAllBynstitutionID(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                       @PathVariable(value = "institutionID") Integer institutionID,
                                                                       @PathVariable("byField") String byField,
                                                                       @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctorsByInstitutionID(institutionID, byField, ascending);
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

    @RequestMapping(value = "/name/{name}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Doctor>>> getAllPartOfName(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                   @PathVariable(value = "name") String name,
                                                                   @PathVariable("byField") String byField,
                                                                   @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor(country);
        List<Doctor> list = manageDoctor.getDoctorsByPartOfName(name, byField, ascending);

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
