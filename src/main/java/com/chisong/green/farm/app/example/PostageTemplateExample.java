package com.chisong.green.farm.app.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
/**
* <p>
* 运费模板
* </p>
*
* @author 孙龙云
* @date 2019-12-28
*/
@Data
public class PostageTemplateExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PostageTemplateExample(){oredCriteria = new ArrayList<>();}

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
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
        protected GeneratedCriteria() {super();criteria = new ArrayList<Criterion>();}

        public boolean isValid() {return criteria.size() > 0;}

        public List<Criterion> getAllCriteria() {return criteria;}

        public List<Criterion> getCriteria() {return criteria;}

        protected void addCriterion(String condition) {
            if (condition == null) {throw new RuntimeException("Value for condition cannot be null");}
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {

            if (value == null) {throw new RuntimeException("Value for " + property + " cannot be null");}
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        /**
        *
        * 循环写参数
        * @return
        */
        // in,   not in
        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        //between
        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        //like
        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        // = <>
        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }


        // > >= < <=
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

        //null  not null
        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }
        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        //between
        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        //like
        public Criteria andGoodsIdLike(String value) {
            addCriterion("goods_id like", value, "goodsId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }
        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }
        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }
        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }
        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andProvincesIn(List<String> values) {
            addCriterion("provinces in", values, "provinces");
            return (Criteria) this;
        }
        public Criteria andProvincesNotIn(List<String> values) {
            addCriterion("provinces not in", values, "provinces");
            return (Criteria) this;
        }

        //between
        public Criteria andProvincesBetween(String value1, String value2) {
            addCriterion("provinces between", value1, value2, "provinces");
            return (Criteria) this;
        }

        //like
        public Criteria andProvincesLike(String value) {
            addCriterion("provinces like", value, "provinces");
            return (Criteria) this;
        }

        // = <>
        public Criteria andProvincesEqualTo(String value) {
            addCriterion("provinces =", value, "provinces");
            return (Criteria) this;
        }
        public Criteria andProvincesNotEqualTo(String value) {
            addCriterion("provinces <>", value, "provinces");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andProvincesGreaterThan(String value) {
            addCriterion("provinces >", value, "provinces");
            return (Criteria) this;
        }
        public Criteria andProvincesGreaterThanOrEqualTo(String value) {
            addCriterion("provinces >=", value, "provinces");
            return (Criteria) this;
        }
        public Criteria andProvincesLessThan(String value) {
            addCriterion("provinces <", value, "provinces");
            return (Criteria) this;
        }
        public Criteria andProvincesLessThanOrEqualTo(String value) {
            addCriterion("provinces <=", value, "provinces");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andProvincesIsNull() {
            addCriterion("provinces is null");
            return (Criteria) this;
        }

        public Criteria andProvincesIsNotNull() {
            addCriterion("provinces is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andIncUnitNumIn(List<Integer> values) {
            addCriterion("inc_unit_num in", values, "incUnitNum");
            return (Criteria) this;
        }
        public Criteria andIncUnitNumNotIn(List<Integer> values) {
            addCriterion("inc_unit_num not in", values, "incUnitNum");
            return (Criteria) this;
        }

        //between
        public Criteria andIncUnitNumBetween(Integer value1, Integer value2) {
            addCriterion("inc_unit_num between", value1, value2, "incUnitNum");
            return (Criteria) this;
        }

        //like
        public Criteria andIncUnitNumLike(String value) {
            addCriterion("inc_unit_num like", value, "incUnitNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andIncUnitNumEqualTo(Integer value) {
            addCriterion("inc_unit_num =", value, "incUnitNum");
            return (Criteria) this;
        }
        public Criteria andIncUnitNumNotEqualTo(Integer value) {
            addCriterion("inc_unit_num <>", value, "incUnitNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andIncUnitNumGreaterThan(Integer value) {
            addCriterion("inc_unit_num >", value, "incUnitNum");
            return (Criteria) this;
        }
        public Criteria andIncUnitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("inc_unit_num >=", value, "incUnitNum");
            return (Criteria) this;
        }
        public Criteria andIncUnitNumLessThan(Integer value) {
            addCriterion("inc_unit_num <", value, "incUnitNum");
            return (Criteria) this;
        }
        public Criteria andIncUnitNumLessThanOrEqualTo(Integer value) {
            addCriterion("inc_unit_num <=", value, "incUnitNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andIncUnitNumIsNull() {
            addCriterion("inc_unit_num is null");
            return (Criteria) this;
        }

        public Criteria andIncUnitNumIsNotNull() {
            addCriterion("inc_unit_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andUnitIn(List<Integer> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }
        public Criteria andUnitNotIn(List<Integer> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        //between
        public Criteria andUnitBetween(Integer value1, Integer value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        //like
        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        // = <>
        public Criteria andUnitEqualTo(Integer value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }
        public Criteria andUnitNotEqualTo(Integer value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andUnitGreaterThan(Integer value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }
        public Criteria andUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }
        public Criteria andUnitLessThan(Integer value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }
        public Criteria andUnitLessThanOrEqualTo(Integer value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }
        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        //between
        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        //like
        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }
        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }
        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }
        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }
        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andFreeTotalAmountIn(List<Integer> values) {
            addCriterion("free_total_amount in", values, "freeTotalAmount");
            return (Criteria) this;
        }
        public Criteria andFreeTotalAmountNotIn(List<Integer> values) {
            addCriterion("free_total_amount not in", values, "freeTotalAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andFreeTotalAmountBetween(Integer value1, Integer value2) {
            addCriterion("free_total_amount between", value1, value2, "freeTotalAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andFreeTotalAmountLike(String value) {
            addCriterion("free_total_amount like", value, "freeTotalAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andFreeTotalAmountEqualTo(Integer value) {
            addCriterion("free_total_amount =", value, "freeTotalAmount");
            return (Criteria) this;
        }
        public Criteria andFreeTotalAmountNotEqualTo(Integer value) {
            addCriterion("free_total_amount <>", value, "freeTotalAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andFreeTotalAmountGreaterThan(Integer value) {
            addCriterion("free_total_amount >", value, "freeTotalAmount");
            return (Criteria) this;
        }
        public Criteria andFreeTotalAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_total_amount >=", value, "freeTotalAmount");
            return (Criteria) this;
        }
        public Criteria andFreeTotalAmountLessThan(Integer value) {
            addCriterion("free_total_amount <", value, "freeTotalAmount");
            return (Criteria) this;
        }
        public Criteria andFreeTotalAmountLessThanOrEqualTo(Integer value) {
            addCriterion("free_total_amount <=", value, "freeTotalAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andFreeTotalAmountIsNull() {
            addCriterion("free_total_amount is null");
            return (Criteria) this;
        }

        public Criteria andFreeTotalAmountIsNotNull() {
            addCriterion("free_total_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }
        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        //between
        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        //like
        public Criteria andWeightLike(String value) {
            addCriterion("weight like", value, "weight");
            return (Criteria) this;
        }

        // = <>
        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }
        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }
        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }
        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }
        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andFreeNumIn(List<Integer> values) {
            addCriterion("free_num in", values, "freeNum");
            return (Criteria) this;
        }
        public Criteria andFreeNumNotIn(List<Integer> values) {
            addCriterion("free_num not in", values, "freeNum");
            return (Criteria) this;
        }

        //between
        public Criteria andFreeNumBetween(Integer value1, Integer value2) {
            addCriterion("free_num between", value1, value2, "freeNum");
            return (Criteria) this;
        }

        //like
        public Criteria andFreeNumLike(String value) {
            addCriterion("free_num like", value, "freeNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andFreeNumEqualTo(Integer value) {
            addCriterion("free_num =", value, "freeNum");
            return (Criteria) this;
        }
        public Criteria andFreeNumNotEqualTo(Integer value) {
            addCriterion("free_num <>", value, "freeNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andFreeNumGreaterThan(Integer value) {
            addCriterion("free_num >", value, "freeNum");
            return (Criteria) this;
        }
        public Criteria andFreeNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_num >=", value, "freeNum");
            return (Criteria) this;
        }
        public Criteria andFreeNumLessThan(Integer value) {
            addCriterion("free_num <", value, "freeNum");
            return (Criteria) this;
        }
        public Criteria andFreeNumLessThanOrEqualTo(Integer value) {
            addCriterion("free_num <=", value, "freeNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andFreeNumIsNull() {
            addCriterion("free_num is null");
            return (Criteria) this;
        }

        public Criteria andFreeNumIsNotNull() {
            addCriterion("free_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andValidityIn(List<Integer> values) {
            addCriterion("validity in", values, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityNotIn(List<Integer> values) {
            addCriterion("validity not in", values, "validity");
            return (Criteria) this;
        }

        //between
        public Criteria andValidityBetween(Integer value1, Integer value2) {
            addCriterion("validity between", value1, value2, "validity");
            return (Criteria) this;
        }

        //like
        public Criteria andValidityLike(String value) {
            addCriterion("validity like", value, "validity");
            return (Criteria) this;
        }

        // = <>
        public Criteria andValidityEqualTo(Integer value) {
            addCriterion("validity =", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityNotEqualTo(Integer value) {
            addCriterion("validity <>", value, "validity");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andValidityGreaterThan(Integer value) {
            addCriterion("validity >", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity >=", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityLessThan(Integer value) {
            addCriterion("validity <", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityLessThanOrEqualTo(Integer value) {
            addCriterion("validity <=", value, "validity");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andValidityIsNull() {
            addCriterion("validity is null");
            return (Criteria) this;
        }

        public Criteria andValidityIsNotNull() {
            addCriterion("validity is not null");
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