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
        	
    	    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    	    
    	    logger.setLevel(Level.INFO);
    	    FileHandler fileTxt = new FileHandler("C:\\Users\\mauma\\workspace\\ProjetoInter\\src\\Logging.txt");

    	    // create a TXT formatter
    	    SimpleFormatter formatterTxt = new SimpleFormatter();
    	    fileTxt.setFormatter(formatterTxt);
    	    logger.addHandler(fileTxt);
    	    
    	    logger.log(Level.INFO, tipoPessoaField);
    	    logger.log(Level.INFO, CGCField);
    	    final String cgc = BeanUtils.getProperty(object, CGCField);
    	    logger.log(Level.INFO, cgc);
    	    
    	    String tipoPessoaString = (BeanUtils.getProperty(object, tipoPessoaField) == null) ? "Fisica" : BeanUtils.getProperty(object, tipoPessoaField);
    	    
    	    logger.log(Level.INFO, tipoPessoaString);
    	    
    	    final TipoPessoa tipoPessoa = TipoPessoa.valueOf(tipoPessoaString);
    	    logger.log(Level.INFO, tipoPessoa.toString());
    	    
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
	            		logger.log(Level.INFO, "1");
	            		return CGCFormatter.CNPJFORMATED.matcher(cgc).matches();
	            	}else{
	            		logger.log(Level.INFO, "2");
	            		return CGCFormatter.CNPJUNFORMATED.matcher(cgc).matches();
	            	}
	        default:
	    	    constraintContext.buildConstraintViolationWithTemplate( "CNPJ/CPF Inválido!"  ).addConstraintViolation();
	    	    return false;	    	    
            }  
        }
        catch (final Exception ignore)
        {
            return false;
        }
        
        
    }
}