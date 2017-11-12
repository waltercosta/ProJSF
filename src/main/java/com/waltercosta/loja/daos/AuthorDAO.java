package com.waltercosta.loja.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waltercosta.loja.models.Author;

public class AuthorDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;

	public List<Author> list() {
		return manager.createQuery(
				"select a from Author a order by a.name asc", Author.class)
				.getResultList();
	}

}