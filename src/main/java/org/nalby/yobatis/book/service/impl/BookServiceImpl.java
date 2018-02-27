package org.nalby.yobatis.book.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.nalby.yobatis.book.mapper.AuthorDao;
import org.nalby.yobatis.book.mapper.BookDao;
import org.nalby.yobatis.book.model.Book;
import org.nalby.yobatis.book.model.criteria.BookCriteria;
import org.nalby.yobatis.book.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource
	private BookDao bookDao;
	
	@Resource
	private AuthorDao authorDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Book getById(Long id) {
		return bookDao.selectOne(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Book getByName(String name) {
		List<Book> books = bookDao.selectList(BookCriteria.nameEqualTo(name));
		if (books.isEmpty()) {
			return null;
		}
		return books.get(0);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Book> nameEqualOrAuthorIs(String name, long authorId) {
		BookCriteria criteria = BookCriteria.nameEqualTo(name).or().andAuthorEqualTo(authorId);
		return bookDao.selectList(criteria);
	}
}
