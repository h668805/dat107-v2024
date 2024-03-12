package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TodoDAO {
	
	private EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("todoPersistenceUnit");
	
	/* --------------------------------------------------------------------- */

	public List<Todo> finnAlleTodos() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			String p = "select t from Todo t";
			return em.createQuery(p, Todo.class).getResultList();
//			return query.getResultList();

		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public Todo finnTodoMedId(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Todo.class, id);
			
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public Todo finnTodoMedTekst(String str) {
		EntityManager em = emf.createEntityManager();
		
		try {
			return finnTodosMedTekst(str).get(0);
		} finally {
			em.close();
		}
	}
	
	/* --------------------------------------------------------------------- */

	public List<Todo>  finnTodosMedTekst(String str) {
		EntityManager em = emf.createEntityManager();
		
		try {
			String p = "SELECT t from Todo t WHERE t.tekst = " + str;
			return em.createQuery(p, Todo.class).getResultList();
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public boolean lagreNyTodo(Todo todo) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean lagtTil = false;
		try {
			tx.begin();
			
			em.persist(todo);
			
			tx.commit();
			lagtTil = true;
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return lagtTil; 
	}

	/* --------------------------------------------------------------------- */

	public Todo slettTodoMedPk(int pk) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Todo slettet = null;

		try {
			tx.begin();
			String p = "SELECT t from Todo t WHERE t.id = " + pk;
			Todo todo = em.find(Todo.class, p);
			em.remove(todo);
			
			tx.commit();
			slettet = todo;
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return slettet; 
	}

	/* --------------------------------------------------------------------- */

	public Todo oppdaterTodo(Todo todo) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Todo merge = null;
		try {
			tx.begin();
			
			em.merge(todo);
			
			tx.commit();
			merge = todo;
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return merge; 
	}

	/* --------------------------------------------------------------------- */

	public Todo oppdaterTekst(int id, String tekst) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Todo merge = null;

		try {
			tx.begin();
			
			em.merge(new Todo(id, tekst));
			
			tx.commit();
			merge = new Todo(id, tekst);
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return merge; 
	}
}
