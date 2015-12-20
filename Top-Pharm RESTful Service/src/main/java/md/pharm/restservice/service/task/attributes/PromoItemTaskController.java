package md.pharm.restservice.service.task.attributes;

import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.task.attributes.NextObjective;
import md.pharm.hibernate.task.attributes.PromoItem;
import md.pharm.hibernate.task.attributes.PromoItemManage;
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
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "/toppharm/v1/task/{taskID}/promoitem/")
public class PromoItemTaskController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Response<Set<PromoItem>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                           @PathVariable(value = "taskID") Integer taskID) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if (task != null) {
            Set<PromoItem> products = task.getPromoItems();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(products);
            return new ResponseEntity<Response<Set<PromoItem>>>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<Set<PromoItem>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{promoitemID}", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "taskID") Integer taskID,
                                        @PathVariable(value = "promoitemID") Integer promoitemID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            PromoItemManage manageProduct = new PromoItemManage(country);
            PromoItem product = manageProduct.getByID(promoitemID);
            if(product!=null) {
                task.getPromoItems().add(product);
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

    @RequestMapping(value = "/delete/{promoitemID}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "taskID") Integer taskID,
                                           @PathVariable(value = "promoitemID") Integer promoitemID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            PromoItemManage manageProduct = new PromoItemManage(country);
            PromoItem product = manageProduct.getByID(promoitemID);
            if(product!=null) {
                if(manageTask.deletePromoItemTask(taskID, promoitemID)) {
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
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }
    }

}
