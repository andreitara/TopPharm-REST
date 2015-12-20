package md.pharm.restservice.service.task.attributes;

import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.task.attributes.*;
import md.pharm.util.ErrorCodes;
import md.pharm.util.Response;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Andrei on 12/19/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "/toppharm/v1/task/{taskID}/sample/")
public class SampleTaskController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<Sample>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                        @PathVariable(value = "taskID") Integer taskID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            Set<Sample> products = task.getSamples();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(products);
            return new ResponseEntity<Response<Set<Sample>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<Sample>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{sampleID}", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "taskID") Integer taskID,
                                        @PathVariable(value = "sampleID") Integer sampleID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            SampleManage manageProduct = new SampleManage(country);
            Sample product = manageProduct.getByID(sampleID);
            if(product!=null) {
                task.getSamples().add(product);
                manageTask.updateTask(task);
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{sampleID}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "taskID") Integer taskID,
                                           @PathVariable(value = "sampleID") Integer sampleID) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if (task != null) {
            Set<Sample> memos = task.getSamples();
            Sample removeMemo = null;
            for (Sample memo : memos) {
                if (memo.getId().equals(sampleID)) {
                    removeMemo = memo;
                }
            }
            if(removeMemo!=null){
                memos.remove(removeMemo);
                task.setSamples(memos);
                if (manageTask.updateTask(task)) {
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
