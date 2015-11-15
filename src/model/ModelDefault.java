package model;

import java.util.Set;

import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class ModelDefault {
	@Transient
	private String errors;

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	public boolean isValid() {	 
	    // valores sao setados no objeto
	 
	    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	 
	    final Set<ConstraintViolation<ModelDefault>> violations = validator.validate(this);
	 
	    setErrors("");
	    if (!violations.isEmpty()) {
	        for (ConstraintViolation<ModelDefault> violation : violations) {
	        	if(!getErrors().isEmpty()){
	        		setErrors(getErrors() + System.lineSeparator());
	        	}
	        	
	        	setErrors(getErrors() + "- " + violation.getMessage());
	        }
	        return false;
	    }
	    return true;
	}

}
