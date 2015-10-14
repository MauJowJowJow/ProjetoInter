package model.dao;

import model.Pessoa;

public class PessoaDAO extends GenericDAO<Integer, Pessoa>{
	
	PessoaDAO(){}

    public static void main(String[] args){
    	Pessoa pessoa = new Pessoa();
    	pessoa.setNome("Hibernate JPA Customer");
    	System.out.println("Customer id before creation:"
    			+ pessoa.getCodigo());
    	PessoaDAO customerDAO = new PessoaDAO();
    	
    	Pessoa persistedCustomer = customerDAO.insert(pessoa);
    	System.out.println("Customer id after creation:"
    			+ persistedCustomer.getCodigo());
    }
}
