package org.nalby.yobatis.book.mapper.impl;

import org.nalby.yobatis.book.mapper.AuthorDao;
import org.nalby.yobatis.book.model.Author;
import org.nalby.yobatis.book.model.base.BaseAuthor;
import org.springframework.stereotype.Repository;

/*
 * It is safe to modify this file.
 */
@Repository("authorDao")
public final class AuthorDaoImpl extends BaseDaoImpl<Author, BaseAuthor, Long> implements AuthorDao {

    @Override
    protected String namespace() {
        return "org.nalby.yobatis.book.mapper.impl.AuthorDaoImpl.";
    }
}