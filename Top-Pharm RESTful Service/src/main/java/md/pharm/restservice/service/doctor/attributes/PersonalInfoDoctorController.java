package md.pharm.restservice.service.doctor.attributes;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.doctor.attributes.PersonalInfo;
import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.task.attributes.Memo;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Andrei on 12/21/2015.
 */

@RestController
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/doctor/{doctorID}/personalinfo/")
public class PersonalInfoDoctorController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<PersonalInfo>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable(value = "doctorID") Integer doctorID){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if(task!=null){
            Set<PersonalInfo> products = task.getPersonalInfos();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(products);
            return new ResponseEntity<Response<Set<PersonalInfo>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<PersonalInfo>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestBody PersonalInfo personalInfo,
                                        @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "doctorID") Integer doctorID) {
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if (task != null) {
            personalInfo.setDoctor(task);
            Set<PersonalInfo> memos = task.getPersonalInfos();
            memos.add(personalInfo);
            task.setPersonalInfos(memos);
            if(manageTask.updateDoctor(task)) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }else {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{personalinfoID}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "doctorID") Integer doctorID,
                                           @PathVariable(value = "personalinfoID") Integer personalinfoID) {
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if (task != null) {
            Set<PersonalInfo> memos = task.getPersonalInfos();
            PersonalInfo removeMemo = null;
            for (PersonalInfo memo : memos) {
                if (memo.getId().equals(personalinfoID)) {
                    removeMemo = memo;
                }
            }
            if(removeMemo!=null){
                memos.remove(removeMemo);
                task.setPersonalInfos(memos);
                if (manageTask.updateDoctor(task)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }
}
