package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.Book;
import com.app.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookService.findBookList();

		model.addAttribute("bookList", bookList);
		return "bookList";
	}
	
	@GetMapping("/bookList/{bookId}")
	public String book(@PathVariable String bookId, Model model) {
		
		Book book = bookService.findBookByBookID(Integer.parseInt(bookId));
		model.addAttribute("book", book);
		
		return "book";
		
	}
	
	@GetMapping("/addBook")
	public String addBook() {
		return "addBook";
	}

	@PostMapping("/addBook")
	public String addBookAction(@ModelAttribute Book book) {
		int result = bookService.saveBook(book);
		return "redirect:/bookList";
	}

	@GetMapping("/removeBook")
	public String removeBook(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");

		if (bookId == null) {
			return "redirect:/bookList";
		}

		int result = bookService.removeBook(Integer.parseInt(bookId));
		return "redirect:/bookList";
	}
	
	@GetMapping("/modifyBook")
	public String modifyBook(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		
		if(bookId == null) {
			return "redirect:/bookList";
		}
		
		Book book = bookService.findBookByBookID(Integer.parseInt(bookId));
		
		request.setAttribute("book", book);
		
		// 수정
		
		return "modifyBook";
	}
	
	@PostMapping("/modifyBook")
	public String modifyRoomAction(@ModelAttribute Book book) {
		
		int result = bookService.modifyBook(book);
		
		if(result > 0) {
			return "redirect:/bookList";
		} else {
			return "redirect:/modifyBook?bookId=" + book.getId();
		}
	}
}
