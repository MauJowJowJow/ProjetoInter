package model.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.ELProperty;

/**
 * O mecanismo que atraves de anotations vincula um campo de um formulario a 
 * um bean
 *
 * @author Arthur Gregorio
 *
 * @version 1.0
 * @since 1.0, 16/09/2013
 */
public final class FormBinder {

    /**
     * Cria os vinculos entre o form e o bean para os campos anotatos do form
     * passado como parametro
     * 
     * @param source a origem dos valores ou do bean utilizado na vinculacao 
     * @param form o form a ser bindado
     * 
     * @return um {@link BindingGroup} com os campos 
     */
    public static BindingGroup bindFormToBean(Object source, Object form) {
        
        // grupo com os bindings e os campos a serem bindados
        final BindingGroup bindingGroup = new BindingGroup();
        final List<FormItem> formItems = resolvePropertys(form.getClass());
        
        // iteramos campo a campo para criar os bindings
        for (FormItem formItem : formItems)  {
            
            final Field field = formItem.getField();
            final Bindable bindable = formItem.getAnnotation();

            try {
                final Object target = field.get(form);
                
                // cria o binding de acordo com as propriedades definidas
                final AutoBinding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, source, ELProperty.create(
                    "${" + bindable.expression() + "}"), target, BeanProperty.create(bindable.property()), bindable.eventName());
            
                // seta o converter se houver
                if (!bindable.converter().isAssignableFrom(Converter.class)) {
                    try {
                        binding.setConverter(bindable.converter().newInstance());
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger("FormBinder").log(Level.SEVERE, 
                                "can't instantiate converter for binding {0}", bindable.eventName());
                        System.err.println(ex);
                    }
                }

                // define a acao caso o source seja null
                if (!bindable.whenSourceIsNull().isEmpty()) {
                    binding.setSourceNullValue(bindable.whenSourceIsNull());
                }
                
                // define a acao caso o target seja null
                if (!bindable.whenTargetIsNull().isEmpty()) {
                    binding.setTargetNullValue(bindable.whenTargetIsNull());
                }
                
                // adiciona ao grupo que seja retornado
                bindingGroup.addBinding(binding);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger("FormBinder").log(Level.SEVERE, 
                                "can't bind field {0}", field.getName());
                System.err.println(ex);
            }
        }
        
        // retornamos o grupo para que quem chamou ative os bindings pelo metodo
        // <code>bindingGroup.bind();</code>
        return bindingGroup;
    }
    
    /**
     * Metodo que resolve os campos e suas propriedades para binding
     * 
     * @param clazz a classe a ser resolvida os campos
     * @return os campos a serem bindados
     */
    private static List<FormItem> resolvePropertys(Class<?> clazz) {
        
        final List<FormItem> formItems = new ArrayList<>();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Bindable.class)) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                formItems.add(new FormItem(field, field.getAnnotation(Bindable.class)));
            }
        }
        return formItems;
    }
    
    /**
     * Classe interna que encapsula os campos e suas anotações para que possam
     * ser feitos os bindings dos campos
     */
    private static class FormItem {

        
        private Field field;
       
		private Bindable annotation;
		
        public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

		public Bindable getAnnotation() {
			return annotation;
		}

		public void setAnnotation(Bindable annotation) {
			this.annotation = annotation;
		}

        public FormItem(Field component, Bindable annotation) {
            this.field = component;
            this.annotation = annotation;
        }
    }
}
