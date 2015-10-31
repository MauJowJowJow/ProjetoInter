package model.dao;

import model.Estado;

public class EstadoDAO extends GenericDAO<Integer, Estado> {

	EstadoDAO(){}
	
	 public static void main(String[] args){
	    	Estado estado = new Estado();
	    	estado.setNomeEstado("Hibernate JPA Customer");
	    	System.out.println("Customer id before creation:"
	    			+ estado.getCodigoEstado());
	    	EstadoDAO customerDAO = new EstadoDAO();
	    	
	    	Estado persistedCustomer = customerDAO.insert(estado);
	    	System.out.println("Customer id after creation:"
	    			+ persistedCustomer.getCodigoEstado());
	    }
}
