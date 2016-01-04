package md.pharm.restservice.service.task;

import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.task.TaskComment;
import md.pharm.hibernate.task.TaskHistory;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.validator.ValidatorUtil;
import md.pharm.hibernate.validator.Violation;
import md.pharm.util.Response;
import md.pharm.util.DateUtil;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/10/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/v1/task")
public class TaskController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                       @PathVariable("byField") String byField,
                                                       @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasks(byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response<Integer>> create(@RequestBody Task task,
                                                    @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                                    @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Task>().getViolations(task);
        if (violations.size() == 0) {
            ManageTask manage = new ManageTask(country);
            ManageUser manageUser = new ManageUser(country);
            if (task.getId() == null) {
                if (true) {//TODO condition if not exists this task in DB
                    if (true) {
                        Integer id = manage.addTask(task);
                        if (id != null) {
                            task.setId(id);
                            User user = manageUser.getUserByUsername(username);
                            TaskHistory taskHistory = new TaskHistory(task, Calendar.getInstance().getTime(), "Task was created by " + user.getName());
                            manage.addTaskHistory(taskHistory);
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
                        response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " Start Date must be after Current Date and before End Date.");
                        return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
                    response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage + " Task already exists.");
                    return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " Task contains ID.");
                return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " Task contains violations.");
            response.setViolations(violations);
            return new ResponseEntity<Response<Integer>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Response> update(@RequestBody Task task,
                                           @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                           @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Task>().getViolations(task);
        if (violations.size() == 0) {
            ManageTask manage = new ManageTask(country);
            ManageUser manageUser = new ManageUser(country);
            if (task.getId() != null) {
                Task taskFromDB = manage.getTaskByID(task.getId());
                User user = manageUser.getUserByUsername(username);
                if (taskFromDB != null) {
                    if (true) {
                        task.setAttendees(taskFromDB.getAttendees());
                        task.setInstitution(taskFromDB.getInstitution());
                        task.setProducts(taskFromDB.getProducts());
                        if (manage.updateTask(task)) {
                            TaskHistory taskHistory = new TaskHistory(task, Calendar.getInstance().getTime(), "Task was updated by " + user.getName());
                            manage.addTaskHistory(taskHistory);
                            response.setResponseCode(ErrorCodes.OK.name);
                            response.setResponseMessage(ErrorCodes.OK.userMessage);
                            return new ResponseEntity<Response>(response, HttpStatus.OK);
                        } else {
                            response.setResponseCode(ErrorCodes.InternalError.name);
                            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                            return new ResponseEntity<Response>(response, HttpStatus.OK);
                        }
                    } else {
                        response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                        response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " Start Date must be after Current Date and before End Date.");
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
                                           @RequestHeader(value = StaticStrings.HEADER_USERNAME) String username,
                                           @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageTask manage = new ManageTask(country);
        ManageUser manageUser = new ManageUser(country);
        User user = manageUser.getUserByUsername(username);
        Task taskFromDB = manage.getTaskByID(id);
        if (taskFromDB != null) {
            if (true) {
                if (manage.deleteTask(taskFromDB)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " Task is not from current week");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Response<Task>> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                              @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(id);
        if (task != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(task);
            return new ResponseEntity<Response<Task>>(response, HttpStatus.CREATED);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Response<Task>>(response, HttpStatus.OK);
        }
    }

    //####################################################################################
    //GET
    //####################################################################################
    @RequestMapping(value = "/all/category/{category}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByCategory(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                 @PathVariable("category") String category,
                                                                 @PathVariable("byField") String byField,
                                                                 @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByCategory(category, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/submitted/{isSubmitted}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllBySubmitted(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                  @PathVariable("isSubmitted") boolean isSubmitted,
                                                                  @PathVariable("byField") String byField,
                                                                  @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksBySubmitted(isSubmitted, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/capital/{isCapital}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByCapital(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                @PathVariable("isCapital") boolean isCapital,
                                                                @PathVariable("byField") String byField,
                                                                @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByCapital(isCapital, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/type/{typeID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                 @PathVariable("typeID") Integer typeID,
                                                                 @PathVariable("byField") String byField,
                                                                 @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByType(typeID, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/customer/{customerID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByCustomer(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                 @PathVariable("customerID") Integer customerID,
                                                                 @PathVariable("byField") String byField,
                                                                 @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByCustomer(customerID, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    //####################################################################################
    //GET TASKS BY USER
    //####################################################################################
    @RequestMapping(value = "/all/user/{userID}/{byField}/{ascending}", method = RequestMethod.GET)
     public ResponseEntity<Response<List<Task>>> getAllByUser(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                              @PathVariable("userID") Integer userID,
                                                              @PathVariable("byField") String byField,
                                                              @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByUser(userID, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/user/{userID}/from/{fromDate}/to/{toDate}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByUserFromDateThruDate(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                             @PathVariable("userID") Integer userID,
                                                                             @PathVariable(value = "fromDate") @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date fromDate,
                                                                             @PathVariable(value = "toDate")   @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date toDate,
                                                                             @PathVariable("byField") String byField,
                                                                             @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksFromDateToDate(userID, fromDate, toDate, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/user/{userID}/category/{category}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByUserByCategory(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                       @PathVariable("userID") Integer userID,
                                                                       @PathVariable("category") String category,
                                                                       @PathVariable("byField") String byField,
                                                                       @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByUserByCategory(userID, category, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/user/{userID}/capital/{isCapital}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByUserByCapital(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                       @PathVariable("userID") Integer userID,
                                                                       @PathVariable("isCapital") boolean isCapital,
                                                                       @PathVariable("byField") String byField,
                                                                       @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByUserByCapital(userID, isCapital, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/user/{userID}/submitted/{isSubmitted}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByUserBySubmitted(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                      @PathVariable("userID") Integer userID,
                                                                      @PathVariable("isSubmitted") boolean isSubmitted,
                                                                      @PathVariable("byField") String byField,
                                                                      @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByUserBySubmitted(userID, isSubmitted, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/all/user/{userID}/type/{typeID}/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Task>>> getAllByUserByType(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                        @PathVariable("userID") Integer userID,
                                                                        @PathVariable("typeID") Integer typeID,
                                                                        @PathVariable("byField") String byField,
                                                                        @PathVariable("ascending") boolean ascending) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Task> list = manageTask.getTasksByUserByType(userID, typeID, byField, ascending);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Task>>>(response, HttpStatus.OK);
        }
    }


    //####################################################################################
    //Comment and History
    //####################################################################################
    @RequestMapping(value = "/{taskID}/comment/add", method = RequestMethod.POST)
    public ResponseEntity<Response> addComment(@RequestBody TaskComment taskComment,
                                               @RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                               @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageTask manage = new ManageTask(country);
        Task taskFromDB = manage.getTaskByID(id);
        if (taskFromDB != null) {
            if (taskComment != null && taskComment.getComment() != null) {
                taskComment.setDate(Calendar.getInstance().getTime());
                taskComment.setTask(taskFromDB);
                Integer commentID = manage.addTaskComment(taskComment);
                if (id!=null) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    response.setObject(commentID);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " Comment is mandatory");
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage + " Task does not exists");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{taskID}/comment/all", method = RequestMethod.GET)
    public ResponseEntity<Response<List<TaskComment>>> getTaskComments(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                       @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageTask manage = new ManageTask(country);
        List<TaskComment> list = manage.getTaskCommentByTaskID(id);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<TaskComment>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage + " Task does not exists");
            return new ResponseEntity<Response<List<TaskComment>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{taskID}/history/all", method = RequestMethod.GET)
    public ResponseEntity<Response<List<TaskHistory>>> getTaskHistories(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                                        @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageTask manage = new ManageTask(country);
        List<TaskHistory> list = manage.getTaskHistoryByTaskID(id);
        if (list != null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<TaskHistory>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage + " Task does not exists");
            return new ResponseEntity<Response<List<TaskHistory>>>(response, HttpStatus.OK);
        }
    }
}
