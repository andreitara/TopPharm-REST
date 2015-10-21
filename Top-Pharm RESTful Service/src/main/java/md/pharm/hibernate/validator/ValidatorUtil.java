package md.pharm.hibernate.validator;

import md.pharm.hibernate.user.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrei on 10/17/2015.
 */
public class ValidatorUtil<T> {

    static public ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static public Validator validator = factory.getValidator ();

    public Set<ConstraintViolation<T>> validate(T object){
        return validator.validate(object);
    }

    public Set<Violation> getViolations(T object){
        Set<ConstraintViolation<T>> constraintViolations = validate(object);
        Set<Violation> violations = new HashSet<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations){
            violations.add(new Violation(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage(), constraintViolation.getRootBeanClass().getName()));
        }
        return violations;
    }
}
