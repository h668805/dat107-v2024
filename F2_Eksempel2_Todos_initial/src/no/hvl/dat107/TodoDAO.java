package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TodoDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoPersistenceUnit");

	/* --------------------------------------------------------------------- */

	public List<Todo> finnAlleTodos() {

		EntityManager em = emf.createEntityManager();

		try {
			String p = "select t from Todo t";
			return em.createQuery(p, Todo.class).getResultList();

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
			List<Todo> liste = finnTodosMedTekst(str);
			return liste.isEmpty() ? null : liste.getFirst();
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public List<Todo> finnTodosMedTekst(String str) {
		EntityManager em = emf.createEntityManager();

		try {
			String p = "SELECT t from Todo t WHERE t.tekst like :str";
			TypedQuery<Todo> query = em.createQuery(p, Todo.class);
			query.setParameter("str", str);
			return query.getResultList();
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

	public Todo slettTodoMedId(int id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Todo todo = null;

		try {
			tx.begin();
			todo = em.find(Todo.class, id);
			if (todo != null)
				em.remove(todo);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

		return todo;
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
