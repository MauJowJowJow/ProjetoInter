package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<PK, T> implements GenericDAO<PK, T> {

	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public GenericDAOImpl() {
		createEntity("");
	}
	
	public GenericDAOImpl(String entity) {
		createEntity(entity);
	}
	
	public EntityManagerFactory getEntityManagerFactory(){
		return emf;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}

	// Cria o entity manager, gerenciador de classes no BD do JPA 
	public void createEntity(String entity) {
		if (emf != null)
			if (emf.isOpen())
				return;

		if (entity.isEmpty())
			entity = "ProjetoInter";

		emf = Persistence.createEntityManagerFactory(entity);
		entityManager = emf.createEntityManager();
	}

	public void closeEntity() {
		if (emf != null)
			if (emf.isOpen())
				emf.close();

		if (entityManager != null)
			if (entityManager.isOpen())
				entityManager.close();
	}

	// Retorna objeto completo pelo ID dele
	@Override
	public T getById(PK pk) {
		createEntity("");

		return (T) entityManager.find(getTypeClass(), pk);
	}

	@Override
	public T insert(T entity) {
		createEntity("");
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try {
			entityManager.persist(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();			
		}

		return entity;
	}

	@Override
	public T update(T entity) {
		createEntity("");
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		try {
			entity = entityManager.merge(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();			
		}
		return entity;
	}

	// Exclui dados do banco
	@Override
	public void delete(T entity) {
		createEntity("");
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try {
			entityManager.remove(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();			
		}
	}

	// retorna lista do tipo T
	@Override
	public List<T> query(String SQL, Map<String, Object> parametros) {
		createEntity("");

		Query query = entityManager.createQuery(SQL);

		Object parametro;

		for(String key : parametros.keySet()){
			parametro = parametros.get(key);
			query.setParameter(key, parametro);
		}

		return query.getResultList();
	}

	// Reflection pra pegar o tipo da classe de forma genérica
	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return clazz;
	}
}