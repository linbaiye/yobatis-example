package org.nalby.yobatis.book.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.nalby.yobatis.book.mapper.AuthorMapper;
import org.nalby.yobatis.book.mapper.BookMapper;
import org.nalby.yobatis.book.model.Author;
import org.nalby.yobatis.book.model.Book;
import org.nalby.yobatis.book.model.criteria.AuthorCriteria;
import org.nalby.yobatis.book.model.criteria.BookCriteria;
import org.nalby.yobatis.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Book getById(Long id) {
		return bookMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Book getByName(String name) {
		List<Book> books = bookMapper.selectByCriteria(BookCriteria.nameEqualTo(name));
		if (books.isEmpty()) {
			return null;
		}
		return books.get(0);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Book> nameEqualOrAuthorIs(String name, String authorName) {
		List<Author> authors = authorMapper.selectByCriteria(AuthorCriteria.nameEqualTo(authorName));
		List<Long> ids = new LinkedList<>();
		for (Author author: authors) {
			ids.add(author.getId());
		}
		// -> where name = '#{name}'
		BookCriteria criteria = BookCriteria.nameEqualTo(name);
		if (!ids.isEmpty()) {
			// -> where name = '#{name}' or (author in (#{id1}, #{id2}))
			criteria.or()
			.andAuthorIn(ids);
		}
		return bookMapper.selectByCriteria(criteria);
	}

}
