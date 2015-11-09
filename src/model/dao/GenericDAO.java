package model.dao;

import java.util.List;

public interface GenericDAO<PK, T> {
	
	public T getById(PK pk);
	
	public T insert(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> query(String SQL, List<String> parametros);
}
