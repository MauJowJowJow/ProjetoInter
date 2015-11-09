package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<PK, T> implements GenericDAO {

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

	public T getById(PK pk) {
		createEntity("");

		return (T) entityManager.find(getTypeClass(), pk);
	}

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

	public void update(T entity) {
		createEntity("");
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		try {
			entityManager.merge(entity);
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();			
		}
	}

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

	public List<T> query(String SQL, List<String> parametros) {
		createEntity("");

		Query query = entityManager.createQuery(SQL);

		String parametro;

		for (int i = 0; i < parametros.size(); i++) {
			parametro = parametros.get(i);
			query.setParameter(i, parametro);
		}

		return query.getResultList();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return clazz;
	}
}