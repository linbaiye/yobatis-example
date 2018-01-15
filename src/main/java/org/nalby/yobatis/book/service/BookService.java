package org.nalby.yobatis.book.service;

import java.util.List;

import org.nalby.yobatis.book.model.Book;

public interface BookService {
	
	Book getById(Long id);
	
	Book getByName(String name);

	List<Book> nameEqualOrAuthorIs(String name, String author);

}
