package model.bean.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import java.util.logging.*;

import model.Pessoa;
import model.bean.formatters.CGCFormatter;
import model.enums.TipoPessoa;

public class CheckCGCValidator implements ConstraintValidator<CheckCGC, Object> {
	private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME );
	private String tipoPessoaField;
	private String CGCField;

    @Override
    public void initialize(CheckCGC constraintAnnotation) {
    	this.tipoPessoaField = constraintAnnotation.tipoPessoa();
    	this.CGCField = constraintAnnotation.CGC();
    }

    @Override
    public boolean isValid(final Object object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }
        
        try
        {
        	
    	    final String cgc = BeanUtils.getProperty(object, CGCField);    	    
    	    
    	    String tipoPessoaString = (BeanUtils.getProperty(object, tipoPessoaField) == null) ? "Fisica" : BeanUtils.getProperty(object, tipoPessoaField);
    	    final TipoPessoa tipoPessoa = TipoPessoa.valueOf(tipoPessoaString);
    	    
    	    if(cgc == null){
    	    	return false;
    	    }else if(cgc.isEmpty()){
    	    	return false;
    	    }

            //cgc.equals("62.413.459/0001-74");
    	    constraintContext.disableDefaultConstraintViolation();
            switch(tipoPessoa){
	            case Fisica:
	            	constraintContext
		                .buildConstraintViolationWithTemplate( "CPF Inválido!" )
		                .addConstraintViolation();
	            	
	            	if(new CGCFormatter(tipoPessoa).isFormatted(cgc)){
	            		return CGCFormatter.CPFFORMATED.matcher(cgc).matches();
	            	}else{
	            		
	            		return CGCFormatter.CPFUNFORMATED.matcher(cgc).matches();
	            	}

	            case Juridica:
	            	constraintContext
	                	.buildConstraintViolationWithTemplate( "CNPJ Inválido!" )
	                	.addConstraintViolation();
	            	
	            	if(new CGCFormatter(tipoPessoa).isFormatted(cgc)){
	            		return CGCFormatter.CNPJFORMATED.matcher(cgc).matches();
	            	}else{
	            		return CGCFormatter.CNPJUNFORMATED.matcher(cgc).matches();
	            	}
	        default:
	    	    constraintContext.buildConstraintViolationWithTemplate( "CNPJ/CPF Inválido!"  ).addConstraintViolation();
	    	    return false;	    	    
            }  
        }
        catch (final Exception ignore)
        {
        	ignore.printStackTrace();
            return false;
        }
        
        
    }
}