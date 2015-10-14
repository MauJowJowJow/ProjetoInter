package model.bean;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Validador de formulários
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 17/09/2013
 */
public final class FormValidator {

    /**
     * Valida um formulário de acordo com as anotações do Bean para cada atributo
     * 
     * @param <T> o bean
     * @param form o formulario que contem os campos
     * @param bean o bean que contem as regras de validação
     * 
     * @throws InvalidFormValuesException caso haja algum valor inválido no form
     */
    public static <T> void validateBeanValues(Object form, T bean) throws InvalidFormValuesException {
        // define que o formulario é valido
        boolean valid = true;
        
        // resolve os campos a serem validados
        final List<Field> fields = resolveFields(form.getClass());
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        
        // percorre campo a campo
        for (Field field : fields) {
            
            final ValidateValue validateValue = field.getAnnotation(ValidateValue.class);
            
            // pega a lista de violações
            final Set<ConstraintViolation<T>> violations = 
                    validator.validateProperty(bean, validateValue.property());
            
            if (!violations.isEmpty()) {
                // se há violacoes já diz que o formulario é inválido
                valid = false;
                        
                try {
                    final JComponent component = (JComponent) field.get(form);

                    final StringBuilder messages = new StringBuilder();
                    
                    for (ConstraintViolation<T> violation : violations) {
                        messages.append(violation.getMessage());
                    }
                    
                    // define como tooltip a mensagem de erro e seta a borda para vermelha
                    component.setToolTipText(messages.toString());
                    component.setBorder(new LineBorder(Color.red));
                    component.addFocusListener(new BorderListener());
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger("FormBinder").log(Level.SEVERE,
                            "can't validate field {0}", field.getName());
                    System.err.println(ex);
                }
            }
        }
        
        // caso o formulario tenha produzido erros, lanca a exception
        if (!valid) {
            throw new InvalidFormValuesException("this for has invalid values");
        }
    }
    
    /**
     * Resolve os campos a serem validados
     * 
     * @param clazz a classe de onde os campos serao extraídos
     * @return a lista de campos
     */
    private static List<Field> resolveFields(Class<?> clazz) {
        
        final List<Field> fields = new ArrayList<>();
        
        for (Field field : clazz.getDeclaredFields()) {
            // checa pela anotação 
            if (field.isAnnotationPresent(ValidateValue.class)) {
                if (!field.isAccessible()) {
                    field.setAccessible(true); // define o campo como acessível
                }
                fields.add(field);
            }
        }
        return fields;
    }
    
    /**
     * Listener que faz a borda de um campo inválido voltar ao normal quando
     * damos foco a ele
     */
    private static class BorderListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent event) {
            final JComponent component = (JComponent) event.getSource();
            
            // caso seja um combobox, seta a borda de combobox
            if (event.getSource() instanceof JComboBox) {
                component.setBorder(UIManager.getBorder("ComboBox.border"));
            } else if (event.getSource() instanceof JTextComponent) {
                component.setBorder(UIManager.getBorder("TextField.border"));
            }
            
            // remove a mensagem de erro
            component.setToolTipText(null);
        }
    }
    
    /**
     * Exception que é lançada para indicar que o formulário é inválido
     */
    public static class InvalidFormValuesException extends Exception {

        /**
         * @see Exception#Exception(java.lang.String)
         * 
         * @param message 
         */
        public InvalidFormValuesException(String message) {
            super(message);
        }
    }
}
