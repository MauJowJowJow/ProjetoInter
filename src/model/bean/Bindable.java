package model.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jdesktop.beansbinding.Converter;

/**
 * Anotação que define as propriedades de binding para um determinado campo
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 11/09/2013
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bindable {
    
    /**
     * @return a propriedade do bean que será bindada
     */
    String property() default "";
    
    /**
     * @return o nome do evento de binding
     */
    String eventName() default "";
    
    /**
     * @return a EL Expression de bind
     */
    String expression() default "";
    
    /**
     * @return se a origem for nula, uma valor para ser apresentado
     */
    String whenSourceIsNull() default "";
    
    /**
     * @return se o alvo for nulo um valor a ser retornado
     */
    String whenTargetIsNull() default "";
    
    /**
     * @return um converter a ser utilizado para converter os valores
     */
    Class<? extends Converter> converter() default Converter.class;
}
