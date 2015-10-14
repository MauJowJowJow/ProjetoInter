package model.bean.validations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, TYPE, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCGCValidator.class)
@Documented
public @interface CheckCGC{

    //String message() default "{org.hibernate.validator.referenceguide.chapter06.CheckCase." +
	String message() default "CNPJ/CPF inválido!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    String tipoPessoa();
    String CGC();
}