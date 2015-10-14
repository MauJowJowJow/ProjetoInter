package model.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marca que um camp deve ter seu valor validado com a respectiva propriedade 
 * bindada a ele no bean
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 17/09/2013
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateValue {
    
    /**
     * A propriedade do bean que corresponde ao campo a ser validado
     * 
     * @return o nome da propriedade
     */
    String property();
}
