package com.waltercosta.loja.managedbeans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.waltercosta.loja.daos.BookDAO;
import com.waltercosta.loja.models.Book;

@Model
public class AdminListBooksBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private BookDAO bookDAO;
	private List<Book> books = new ArrayList<Book>();

	@PostConstruct
	private void loagObjects() {
		this.books = bookDAO.list();
	}

	public List<Book> getBooks() {
		return books;
	}
}