package org.nalby.yobatis.book.mapper.impl;

import org.nalby.yobatis.book.mapper.BookDao;
import org.nalby.yobatis.book.model.Book;
import org.nalby.yobatis.book.model.base.BaseBook;
import org.springframework.stereotype.Repository;

/*
 * It is safe to modify this file.
 */
@Repository("bookDao")
public final class BookDaoImpl extends BaseDaoImpl<Book, BaseBook, Long> implements BookDao {

    @Override
    protected String namespace() {
        return "org.nalby.yobatis.book.mapper.impl.BookDaoImpl.";
    }
}