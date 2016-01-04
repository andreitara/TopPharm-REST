package md.pharm.restservice.service.task;

import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.util.Response;
import md.pharm.util.ErrorCodes;
import md.pharm.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/10/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "/toppharm/v1/task/{taskID}/product/")
public class ProductTaskController {

    @RequestMapping(value = "/all/{byField}/{ascending}", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Product>>> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                                         @PathVariable(value = "taskID") Integer taskID,
                                                         @PathVariable("byField") String byField,
                                                         @PathVariable("ascending") boolean ascending){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        List<Product> list = manageTask.getProductsByTaskID(taskID, byField, ascending);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            return new ResponseEntity<Response<List<Product>>>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Response<List<Product>>>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{productID}", method = RequestMethod.POST)
    public ResponseEntity<Response> add(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                        @PathVariable(value = "taskID") Integer taskID,
                                        @PathVariable(value = "productID") Integer productID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageProduct manageProduct = new ManageProduct(country);
            Product product = manageProduct.getProductByID(productID);
            if(product!=null) {
                task.getProducts().add(product);
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

    @RequestMapping(value = "/delete/{productID}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                           @PathVariable(value = "taskID") Integer taskID,
                                           @PathVariable(value = "productID") Integer productID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask(country);
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageProduct manageProduct = new ManageProduct(country);
            Product product = manageProduct.getProductByID(productID);
            if(product!=null) {
                if(manageTask.deleteProductTask(taskID, productID)) {
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
