package md.pharm.restservice.service.product;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Product;
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
 * Created by Andrei on 10/6/2015.
 */

@RestController
@RequestMapping(StaticStrings.PORT_FOR_ALL_CONTROLLERS + "toppharm/medical/product")
public class ProductController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country){
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct(country);
        List<Product> list = manageProduct.getProducts();
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(list);
            //response.addMapItem("products", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestBody Product product) {
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Product>().getViolations(product);
        if (violations.size() == 0) {
            ManageProduct manage = new ManageProduct(country);
            if (product.getId() == null) {
                if (true) {//TODO condition if not exists this doctor in DB
                    Integer id = manage.addProduct(product);
                    if (id != null) {
                        response.setResponseCode(ErrorCodes.Created.name);
                        response.setResponseMessage(ErrorCodes.Created.userMessage);
                        response.setObject(id);
                        //product.setId(id);
                        //response.addMapItem("product", product);
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
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            response.setViolations(violations);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @RequestBody Product product){
        Response response = new Response();
        Set<Violation> violations = new ValidatorUtil<Product>().getViolations(product);
        if(violations.size()==0) {
            ManageProduct manage = new ManageProduct(country);
            if (product.getId() != null) {
                Product productFromDB = manage.getProductByID(product.getId());
                if (productFromDB != null) {
                    if (manage.updateProduct(product)) {
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
    public ResponseEntity<?> delete(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") Integer id) {
        Response response = new Response();
        ManageProduct manage = new ManageProduct(country);
        Product product = manage.getProductByID(id);
        if (product != null) {
            if (manage.delete(product)) {
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
    public ResponseEntity<?> get(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country, @PathVariable(value = "id") Integer id){
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct(country);
        Product product = manageProduct.getProductByID(id);
        if(product!=null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(product);
            //response.addMapItem("product", product);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }


    //GET PRODUCTS
    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCategory(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                              @PathVariable(value = "category") String category) {
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct(country);
        List<Product> list = manageProduct.getProductsByPartOfCategory(category);
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

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByName(@RequestHeader(value = StaticStrings.HEADER_COUNTRY) String country,
                                          @PathVariable(value = "name") String name) {
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct(country);
        List<Product> list = manageProduct.getProductsByPartOfName(name);
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
