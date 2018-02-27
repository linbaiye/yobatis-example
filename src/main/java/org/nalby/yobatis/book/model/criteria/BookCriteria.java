package org.nalby.yobatis.book.model.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Do NOT modify, it will be overwrote every time yobatis runs.
 */
/**
 * A BookCriteria provides methods to construct 'where', 'limit', 'offset', 'for update'
 * clauses. Although building 'limit', 'offset', 'for update' and simple 'where' clauses is pretty
 * intuitive, a complex 'where' clause requires a little bit more attention.
 * <p>A complex 'where' consists of multiple expressions that are ORed together, such as <br>
 * {@code (id = 1 and field = 2) or (filed <= 3) or ( ... ) ...}
 * <p>Suppose we had a Book model which has author and name fields,
 * here is an example that utilizes BookCriteria to build a where clause of<br>
 * {@code (author = "Some author" and name = "Some book") or (name not in ("hated ones", "boring ones"))}
 * <pre>
 * BookCriteria.authorEqualTo("Some author")
 * .andNameEqualTo("Some book")
 * .or()
 * .andNameNotIn(Arrays.asList("hated ones", "boring ones"));
 * </pre>
 */
public class BookCriteria extends BaseCriteria {
    private static final Map<String, String> PROPERTY_TO_COLUMN;

    static {
        PROPERTY_TO_COLUMN = new HashMap<String, String>();
        PROPERTY_TO_COLUMN.put("id", "id");
        PROPERTY_TO_COLUMN.put("name", "name");
        PROPERTY_TO_COLUMN.put("author", "author");
    }

    protected void orderBy(String  order, String  ... fields) {
        if ( fields == null || fields.length == 0) {
            throw new IllegalArgumentException("Empty fields passed.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (orderByClause != null) {
            stringBuilder.append(orderByClause);
            stringBuilder.append(',');
        }
        for (String field : fields) {
            if (!PROPERTY_TO_COLUMN.containsKey(field)) {
                throw new IllegalArgumentException("Unrecognizable field:" + field);
            }
            stringBuilder.append(PROPERTY_TO_COLUMN.get(field));
            stringBuilder.append(" ");
            stringBuilder.append(order);
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        orderByClause = stringBuilder.toString();
    }

    /**
     * Add the 'order by field1 asc, field2 asc, ...' clause to query, only fields in {@code Book}(not column names) are allowed.
     * By invoking this method and {@link #descOrderBy(String...) descOrderBy} alternately, a more complex 'order by' clause
     * can be constructed, shown as below.
     * <pre>
     * criteria.ascOrderBy('field1');
     * criteria.descOrderBy('field2');
     * -> 'order by field1 asc, field2 desc'
     * </pre>
     * @param fields the fields to sort.
     * @throws IllegalArgumentException if fields is empty, or any of the fields is invalid.
     * @return this criteria.
     */
    public BookCriteria ascOrderBy(String  ... fields) {
        orderBy("asc", fields);
        return this;
    }

    /**
     * Add the 'order by field1 desc, field2 desc, ...' clause to query, only fields in {@code Book}(not column names) are allowed.
     * By invoking this method and {@link #ascOrderBy(String...) ascOrderBy} alternately, a more complex 'order by' clause
     * can be constructed, shown as below.
     * <pre>
     * criteria.ascOrderBy('field1');
     * criteria.descOrderBy('field2');
     * -> 'order by field1 asc, field2 desc'
     * </pre>
     * @param fields the fields to sort.
     * @throws IllegalArgumentException if fields is empty, or any of the fields is invalid.
     * @return this criteria.
     */
    public BookCriteria descOrderBy(String  ... fields) {
        orderBy("desc", fields);
        return this;
    }

    public BookCriteria or() {
        oredCriteria.add(createCriteriaInternal());
        return this;
    }

    public BookCriteria setLimit(Long limit) {
        this.limit = limit;
        return this;
    }

    public BookCriteria setOffset(Long offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Set true to append 'for update' clause to this query.
     */
    public BookCriteria setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
        return this;
    }

    public BookCriteria andIdIsNull() {
        lastCriteria().addCriterion("id is null");
        return this;
    }

    public BookCriteria andIdIsNotNull() {
        lastCriteria().addCriterion("id is not null");
        return this;
    }

    public BookCriteria andIdEqualTo(Long value) {
        lastCriteria().addCriterion("id =", value, "id");
        return this;
    }

    public BookCriteria andIdNotEqualTo(Long value) {
        lastCriteria().addCriterion("id <>", value, "id");
        return this;
    }

    public BookCriteria andIdGreaterThan(Long value) {
        lastCriteria().addCriterion("id >", value, "id");
        return this;
    }

    public BookCriteria andIdGreaterThanOrEqualTo(Long value) {
        lastCriteria().addCriterion("id >=", value, "id");
        return this;
    }

    public BookCriteria andIdLessThan(Long value) {
        lastCriteria().addCriterion("id <", value, "id");
        return this;
    }

    public BookCriteria andIdLessThanOrEqualTo(Long value) {
        lastCriteria().addCriterion("id <=", value, "id");
        return this;
    }

    public BookCriteria andIdIn(List<Long> values) {
        lastCriteria().addCriterion("id in", values, "id");
        return this;
    }

    public BookCriteria andIdNotIn(List<Long> values) {
        lastCriteria().addCriterion("id not in", values, "id");
        return this;
    }

    public BookCriteria andIdBetween(Long value1, Long value2) {
        lastCriteria().addCriterion("id between", value1, value2, "id");
        return this;
    }

    public BookCriteria andIdNotBetween(Long value1, Long value2) {
        lastCriteria().addCriterion("id not between", value1, value2, "id");
        return this;
    }

    public BookCriteria andNameIsNull() {
        lastCriteria().addCriterion("name is null");
        return this;
    }

    public BookCriteria andNameIsNotNull() {
        lastCriteria().addCriterion("name is not null");
        return this;
    }

    public BookCriteria andNameEqualTo(String value) {
        lastCriteria().addCriterion("name =", value, "name");
        return this;
    }

    public BookCriteria andNameNotEqualTo(String value) {
        lastCriteria().addCriterion("name <>", value, "name");
        return this;
    }

    public BookCriteria andNameGreaterThan(String value) {
        lastCriteria().addCriterion("name >", value, "name");
        return this;
    }

    public BookCriteria andNameGreaterThanOrEqualTo(String value) {
        lastCriteria().addCriterion("name >=", value, "name");
        return this;
    }

    public BookCriteria andNameLessThan(String value) {
        lastCriteria().addCriterion("name <", value, "name");
        return this;
    }

    public BookCriteria andNameLessThanOrEqualTo(String value) {
        lastCriteria().addCriterion("name <=", value, "name");
        return this;
    }

    public BookCriteria andNameLike(String value) {
        lastCriteria().addCriterion("name like", value, "name");
        return this;
    }

    public BookCriteria andNameNotLike(String value) {
        lastCriteria().addCriterion("name not like", value, "name");
        return this;
    }

    public BookCriteria andNameIn(List<String> values) {
        lastCriteria().addCriterion("name in", values, "name");
        return this;
    }

    public BookCriteria andNameNotIn(List<String> values) {
        lastCriteria().addCriterion("name not in", values, "name");
        return this;
    }

    public BookCriteria andNameBetween(String value1, String value2) {
        lastCriteria().addCriterion("name between", value1, value2, "name");
        return this;
    }

    public BookCriteria andNameNotBetween(String value1, String value2) {
        lastCriteria().addCriterion("name not between", value1, value2, "name");
        return this;
    }

    public BookCriteria andAuthorIsNull() {
        lastCriteria().addCriterion("author is null");
        return this;
    }

    public BookCriteria andAuthorIsNotNull() {
        lastCriteria().addCriterion("author is not null");
        return this;
    }

    public BookCriteria andAuthorEqualTo(Long value) {
        lastCriteria().addCriterion("author =", value, "author");
        return this;
    }

    public BookCriteria andAuthorNotEqualTo(Long value) {
        lastCriteria().addCriterion("author <>", value, "author");
        return this;
    }

    public BookCriteria andAuthorGreaterThan(Long value) {
        lastCriteria().addCriterion("author >", value, "author");
        return this;
    }

    public BookCriteria andAuthorGreaterThanOrEqualTo(Long value) {
        lastCriteria().addCriterion("author >=", value, "author");
        return this;
    }

    public BookCriteria andAuthorLessThan(Long value) {
        lastCriteria().addCriterion("author <", value, "author");
        return this;
    }

    public BookCriteria andAuthorLessThanOrEqualTo(Long value) {
        lastCriteria().addCriterion("author <=", value, "author");
        return this;
    }

    public BookCriteria andAuthorIn(List<Long> values) {
        lastCriteria().addCriterion("author in", values, "author");
        return this;
    }

    public BookCriteria andAuthorNotIn(List<Long> values) {
        lastCriteria().addCriterion("author not in", values, "author");
        return this;
    }

    public BookCriteria andAuthorBetween(Long value1, Long value2) {
        lastCriteria().addCriterion("author between", value1, value2, "author");
        return this;
    }

    public BookCriteria andAuthorNotBetween(Long value1, Long value2) {
        lastCriteria().addCriterion("author not between", value1, value2, "author");
        return this;
    }

    public static BookCriteria idIsNull() {
        return new BookCriteria().andIdIsNull();
    }

    public static BookCriteria idIsNotNull() {
        return new BookCriteria().andIdIsNotNull();
    }

    public static BookCriteria idEqualTo(Long value) {
        return new BookCriteria().andIdEqualTo(value);
    }

    public static BookCriteria idNotEqualTo(Long value) {
        return new BookCriteria().andIdNotEqualTo(value);
    }

    public static BookCriteria idGreaterThan(Long value) {
        return new BookCriteria().andIdGreaterThan(value);
    }

    public static BookCriteria idGreaterThanOrEqualTo(Long value) {
        return new BookCriteria().andIdGreaterThanOrEqualTo(value);
    }

    public static BookCriteria idLessThan(Long value) {
        return new BookCriteria().andIdLessThan(value);
    }

    public static BookCriteria idLessThanOrEqualTo(Long value) {
        return new BookCriteria().andIdLessThanOrEqualTo(value);
    }

    public static BookCriteria idIn(List<Long> values) {
        return new BookCriteria().andIdIn(values);
    }

    public static BookCriteria idNotIn(List<Long> values) {
        return new BookCriteria().andIdNotIn(values);
    }

    public static BookCriteria idBetween(Long value1, Long value2) {
        return new BookCriteria().andIdBetween(value1, value2);
    }

    public static BookCriteria idNotBetween(Long value1, Long value2) {
        return new BookCriteria().andIdNotBetween(value1, value2);
    }

    public static BookCriteria nameIsNull() {
        return new BookCriteria().andNameIsNull();
    }

    public static BookCriteria nameIsNotNull() {
        return new BookCriteria().andNameIsNotNull();
    }

    public static BookCriteria nameEqualTo(String value) {
        return new BookCriteria().andNameEqualTo(value);
    }

    public static BookCriteria nameNotEqualTo(String value) {
        return new BookCriteria().andNameNotEqualTo(value);
    }

    public static BookCriteria nameGreaterThan(String value) {
        return new BookCriteria().andNameGreaterThan(value);
    }

    public static BookCriteria nameGreaterThanOrEqualTo(String value) {
        return new BookCriteria().andNameGreaterThanOrEqualTo(value);
    }

    public static BookCriteria nameLessThan(String value) {
        return new BookCriteria().andNameLessThan(value);
    }

    public static BookCriteria nameLessThanOrEqualTo(String value) {
        return new BookCriteria().andNameLessThanOrEqualTo(value);
    }

    public static BookCriteria nameLike(String value) {
        return new BookCriteria().andNameLike(value);
    }

    public static BookCriteria nameNotLike(String value) {
        return new BookCriteria().andNameNotLike(value);
    }

    public static BookCriteria nameIn(List<String> values) {
        return new BookCriteria().andNameIn(values);
    }

    public static BookCriteria nameNotIn(List<String> values) {
        return new BookCriteria().andNameNotIn(values);
    }

    public static BookCriteria nameBetween(String value1, String value2) {
        return new BookCriteria().andNameBetween(value1, value2);
    }

    public static BookCriteria nameNotBetween(String value1, String value2) {
        return new BookCriteria().andNameNotBetween(value1, value2);
    }

    public static BookCriteria authorIsNull() {
        return new BookCriteria().andAuthorIsNull();
    }

    public static BookCriteria authorIsNotNull() {
        return new BookCriteria().andAuthorIsNotNull();
    }

    public static BookCriteria authorEqualTo(Long value) {
        return new BookCriteria().andAuthorEqualTo(value);
    }

    public static BookCriteria authorNotEqualTo(Long value) {
        return new BookCriteria().andAuthorNotEqualTo(value);
    }

    public static BookCriteria authorGreaterThan(Long value) {
        return new BookCriteria().andAuthorGreaterThan(value);
    }

    public static BookCriteria authorGreaterThanOrEqualTo(Long value) {
        return new BookCriteria().andAuthorGreaterThanOrEqualTo(value);
    }

    public static BookCriteria authorLessThan(Long value) {
        return new BookCriteria().andAuthorLessThan(value);
    }

    public static BookCriteria authorLessThanOrEqualTo(Long value) {
        return new BookCriteria().andAuthorLessThanOrEqualTo(value);
    }

    public static BookCriteria authorIn(List<Long> values) {
        return new BookCriteria().andAuthorIn(values);
    }

    public static BookCriteria authorNotIn(List<Long> values) {
        return new BookCriteria().andAuthorNotIn(values);
    }

    public static BookCriteria authorBetween(Long value1, Long value2) {
        return new BookCriteria().andAuthorBetween(value1, value2);
    }

    public static BookCriteria authorNotBetween(Long value1, Long value2) {
        return new BookCriteria().andAuthorNotBetween(value1, value2);
    }
}