package com.smxknife.mybatis.springboot.model;

import java.util.ArrayList;
import java.util.List;

public class TBStringExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBStringExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCCharIsNull() {
            addCriterion("c_char is null");
            return (Criteria) this;
        }

        public Criteria andCCharIsNotNull() {
            addCriterion("c_char is not null");
            return (Criteria) this;
        }

        public Criteria andCCharEqualTo(String value) {
            addCriterion("c_char =", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharNotEqualTo(String value) {
            addCriterion("c_char <>", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharGreaterThan(String value) {
            addCriterion("c_char >", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharGreaterThanOrEqualTo(String value) {
            addCriterion("c_char >=", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharLessThan(String value) {
            addCriterion("c_char <", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharLessThanOrEqualTo(String value) {
            addCriterion("c_char <=", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharLike(String value) {
            addCriterion("c_char like", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharNotLike(String value) {
            addCriterion("c_char not like", value, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharIn(List<String> values) {
            addCriterion("c_char in", values, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharNotIn(List<String> values) {
            addCriterion("c_char not in", values, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharBetween(String value1, String value2) {
            addCriterion("c_char between", value1, value2, "cChar");
            return (Criteria) this;
        }

        public Criteria andCCharNotBetween(String value1, String value2) {
            addCriterion("c_char not between", value1, value2, "cChar");
            return (Criteria) this;
        }

        public Criteria andCVarcharIsNull() {
            addCriterion("c_varchar is null");
            return (Criteria) this;
        }

        public Criteria andCVarcharIsNotNull() {
            addCriterion("c_varchar is not null");
            return (Criteria) this;
        }

        public Criteria andCVarcharEqualTo(String value) {
            addCriterion("c_varchar =", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharNotEqualTo(String value) {
            addCriterion("c_varchar <>", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharGreaterThan(String value) {
            addCriterion("c_varchar >", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharGreaterThanOrEqualTo(String value) {
            addCriterion("c_varchar >=", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharLessThan(String value) {
            addCriterion("c_varchar <", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharLessThanOrEqualTo(String value) {
            addCriterion("c_varchar <=", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharLike(String value) {
            addCriterion("c_varchar like", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharNotLike(String value) {
            addCriterion("c_varchar not like", value, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharIn(List<String> values) {
            addCriterion("c_varchar in", values, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharNotIn(List<String> values) {
            addCriterion("c_varchar not in", values, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharBetween(String value1, String value2) {
            addCriterion("c_varchar between", value1, value2, "cVarchar");
            return (Criteria) this;
        }

        public Criteria andCVarcharNotBetween(String value1, String value2) {
            addCriterion("c_varchar not between", value1, value2, "cVarchar");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}