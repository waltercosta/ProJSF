package com.waltercosta.loja.managedbeans.admin;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.waltercosta.loja.daos.BookDAO;
import com.waltercosta.loja.models.Book;

@Model
public class HomeBean {

	@Inject
	private BookDAO bookDao;

	public List<Book> lastReleases() {
		return bookDao.lastReleases();
	}

	public List<Book> olderBooks() {
		return bookDao.olderBooks();
	}
}
