package com.app.service;

import java.util.List;

import com.app.dto.Book;

public interface BookService {
	List<Book> findBookList();
	Book findBookByBookID(int bookId);
	int saveBook(Book book);
	int removeBook(int id);
	int modifyBook(Book book);
}
