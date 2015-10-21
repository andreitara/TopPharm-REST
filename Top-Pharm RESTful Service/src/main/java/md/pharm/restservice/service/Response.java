package md.pharm.restservice.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import md.pharm.hibernate.product.Objective;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.validator.Violation;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Andrei on 9/24/2015.
 */
public class Response<T>{
    private String responseCode;
    private String responseMessage;
    private Set<Violation> violations;
    private Object object;
    @JsonIgnore
    private Map<String, Object> map;

    public Response(){
        map = new HashMap<String, Object>();
    }

    public Response(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public void addMapItem(String name, Object object){
        map.put(name, object);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Set<Violation> getViolations() {
        return violations;
    }

    public void setViolations(Set<Violation> violations) {
        this.violations = violations;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (responseCode != null ? !responseCode.equals(response.responseCode) : response.responseCode != null)
            return false;
        if (responseMessage != null ? !responseMessage.equals(response.responseMessage) : response.responseMessage != null)
            return false;
        return !(object != null ? !object.equals(response.object) : response.object != null);

    }

    @Override
    public int hashCode() {
        int result = responseCode != null ? responseCode.hashCode() : 0;
        result = 31 * result + (responseMessage != null ? responseMessage.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }
}
