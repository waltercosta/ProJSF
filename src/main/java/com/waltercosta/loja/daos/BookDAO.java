package com.waltercosta.loja.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waltercosta.loja.models.Book;

public class BookDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public void save(Book book) {
		em.persist(book);
	}

	public List<Book> list() {
		return em.createQuery("select distinct(b) from Book b join fetch b.authors", Book.class).getResultList();
	}

	public List<Book> lastReleases() {
		return em.createQuery("select b from Book b where b.releaseDate <= now() order by b.id desc",Book.class).setMaxResults(3).getResultList();
		}

	public List<Book> olderBooks() {
		return em.createQuery("select b from Book b",Book.class).setMaxResults(20).getResultList();
		}
}