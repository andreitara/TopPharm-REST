package md.pharm.restservice.service.doctor.attributes;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.doctor.attributes.Habit;
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
@RequestMapping(value = StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/medical/doctor/{doctorID}/habit/")
public class HabitDoctorController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<Habit>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                       @PathVariable("doctorID") Integer doctorID){
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if(task!=null){
            Set<Habit> products = task.getHabits();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(products);
            return new ResponseEntity<Response<Set<Habit>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<Habit>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestBody Habit habit,
                                        @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "doctorID") Integer doctorID) {
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor task = manageTask.getDoctorByID(doctorID);
        if (task != null) {
            habit.setDoctor(task);
            Set<Habit> memos = task.getHabits();
            memos.add(habit);
            task.setHabits(memos);
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

    @RequestMapping(value = "/delete/{habitID}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "doctorID") Integer taskID,
                                           @PathVariable(value = "habitID") Integer habitID) {
        Response response = new Response();
        ManageDoctor manageTask = new ManageDoctor(country);
        Doctor doctor = manageTask.getDoctorByID(taskID);
        if (doctor != null) {
            Set<Habit> memos = doctor.getHabits();
            Habit removeMemo = null;
            for (Habit memo : memos) {
                if (memo.getId().equals(habitID)) {
                    removeMemo = memo;
                }
            }
            if(removeMemo!=null){
                memos.remove(removeMemo);
                doctor.setHabits(memos);
                if (manageTask.updateDoctor(doctor)) {
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
