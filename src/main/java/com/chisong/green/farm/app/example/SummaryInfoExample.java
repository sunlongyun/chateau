package com.chisong.green.farm.app.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
/**
* <p>
* 
* </p>
*
* @author 孙龙云
* @date 2020-05-02
*/
@Data
public class SummaryInfoExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SummaryInfoExample(){oredCriteria = new ArrayList<>();}

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
        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        //between
        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        //like
        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        // = <>
        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }
        public Criteria andIdLessThanOrEqualTo(Integer value) {
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
        public Criteria andOrderNumsIn(List<Long> values) {
            addCriterion("order_nums in", values, "orderNums");
            return (Criteria) this;
        }
        public Criteria andOrderNumsNotIn(List<Long> values) {
            addCriterion("order_nums not in", values, "orderNums");
            return (Criteria) this;
        }

        //between
        public Criteria andOrderNumsBetween(Long value1, Long value2) {
            addCriterion("order_nums between", value1, value2, "orderNums");
            return (Criteria) this;
        }

        //like
        public Criteria andOrderNumsLike(String value) {
            addCriterion("order_nums like", value, "orderNums");
            return (Criteria) this;
        }

        // = <>
        public Criteria andOrderNumsEqualTo(Long value) {
            addCriterion("order_nums =", value, "orderNums");
            return (Criteria) this;
        }
        public Criteria andOrderNumsNotEqualTo(Long value) {
            addCriterion("order_nums <>", value, "orderNums");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andOrderNumsGreaterThan(Long value) {
            addCriterion("order_nums >", value, "orderNums");
            return (Criteria) this;
        }
        public Criteria andOrderNumsGreaterThanOrEqualTo(Long value) {
            addCriterion("order_nums >=", value, "orderNums");
            return (Criteria) this;
        }
        public Criteria andOrderNumsLessThan(Long value) {
            addCriterion("order_nums <", value, "orderNums");
            return (Criteria) this;
        }
        public Criteria andOrderNumsLessThanOrEqualTo(Long value) {
            addCriterion("order_nums <=", value, "orderNums");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andOrderNumsIsNull() {
            addCriterion("order_nums is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumsIsNotNull() {
            addCriterion("order_nums is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andGoodsNormalNumIn(List<Integer> values) {
            addCriterion("goods_normal_num in", values, "goodsNormalNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNormalNumNotIn(List<Integer> values) {
            addCriterion("goods_normal_num not in", values, "goodsNormalNum");
            return (Criteria) this;
        }

        //between
        public Criteria andGoodsNormalNumBetween(Integer value1, Integer value2) {
            addCriterion("goods_normal_num between", value1, value2, "goodsNormalNum");
            return (Criteria) this;
        }

        //like
        public Criteria andGoodsNormalNumLike(String value) {
            addCriterion("goods_normal_num like", value, "goodsNormalNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andGoodsNormalNumEqualTo(Integer value) {
            addCriterion("goods_normal_num =", value, "goodsNormalNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNormalNumNotEqualTo(Integer value) {
            addCriterion("goods_normal_num <>", value, "goodsNormalNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andGoodsNormalNumGreaterThan(Integer value) {
            addCriterion("goods_normal_num >", value, "goodsNormalNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNormalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_normal_num >=", value, "goodsNormalNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNormalNumLessThan(Integer value) {
            addCriterion("goods_normal_num <", value, "goodsNormalNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNormalNumLessThanOrEqualTo(Integer value) {
            addCriterion("goods_normal_num <=", value, "goodsNormalNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andGoodsNormalNumIsNull() {
            addCriterion("goods_normal_num is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNormalNumIsNotNull() {
            addCriterion("goods_normal_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andGoodsNumIn(List<Integer> values) {
            addCriterion("goods_num in", values, "goodsNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNumNotIn(List<Integer> values) {
            addCriterion("goods_num not in", values, "goodsNum");
            return (Criteria) this;
        }

        //between
        public Criteria andGoodsNumBetween(Integer value1, Integer value2) {
            addCriterion("goods_num between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        //like
        public Criteria andGoodsNumLike(String value) {
            addCriterion("goods_num like", value, "goodsNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andGoodsNumEqualTo(Integer value) {
            addCriterion("goods_num =", value, "goodsNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNumNotEqualTo(Integer value) {
            addCriterion("goods_num <>", value, "goodsNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andGoodsNumGreaterThan(Integer value) {
            addCriterion("goods_num >", value, "goodsNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_num >=", value, "goodsNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNumLessThan(Integer value) {
            addCriterion("goods_num <", value, "goodsNum");
            return (Criteria) this;
        }
        public Criteria andGoodsNumLessThanOrEqualTo(Integer value) {
            addCriterion("goods_num <=", value, "goodsNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andGoodsNumIsNull() {
            addCriterion("goods_num is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIsNotNull() {
            addCriterion("goods_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTodayPayedNumIn(List<Long> values) {
            addCriterion("today_payed_num in", values, "todayPayedNum");
            return (Criteria) this;
        }
        public Criteria andTodayPayedNumNotIn(List<Long> values) {
            addCriterion("today_payed_num not in", values, "todayPayedNum");
            return (Criteria) this;
        }

        //between
        public Criteria andTodayPayedNumBetween(Long value1, Long value2) {
            addCriterion("today_payed_num between", value1, value2, "todayPayedNum");
            return (Criteria) this;
        }

        //like
        public Criteria andTodayPayedNumLike(String value) {
            addCriterion("today_payed_num like", value, "todayPayedNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTodayPayedNumEqualTo(Long value) {
            addCriterion("today_payed_num =", value, "todayPayedNum");
            return (Criteria) this;
        }
        public Criteria andTodayPayedNumNotEqualTo(Long value) {
            addCriterion("today_payed_num <>", value, "todayPayedNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTodayPayedNumGreaterThan(Long value) {
            addCriterion("today_payed_num >", value, "todayPayedNum");
            return (Criteria) this;
        }
        public Criteria andTodayPayedNumGreaterThanOrEqualTo(Long value) {
            addCriterion("today_payed_num >=", value, "todayPayedNum");
            return (Criteria) this;
        }
        public Criteria andTodayPayedNumLessThan(Long value) {
            addCriterion("today_payed_num <", value, "todayPayedNum");
            return (Criteria) this;
        }
        public Criteria andTodayPayedNumLessThanOrEqualTo(Long value) {
            addCriterion("today_payed_num <=", value, "todayPayedNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTodayPayedNumIsNull() {
            addCriterion("today_payed_num is null");
            return (Criteria) this;
        }

        public Criteria andTodayPayedNumIsNotNull() {
            addCriterion("today_payed_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMonthPayedNumIn(List<Long> values) {
            addCriterion("month_payed_num in", values, "monthPayedNum");
            return (Criteria) this;
        }
        public Criteria andMonthPayedNumNotIn(List<Long> values) {
            addCriterion("month_payed_num not in", values, "monthPayedNum");
            return (Criteria) this;
        }

        //between
        public Criteria andMonthPayedNumBetween(Long value1, Long value2) {
            addCriterion("month_payed_num between", value1, value2, "monthPayedNum");
            return (Criteria) this;
        }

        //like
        public Criteria andMonthPayedNumLike(String value) {
            addCriterion("month_payed_num like", value, "monthPayedNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMonthPayedNumEqualTo(Long value) {
            addCriterion("month_payed_num =", value, "monthPayedNum");
            return (Criteria) this;
        }
        public Criteria andMonthPayedNumNotEqualTo(Long value) {
            addCriterion("month_payed_num <>", value, "monthPayedNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMonthPayedNumGreaterThan(Long value) {
            addCriterion("month_payed_num >", value, "monthPayedNum");
            return (Criteria) this;
        }
        public Criteria andMonthPayedNumGreaterThanOrEqualTo(Long value) {
            addCriterion("month_payed_num >=", value, "monthPayedNum");
            return (Criteria) this;
        }
        public Criteria andMonthPayedNumLessThan(Long value) {
            addCriterion("month_payed_num <", value, "monthPayedNum");
            return (Criteria) this;
        }
        public Criteria andMonthPayedNumLessThanOrEqualTo(Long value) {
            addCriterion("month_payed_num <=", value, "monthPayedNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMonthPayedNumIsNull() {
            addCriterion("month_payed_num is null");
            return (Criteria) this;
        }

        public Criteria andMonthPayedNumIsNotNull() {
            addCriterion("month_payed_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andWeekPayedNumIn(List<Long> values) {
            addCriterion("week_payed_num in", values, "weekPayedNum");
            return (Criteria) this;
        }
        public Criteria andWeekPayedNumNotIn(List<Long> values) {
            addCriterion("week_payed_num not in", values, "weekPayedNum");
            return (Criteria) this;
        }

        //between
        public Criteria andWeekPayedNumBetween(Long value1, Long value2) {
            addCriterion("week_payed_num between", value1, value2, "weekPayedNum");
            return (Criteria) this;
        }

        //like
        public Criteria andWeekPayedNumLike(String value) {
            addCriterion("week_payed_num like", value, "weekPayedNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andWeekPayedNumEqualTo(Long value) {
            addCriterion("week_payed_num =", value, "weekPayedNum");
            return (Criteria) this;
        }
        public Criteria andWeekPayedNumNotEqualTo(Long value) {
            addCriterion("week_payed_num <>", value, "weekPayedNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andWeekPayedNumGreaterThan(Long value) {
            addCriterion("week_payed_num >", value, "weekPayedNum");
            return (Criteria) this;
        }
        public Criteria andWeekPayedNumGreaterThanOrEqualTo(Long value) {
            addCriterion("week_payed_num >=", value, "weekPayedNum");
            return (Criteria) this;
        }
        public Criteria andWeekPayedNumLessThan(Long value) {
            addCriterion("week_payed_num <", value, "weekPayedNum");
            return (Criteria) this;
        }
        public Criteria andWeekPayedNumLessThanOrEqualTo(Long value) {
            addCriterion("week_payed_num <=", value, "weekPayedNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andWeekPayedNumIsNull() {
            addCriterion("week_payed_num is null");
            return (Criteria) this;
        }

        public Criteria andWeekPayedNumIsNotNull() {
            addCriterion("week_payed_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTodayPayedAmountIn(List<Long> values) {
            addCriterion("today_payed_amount in", values, "todayPayedAmount");
            return (Criteria) this;
        }
        public Criteria andTodayPayedAmountNotIn(List<Long> values) {
            addCriterion("today_payed_amount not in", values, "todayPayedAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andTodayPayedAmountBetween(Long value1, Long value2) {
            addCriterion("today_payed_amount between", value1, value2, "todayPayedAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andTodayPayedAmountLike(String value) {
            addCriterion("today_payed_amount like", value, "todayPayedAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTodayPayedAmountEqualTo(Long value) {
            addCriterion("today_payed_amount =", value, "todayPayedAmount");
            return (Criteria) this;
        }
        public Criteria andTodayPayedAmountNotEqualTo(Long value) {
            addCriterion("today_payed_amount <>", value, "todayPayedAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTodayPayedAmountGreaterThan(Long value) {
            addCriterion("today_payed_amount >", value, "todayPayedAmount");
            return (Criteria) this;
        }
        public Criteria andTodayPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("today_payed_amount >=", value, "todayPayedAmount");
            return (Criteria) this;
        }
        public Criteria andTodayPayedAmountLessThan(Long value) {
            addCriterion("today_payed_amount <", value, "todayPayedAmount");
            return (Criteria) this;
        }
        public Criteria andTodayPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("today_payed_amount <=", value, "todayPayedAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTodayPayedAmountIsNull() {
            addCriterion("today_payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andTodayPayedAmountIsNotNull() {
            addCriterion("today_payed_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMonthPayedAmountIn(List<Long> values) {
            addCriterion("month_payed_amount in", values, "monthPayedAmount");
            return (Criteria) this;
        }
        public Criteria andMonthPayedAmountNotIn(List<Long> values) {
            addCriterion("month_payed_amount not in", values, "monthPayedAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andMonthPayedAmountBetween(Long value1, Long value2) {
            addCriterion("month_payed_amount between", value1, value2, "monthPayedAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andMonthPayedAmountLike(String value) {
            addCriterion("month_payed_amount like", value, "monthPayedAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMonthPayedAmountEqualTo(Long value) {
            addCriterion("month_payed_amount =", value, "monthPayedAmount");
            return (Criteria) this;
        }
        public Criteria andMonthPayedAmountNotEqualTo(Long value) {
            addCriterion("month_payed_amount <>", value, "monthPayedAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMonthPayedAmountGreaterThan(Long value) {
            addCriterion("month_payed_amount >", value, "monthPayedAmount");
            return (Criteria) this;
        }
        public Criteria andMonthPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("month_payed_amount >=", value, "monthPayedAmount");
            return (Criteria) this;
        }
        public Criteria andMonthPayedAmountLessThan(Long value) {
            addCriterion("month_payed_amount <", value, "monthPayedAmount");
            return (Criteria) this;
        }
        public Criteria andMonthPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("month_payed_amount <=", value, "monthPayedAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMonthPayedAmountIsNull() {
            addCriterion("month_payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andMonthPayedAmountIsNotNull() {
            addCriterion("month_payed_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andWeekPayedAmountIn(List<Long> values) {
            addCriterion("week_payed_amount in", values, "weekPayedAmount");
            return (Criteria) this;
        }
        public Criteria andWeekPayedAmountNotIn(List<Long> values) {
            addCriterion("week_payed_amount not in", values, "weekPayedAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andWeekPayedAmountBetween(Long value1, Long value2) {
            addCriterion("week_payed_amount between", value1, value2, "weekPayedAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andWeekPayedAmountLike(String value) {
            addCriterion("week_payed_amount like", value, "weekPayedAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andWeekPayedAmountEqualTo(Long value) {
            addCriterion("week_payed_amount =", value, "weekPayedAmount");
            return (Criteria) this;
        }
        public Criteria andWeekPayedAmountNotEqualTo(Long value) {
            addCriterion("week_payed_amount <>", value, "weekPayedAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andWeekPayedAmountGreaterThan(Long value) {
            addCriterion("week_payed_amount >", value, "weekPayedAmount");
            return (Criteria) this;
        }
        public Criteria andWeekPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("week_payed_amount >=", value, "weekPayedAmount");
            return (Criteria) this;
        }
        public Criteria andWeekPayedAmountLessThan(Long value) {
            addCriterion("week_payed_amount <", value, "weekPayedAmount");
            return (Criteria) this;
        }
        public Criteria andWeekPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("week_payed_amount <=", value, "weekPayedAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andWeekPayedAmountIsNull() {
            addCriterion("week_payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andWeekPayedAmountIsNotNull() {
            addCriterion("week_payed_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTodayCustomerNumIn(List<Integer> values) {
            addCriterion("today_customer_num in", values, "todayCustomerNum");
            return (Criteria) this;
        }
        public Criteria andTodayCustomerNumNotIn(List<Integer> values) {
            addCriterion("today_customer_num not in", values, "todayCustomerNum");
            return (Criteria) this;
        }

        //between
        public Criteria andTodayCustomerNumBetween(Integer value1, Integer value2) {
            addCriterion("today_customer_num between", value1, value2, "todayCustomerNum");
            return (Criteria) this;
        }

        //like
        public Criteria andTodayCustomerNumLike(String value) {
            addCriterion("today_customer_num like", value, "todayCustomerNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTodayCustomerNumEqualTo(Integer value) {
            addCriterion("today_customer_num =", value, "todayCustomerNum");
            return (Criteria) this;
        }
        public Criteria andTodayCustomerNumNotEqualTo(Integer value) {
            addCriterion("today_customer_num <>", value, "todayCustomerNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTodayCustomerNumGreaterThan(Integer value) {
            addCriterion("today_customer_num >", value, "todayCustomerNum");
            return (Criteria) this;
        }
        public Criteria andTodayCustomerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("today_customer_num >=", value, "todayCustomerNum");
            return (Criteria) this;
        }
        public Criteria andTodayCustomerNumLessThan(Integer value) {
            addCriterion("today_customer_num <", value, "todayCustomerNum");
            return (Criteria) this;
        }
        public Criteria andTodayCustomerNumLessThanOrEqualTo(Integer value) {
            addCriterion("today_customer_num <=", value, "todayCustomerNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTodayCustomerNumIsNull() {
            addCriterion("today_customer_num is null");
            return (Criteria) this;
        }

        public Criteria andTodayCustomerNumIsNotNull() {
            addCriterion("today_customer_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMonthCustomerNumIn(List<Integer> values) {
            addCriterion("month_customer_num in", values, "monthCustomerNum");
            return (Criteria) this;
        }
        public Criteria andMonthCustomerNumNotIn(List<Integer> values) {
            addCriterion("month_customer_num not in", values, "monthCustomerNum");
            return (Criteria) this;
        }

        //between
        public Criteria andMonthCustomerNumBetween(Integer value1, Integer value2) {
            addCriterion("month_customer_num between", value1, value2, "monthCustomerNum");
            return (Criteria) this;
        }

        //like
        public Criteria andMonthCustomerNumLike(String value) {
            addCriterion("month_customer_num like", value, "monthCustomerNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMonthCustomerNumEqualTo(Integer value) {
            addCriterion("month_customer_num =", value, "monthCustomerNum");
            return (Criteria) this;
        }
        public Criteria andMonthCustomerNumNotEqualTo(Integer value) {
            addCriterion("month_customer_num <>", value, "monthCustomerNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMonthCustomerNumGreaterThan(Integer value) {
            addCriterion("month_customer_num >", value, "monthCustomerNum");
            return (Criteria) this;
        }
        public Criteria andMonthCustomerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("month_customer_num >=", value, "monthCustomerNum");
            return (Criteria) this;
        }
        public Criteria andMonthCustomerNumLessThan(Integer value) {
            addCriterion("month_customer_num <", value, "monthCustomerNum");
            return (Criteria) this;
        }
        public Criteria andMonthCustomerNumLessThanOrEqualTo(Integer value) {
            addCriterion("month_customer_num <=", value, "monthCustomerNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMonthCustomerNumIsNull() {
            addCriterion("month_customer_num is null");
            return (Criteria) this;
        }

        public Criteria andMonthCustomerNumIsNotNull() {
            addCriterion("month_customer_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andWeekCustomerNumIn(List<Integer> values) {
            addCriterion("week_customer_num in", values, "weekCustomerNum");
            return (Criteria) this;
        }
        public Criteria andWeekCustomerNumNotIn(List<Integer> values) {
            addCriterion("week_customer_num not in", values, "weekCustomerNum");
            return (Criteria) this;
        }

        //between
        public Criteria andWeekCustomerNumBetween(Integer value1, Integer value2) {
            addCriterion("week_customer_num between", value1, value2, "weekCustomerNum");
            return (Criteria) this;
        }

        //like
        public Criteria andWeekCustomerNumLike(String value) {
            addCriterion("week_customer_num like", value, "weekCustomerNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andWeekCustomerNumEqualTo(Integer value) {
            addCriterion("week_customer_num =", value, "weekCustomerNum");
            return (Criteria) this;
        }
        public Criteria andWeekCustomerNumNotEqualTo(Integer value) {
            addCriterion("week_customer_num <>", value, "weekCustomerNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andWeekCustomerNumGreaterThan(Integer value) {
            addCriterion("week_customer_num >", value, "weekCustomerNum");
            return (Criteria) this;
        }
        public Criteria andWeekCustomerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("week_customer_num >=", value, "weekCustomerNum");
            return (Criteria) this;
        }
        public Criteria andWeekCustomerNumLessThan(Integer value) {
            addCriterion("week_customer_num <", value, "weekCustomerNum");
            return (Criteria) this;
        }
        public Criteria andWeekCustomerNumLessThanOrEqualTo(Integer value) {
            addCriterion("week_customer_num <=", value, "weekCustomerNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andWeekCustomerNumIsNull() {
            addCriterion("week_customer_num is null");
            return (Criteria) this;
        }

        public Criteria andWeekCustomerNumIsNotNull() {
            addCriterion("week_customer_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayedNumsIn(List<Long> values) {
            addCriterion("payed_nums in", values, "payedNums");
            return (Criteria) this;
        }
        public Criteria andPayedNumsNotIn(List<Long> values) {
            addCriterion("payed_nums not in", values, "payedNums");
            return (Criteria) this;
        }

        //between
        public Criteria andPayedNumsBetween(Long value1, Long value2) {
            addCriterion("payed_nums between", value1, value2, "payedNums");
            return (Criteria) this;
        }

        //like
        public Criteria andPayedNumsLike(String value) {
            addCriterion("payed_nums like", value, "payedNums");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayedNumsEqualTo(Long value) {
            addCriterion("payed_nums =", value, "payedNums");
            return (Criteria) this;
        }
        public Criteria andPayedNumsNotEqualTo(Long value) {
            addCriterion("payed_nums <>", value, "payedNums");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayedNumsGreaterThan(Long value) {
            addCriterion("payed_nums >", value, "payedNums");
            return (Criteria) this;
        }
        public Criteria andPayedNumsGreaterThanOrEqualTo(Long value) {
            addCriterion("payed_nums >=", value, "payedNums");
            return (Criteria) this;
        }
        public Criteria andPayedNumsLessThan(Long value) {
            addCriterion("payed_nums <", value, "payedNums");
            return (Criteria) this;
        }
        public Criteria andPayedNumsLessThanOrEqualTo(Long value) {
            addCriterion("payed_nums <=", value, "payedNums");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayedNumsIsNull() {
            addCriterion("payed_nums is null");
            return (Criteria) this;
        }

        public Criteria andPayedNumsIsNotNull() {
            addCriterion("payed_nums is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSupplierNumIn(List<Integer> values) {
            addCriterion("supplier_num in", values, "supplierNum");
            return (Criteria) this;
        }
        public Criteria andSupplierNumNotIn(List<Integer> values) {
            addCriterion("supplier_num not in", values, "supplierNum");
            return (Criteria) this;
        }

        //between
        public Criteria andSupplierNumBetween(Integer value1, Integer value2) {
            addCriterion("supplier_num between", value1, value2, "supplierNum");
            return (Criteria) this;
        }

        //like
        public Criteria andSupplierNumLike(String value) {
            addCriterion("supplier_num like", value, "supplierNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSupplierNumEqualTo(Integer value) {
            addCriterion("supplier_num =", value, "supplierNum");
            return (Criteria) this;
        }
        public Criteria andSupplierNumNotEqualTo(Integer value) {
            addCriterion("supplier_num <>", value, "supplierNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSupplierNumGreaterThan(Integer value) {
            addCriterion("supplier_num >", value, "supplierNum");
            return (Criteria) this;
        }
        public Criteria andSupplierNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_num >=", value, "supplierNum");
            return (Criteria) this;
        }
        public Criteria andSupplierNumLessThan(Integer value) {
            addCriterion("supplier_num <", value, "supplierNum");
            return (Criteria) this;
        }
        public Criteria andSupplierNumLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_num <=", value, "supplierNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSupplierNumIsNull() {
            addCriterion("supplier_num is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNumIsNotNull() {
            addCriterion("supplier_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCustomerNumIn(List<Long> values) {
            addCriterion("customer_num in", values, "customerNum");
            return (Criteria) this;
        }
        public Criteria andCustomerNumNotIn(List<Long> values) {
            addCriterion("customer_num not in", values, "customerNum");
            return (Criteria) this;
        }

        //between
        public Criteria andCustomerNumBetween(Long value1, Long value2) {
            addCriterion("customer_num between", value1, value2, "customerNum");
            return (Criteria) this;
        }

        //like
        public Criteria andCustomerNumLike(String value) {
            addCriterion("customer_num like", value, "customerNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCustomerNumEqualTo(Long value) {
            addCriterion("customer_num =", value, "customerNum");
            return (Criteria) this;
        }
        public Criteria andCustomerNumNotEqualTo(Long value) {
            addCriterion("customer_num <>", value, "customerNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCustomerNumGreaterThan(Long value) {
            addCriterion("customer_num >", value, "customerNum");
            return (Criteria) this;
        }
        public Criteria andCustomerNumGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_num >=", value, "customerNum");
            return (Criteria) this;
        }
        public Criteria andCustomerNumLessThan(Long value) {
            addCriterion("customer_num <", value, "customerNum");
            return (Criteria) this;
        }
        public Criteria andCustomerNumLessThanOrEqualTo(Long value) {
            addCriterion("customer_num <=", value, "customerNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCustomerNumIsNull() {
            addCriterion("customer_num is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNumIsNotNull() {
            addCriterion("customer_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayedAmountIn(List<Long> values) {
            addCriterion("payed_amount in", values, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountNotIn(List<Long> values) {
            addCriterion("payed_amount not in", values, "payedAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andPayedAmountBetween(Long value1, Long value2) {
            addCriterion("payed_amount between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andPayedAmountLike(String value) {
            addCriterion("payed_amount like", value, "payedAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayedAmountEqualTo(Long value) {
            addCriterion("payed_amount =", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountNotEqualTo(Long value) {
            addCriterion("payed_amount <>", value, "payedAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayedAmountGreaterThan(Long value) {
            addCriterion("payed_amount >", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("payed_amount >=", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountLessThan(Long value) {
            addCriterion("payed_amount <", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("payed_amount <=", value, "payedAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayedAmountIsNull() {
            addCriterion("payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNotNull() {
            addCriterion("payed_amount is not null");
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
        // in,   not in
        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        //between
        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        //like
        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        //between
        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        //like
        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }
        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
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