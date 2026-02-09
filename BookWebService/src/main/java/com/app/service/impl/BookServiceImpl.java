package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BookDAO;
import com.app.dto.Book;
import com.app.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDAO bookDAO;
	
	@Override
	public List<Book> findBookList() {
		List<Book> bookList = bookDAO.findBookList();
		
		return bookList;
	}

	@Override
	public int saveBook(Book book) {
		int result = bookDAO.saveBook(book);

		return result;
	}

	@Override
	public int removeBook(int id) {
		int result = bookDAO.removeBook(id);

		return result;
	}

	@Override
	public int modifyBook(Book book) {
		int result = bookDAO.modifyBook(book);

		return result;
	}

	@Override
	public Book findBookByBookID(int bookId) {
		Book book = bookDAO.findBookByBookID(bookId);
		return book;
	}

}
