package com.waltercosta.loja.managedbeans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import com.waltercosta.loja.daos.AuthorDAO;
import com.waltercosta.loja.daos.BookDAO;
import com.waltercosta.loja.infra.MessagesHelper;
import com.waltercosta.loja.models.Author;
import com.waltercosta.loja.models.Book;

@Model
public class AdminBooksBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Book book = new Book();
	private List<Author> authors = new ArrayList<Author>();
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	@Inject
	private BookDAO bookDAO;
	@Inject
	private AuthorDAO authorDAO;
	@Inject
	private MessagesHelper messagesHelper;
	private Part summary;

	@Transactional
	public String save() {
		bookDAO.save(getBook());
		messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));
		System.out.println(extractFilename(summary.getHeader("content-disposition")));
		return "/livros/lista?faces-redirect=true";
	}

	public Book getBook() {
		return book;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	public Part getSummary() {
		return summary;
	}

	public void setSummary(Part summary) {
		this.summary = summary;
	}

	@PostConstruct
	public void loadObjects() {
		this.authors = authorDAO.list();
	}

	private String extractFilename(String contentDisposition) {
		if (contentDisposition == null) {
			return null;
		}
		String fileNameKey = "filename=";
		int startIndex = contentDisposition.indexOf(fileNameKey);
		if (startIndex == -1) {
			return null;
		}
		String filename = contentDisposition.substring(startIndex + fileNameKey.length());
		if (filename.startsWith("\"")) {
			int endIndex = filename.indexOf("\"", 1);
			if (endIndex != -1) {
				return filename.substring(1, endIndex);
			}
		} else {
			int endIndex = filename.indexOf(";");
			if (endIndex != -1) {
				return filename.substring(0, endIndex);
			}
		}
		return filename;
	}
}
