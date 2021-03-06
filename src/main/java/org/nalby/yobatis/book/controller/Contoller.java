package org.nalby.yobatis.book.controller;

import java.util.List;

import org.nalby.yobatis.book.model.Book;
import org.nalby.yobatis.book.response.Response;
import org.nalby.yobatis.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class Contoller {
	
	private final static Logger logger = LoggerFactory.getLogger(Contoller.class);
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
	public Response<Book> getByName(@PathVariable(value = "name") String name) {
		logger.info("Getting book by name:{}.", name);
		return Response.ok(bookService.getByName(name));
	}
	
	@RequestMapping(value = "/{bookName}/{authorName}", method = RequestMethod.GET, produces = "application/json")
	public Response<List<Book>> getByNameOrAuthor(
			@PathVariable(value = "bookName") String bookName,
			@PathVariable(value = "authorName") long autoherId) {
		return Response.ok(bookService.nameEqualOrAuthorIs(bookName, autoherId));
	}
}
