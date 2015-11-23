package model.dao;

import java.util.List;
import java.util.Map;

public interface GenericDAO<PK, T> {
	
	public T getById(PK pk);
	
	public T insert(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<T> query(String SQL, Map<String, Object> parametros);
}
