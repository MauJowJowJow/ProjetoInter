package model.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Classe que adiciona a um bean a capacidade de ser bindavel
 *
 * @author Arthur Gregorio
 *
 * @since 1.0
 * @version 1.0, 02/04/2013
 */
public abstract class BindableModel implements Serializable {

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(BindableModel.this);
    
    /**
     * Adiciona um listener para visar por mudanças no bean
     * 
     * @param listener a listener a ser adicionada
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Adiciona um listener para visar por mudanças no bean
     * 
     * @param propertyName o nome da propriedade a ser notificada em caso de mudanças
     * @param listener a listener a ser adicionada
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Usado para remover a listener de notificação
     * 
     * @param listener 
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.changeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Usado para remover a listener de notificação
     * 
     * @param propertyName o nome da propriedade que tera a listener removida
     * @param listener a listener a ser removida
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.changeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Notifica a quem estiver escutando por mudanças nesta propriedade
     * 
     * @param propertyName o nome da propriedade que foi alterada
     * @param oldValue o valor antigo
     * @param newValue o novo valor
     */
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        this.changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
