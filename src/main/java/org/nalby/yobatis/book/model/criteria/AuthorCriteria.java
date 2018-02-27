package org.nalby.yobatis.book.model.criteria;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Do NOT modify, it will be overwrote every time yobatis runs.
 */
/**
 * A AuthorCriteria provides methods to construct 'where', 'limit', 'offset', 'for update'
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
public class AuthorCriteria extends BaseCriteria {
    private static final Map<String, String> PROPERTY_TO_COLUMN;

    static {
        PROPERTY_TO_COLUMN = new HashMap<String, String>();
        PROPERTY_TO_COLUMN.put("id", "id");
        PROPERTY_TO_COLUMN.put("name", "name");
        PROPERTY_TO_COLUMN.put("birthday", "birthday");
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
     * Add the 'order by field1 asc, field2 asc, ...' clause to query, only fields in {@code Author}(not column names) are allowed.
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
    public AuthorCriteria ascOrderBy(String  ... fields) {
        orderBy("asc", fields);
        return this;
    }

    /**
     * Add the 'order by field1 desc, field2 desc, ...' clause to query, only fields in {@code Author}(not column names) are allowed.
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
    public AuthorCriteria descOrderBy(String  ... fields) {
        orderBy("desc", fields);
        return this;
    }

    public AuthorCriteria or() {
        oredCriteria.add(createCriteriaInternal());
        return this;
    }

    public AuthorCriteria setLimit(Long limit) {
        this.limit = limit;
        return this;
    }

    public AuthorCriteria setOffset(Long offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Set true to append 'for update' clause to this query.
     */
    public AuthorCriteria setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
        return this;
    }

    public AuthorCriteria andIdIsNull() {
        lastCriteria().addCriterion("id is null");
        return this;
    }

    public AuthorCriteria andIdIsNotNull() {
        lastCriteria().addCriterion("id is not null");
        return this;
    }

    public AuthorCriteria andIdEqualTo(Long value) {
        lastCriteria().addCriterion("id =", value, "id");
        return this;
    }

    public AuthorCriteria andIdNotEqualTo(Long value) {
        lastCriteria().addCriterion("id <>", value, "id");
        return this;
    }

    public AuthorCriteria andIdGreaterThan(Long value) {
        lastCriteria().addCriterion("id >", value, "id");
        return this;
    }

    public AuthorCriteria andIdGreaterThanOrEqualTo(Long value) {
        lastCriteria().addCriterion("id >=", value, "id");
        return this;
    }

    public AuthorCriteria andIdLessThan(Long value) {
        lastCriteria().addCriterion("id <", value, "id");
        return this;
    }

    public AuthorCriteria andIdLessThanOrEqualTo(Long value) {
        lastCriteria().addCriterion("id <=", value, "id");
        return this;
    }

    public AuthorCriteria andIdIn(List<Long> values) {
        lastCriteria().addCriterion("id in", values, "id");
        return this;
    }

    public AuthorCriteria andIdNotIn(List<Long> values) {
        lastCriteria().addCriterion("id not in", values, "id");
        return this;
    }

    public AuthorCriteria andIdBetween(Long value1, Long value2) {
        lastCriteria().addCriterion("id between", value1, value2, "id");
        return this;
    }

    public AuthorCriteria andIdNotBetween(Long value1, Long value2) {
        lastCriteria().addCriterion("id not between", value1, value2, "id");
        return this;
    }

    public AuthorCriteria andNameIsNull() {
        lastCriteria().addCriterion("name is null");
        return this;
    }

    public AuthorCriteria andNameIsNotNull() {
        lastCriteria().addCriterion("name is not null");
        return this;
    }

    public AuthorCriteria andNameEqualTo(String value) {
        lastCriteria().addCriterion("name =", value, "name");
        return this;
    }

    public AuthorCriteria andNameNotEqualTo(String value) {
        lastCriteria().addCriterion("name <>", value, "name");
        return this;
    }

    public AuthorCriteria andNameGreaterThan(String value) {
        lastCriteria().addCriterion("name >", value, "name");
        return this;
    }

    public AuthorCriteria andNameGreaterThanOrEqualTo(String value) {
        lastCriteria().addCriterion("name >=", value, "name");
        return this;
    }

    public AuthorCriteria andNameLessThan(String value) {
        lastCriteria().addCriterion("name <", value, "name");
        return this;
    }

    public AuthorCriteria andNameLessThanOrEqualTo(String value) {
        lastCriteria().addCriterion("name <=", value, "name");
        return this;
    }

    public AuthorCriteria andNameLike(String value) {
        lastCriteria().addCriterion("name like", value, "name");
        return this;
    }

    public AuthorCriteria andNameNotLike(String value) {
        lastCriteria().addCriterion("name not like", value, "name");
        return this;
    }

    public AuthorCriteria andNameIn(List<String> values) {
        lastCriteria().addCriterion("name in", values, "name");
        return this;
    }

    public AuthorCriteria andNameNotIn(List<String> values) {
        lastCriteria().addCriterion("name not in", values, "name");
        return this;
    }

    public AuthorCriteria andNameBetween(String value1, String value2) {
        lastCriteria().addCriterion("name between", value1, value2, "name");
        return this;
    }

    public AuthorCriteria andNameNotBetween(String value1, String value2) {
        lastCriteria().addCriterion("name not between", value1, value2, "name");
        return this;
    }

    public AuthorCriteria andBirthdayIsNull() {
        lastCriteria().addCriterion("birthday is null");
        return this;
    }

    public AuthorCriteria andBirthdayIsNotNull() {
        lastCriteria().addCriterion("birthday is not null");
        return this;
    }

    public AuthorCriteria andBirthdayEqualTo(Date value) {
        lastCriteria().addCriterionForJDBCDate("birthday =", value, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayNotEqualTo(Date value) {
        lastCriteria().addCriterionForJDBCDate("birthday <>", value, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayGreaterThan(Date value) {
        lastCriteria().addCriterionForJDBCDate("birthday >", value, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayGreaterThanOrEqualTo(Date value) {
        lastCriteria().addCriterionForJDBCDate("birthday >=", value, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayLessThan(Date value) {
        lastCriteria().addCriterionForJDBCDate("birthday <", value, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayLessThanOrEqualTo(Date value) {
        lastCriteria().addCriterionForJDBCDate("birthday <=", value, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayIn(List<Date> values) {
        lastCriteria().addCriterionForJDBCDate("birthday in", values, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayNotIn(List<Date> values) {
        lastCriteria().addCriterionForJDBCDate("birthday not in", values, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayBetween(Date value1, Date value2) {
        lastCriteria().addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
        return this;
    }

    public AuthorCriteria andBirthdayNotBetween(Date value1, Date value2) {
        lastCriteria().addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
        return this;
    }

    public static AuthorCriteria idIsNull() {
        return new AuthorCriteria().andIdIsNull();
    }

    public static AuthorCriteria idIsNotNull() {
        return new AuthorCriteria().andIdIsNotNull();
    }

    public static AuthorCriteria idEqualTo(Long value) {
        return new AuthorCriteria().andIdEqualTo(value);
    }

    public static AuthorCriteria idNotEqualTo(Long value) {
        return new AuthorCriteria().andIdNotEqualTo(value);
    }

    public static AuthorCriteria idGreaterThan(Long value) {
        return new AuthorCriteria().andIdGreaterThan(value);
    }

    public static AuthorCriteria idGreaterThanOrEqualTo(Long value) {
        return new AuthorCriteria().andIdGreaterThanOrEqualTo(value);
    }

    public static AuthorCriteria idLessThan(Long value) {
        return new AuthorCriteria().andIdLessThan(value);
    }

    public static AuthorCriteria idLessThanOrEqualTo(Long value) {
        return new AuthorCriteria().andIdLessThanOrEqualTo(value);
    }

    public static AuthorCriteria idIn(List<Long> values) {
        return new AuthorCriteria().andIdIn(values);
    }

    public static AuthorCriteria idNotIn(List<Long> values) {
        return new AuthorCriteria().andIdNotIn(values);
    }

    public static AuthorCriteria idBetween(Long value1, Long value2) {
        return new AuthorCriteria().andIdBetween(value1, value2);
    }

    public static AuthorCriteria idNotBetween(Long value1, Long value2) {
        return new AuthorCriteria().andIdNotBetween(value1, value2);
    }

    public static AuthorCriteria nameIsNull() {
        return new AuthorCriteria().andNameIsNull();
    }

    public static AuthorCriteria nameIsNotNull() {
        return new AuthorCriteria().andNameIsNotNull();
    }

    public static AuthorCriteria nameEqualTo(String value) {
        return new AuthorCriteria().andNameEqualTo(value);
    }

    public static AuthorCriteria nameNotEqualTo(String value) {
        return new AuthorCriteria().andNameNotEqualTo(value);
    }

    public static AuthorCriteria nameGreaterThan(String value) {
        return new AuthorCriteria().andNameGreaterThan(value);
    }

    public static AuthorCriteria nameGreaterThanOrEqualTo(String value) {
        return new AuthorCriteria().andNameGreaterThanOrEqualTo(value);
    }

    public static AuthorCriteria nameLessThan(String value) {
        return new AuthorCriteria().andNameLessThan(value);
    }

    public static AuthorCriteria nameLessThanOrEqualTo(String value) {
        return new AuthorCriteria().andNameLessThanOrEqualTo(value);
    }

    public static AuthorCriteria nameLike(String value) {
        return new AuthorCriteria().andNameLike(value);
    }

    public static AuthorCriteria nameNotLike(String value) {
        return new AuthorCriteria().andNameNotLike(value);
    }

    public static AuthorCriteria nameIn(List<String> values) {
        return new AuthorCriteria().andNameIn(values);
    }

    public static AuthorCriteria nameNotIn(List<String> values) {
        return new AuthorCriteria().andNameNotIn(values);
    }

    public static AuthorCriteria nameBetween(String value1, String value2) {
        return new AuthorCriteria().andNameBetween(value1, value2);
    }

    public static AuthorCriteria nameNotBetween(String value1, String value2) {
        return new AuthorCriteria().andNameNotBetween(value1, value2);
    }

    public static AuthorCriteria birthdayIsNull() {
        return new AuthorCriteria().andBirthdayIsNull();
    }

    public static AuthorCriteria birthdayIsNotNull() {
        return new AuthorCriteria().andBirthdayIsNotNull();
    }

    public static AuthorCriteria birthdayEqualTo(Date value) {
        return new AuthorCriteria().andBirthdayEqualTo(value);
    }

    public static AuthorCriteria birthdayNotEqualTo(Date value) {
        return new AuthorCriteria().andBirthdayNotEqualTo(value);
    }

    public static AuthorCriteria birthdayGreaterThan(Date value) {
        return new AuthorCriteria().andBirthdayGreaterThan(value);
    }

    public static AuthorCriteria birthdayGreaterThanOrEqualTo(Date value) {
        return new AuthorCriteria().andBirthdayGreaterThanOrEqualTo(value);
    }

    public static AuthorCriteria birthdayLessThan(Date value) {
        return new AuthorCriteria().andBirthdayLessThan(value);
    }

    public static AuthorCriteria birthdayLessThanOrEqualTo(Date value) {
        return new AuthorCriteria().andBirthdayLessThanOrEqualTo(value);
    }

    public static AuthorCriteria birthdayIn(List<Date> values) {
        return new AuthorCriteria().andBirthdayIn(values);
    }

    public static AuthorCriteria birthdayNotIn(List<Date> values) {
        return new AuthorCriteria().andBirthdayNotIn(values);
    }

    public static AuthorCriteria birthdayBetween(Date value1, Date value2) {
        return new AuthorCriteria().andBirthdayBetween(value1, value2);
    }

    public static AuthorCriteria birthdayNotBetween(Date value1, Date value2) {
        return new AuthorCriteria().andBirthdayNotBetween(value1, value2);
    }
}