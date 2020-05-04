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
* @date 2020-05-04
*/
@Data
public class PaymentExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaymentExample(){oredCriteria = new ArrayList<>();}

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
        public Criteria andPayOrderNoIn(List<String> values) {
            addCriterion("pay_order_no in", values, "payOrderNo");
            return (Criteria) this;
        }
        public Criteria andPayOrderNoNotIn(List<String> values) {
            addCriterion("pay_order_no not in", values, "payOrderNo");
            return (Criteria) this;
        }

        //between
        public Criteria andPayOrderNoBetween(String value1, String value2) {
            addCriterion("pay_order_no between", value1, value2, "payOrderNo");
            return (Criteria) this;
        }

        //like
        public Criteria andPayOrderNoLike(String value) {
            addCriterion("pay_order_no like", value, "payOrderNo");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayOrderNoEqualTo(String value) {
            addCriterion("pay_order_no =", value, "payOrderNo");
            return (Criteria) this;
        }
        public Criteria andPayOrderNoNotEqualTo(String value) {
            addCriterion("pay_order_no <>", value, "payOrderNo");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayOrderNoGreaterThan(String value) {
            addCriterion("pay_order_no >", value, "payOrderNo");
            return (Criteria) this;
        }
        public Criteria andPayOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_order_no >=", value, "payOrderNo");
            return (Criteria) this;
        }
        public Criteria andPayOrderNoLessThan(String value) {
            addCriterion("pay_order_no <", value, "payOrderNo");
            return (Criteria) this;
        }
        public Criteria andPayOrderNoLessThanOrEqualTo(String value) {
            addCriterion("pay_order_no <=", value, "payOrderNo");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayOrderNoIsNull() {
            addCriterion("pay_order_no is null");
            return (Criteria) this;
        }

        public Criteria andPayOrderNoIsNotNull() {
            addCriterion("pay_order_no is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayNoIn(List<String> values) {
            addCriterion("pay_no in", values, "payNo");
            return (Criteria) this;
        }
        public Criteria andPayNoNotIn(List<String> values) {
            addCriterion("pay_no not in", values, "payNo");
            return (Criteria) this;
        }

        //between
        public Criteria andPayNoBetween(String value1, String value2) {
            addCriterion("pay_no between", value1, value2, "payNo");
            return (Criteria) this;
        }

        //like
        public Criteria andPayNoLike(String value) {
            addCriterion("pay_no like", value, "payNo");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayNoEqualTo(String value) {
            addCriterion("pay_no =", value, "payNo");
            return (Criteria) this;
        }
        public Criteria andPayNoNotEqualTo(String value) {
            addCriterion("pay_no <>", value, "payNo");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayNoGreaterThan(String value) {
            addCriterion("pay_no >", value, "payNo");
            return (Criteria) this;
        }
        public Criteria andPayNoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_no >=", value, "payNo");
            return (Criteria) this;
        }
        public Criteria andPayNoLessThan(String value) {
            addCriterion("pay_no <", value, "payNo");
            return (Criteria) this;
        }
        public Criteria andPayNoLessThanOrEqualTo(String value) {
            addCriterion("pay_no <=", value, "payNo");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayNoIsNull() {
            addCriterion("pay_no is null");
            return (Criteria) this;
        }

        public Criteria andPayNoIsNotNull() {
            addCriterion("pay_no is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPrePayIdIn(List<String> values) {
            addCriterion("pre_pay_id in", values, "prePayId");
            return (Criteria) this;
        }
        public Criteria andPrePayIdNotIn(List<String> values) {
            addCriterion("pre_pay_id not in", values, "prePayId");
            return (Criteria) this;
        }

        //between
        public Criteria andPrePayIdBetween(String value1, String value2) {
            addCriterion("pre_pay_id between", value1, value2, "prePayId");
            return (Criteria) this;
        }

        //like
        public Criteria andPrePayIdLike(String value) {
            addCriterion("pre_pay_id like", value, "prePayId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPrePayIdEqualTo(String value) {
            addCriterion("pre_pay_id =", value, "prePayId");
            return (Criteria) this;
        }
        public Criteria andPrePayIdNotEqualTo(String value) {
            addCriterion("pre_pay_id <>", value, "prePayId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPrePayIdGreaterThan(String value) {
            addCriterion("pre_pay_id >", value, "prePayId");
            return (Criteria) this;
        }
        public Criteria andPrePayIdGreaterThanOrEqualTo(String value) {
            addCriterion("pre_pay_id >=", value, "prePayId");
            return (Criteria) this;
        }
        public Criteria andPrePayIdLessThan(String value) {
            addCriterion("pre_pay_id <", value, "prePayId");
            return (Criteria) this;
        }
        public Criteria andPrePayIdLessThanOrEqualTo(String value) {
            addCriterion("pre_pay_id <=", value, "prePayId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPrePayIdIsNull() {
            addCriterion("pre_pay_id is null");
            return (Criteria) this;
        }

        public Criteria andPrePayIdIsNotNull() {
            addCriterion("pre_pay_id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayedAmountIn(List<Integer> values) {
            addCriterion("payed_amount in", values, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountNotIn(List<Integer> values) {
            addCriterion("payed_amount not in", values, "payedAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andPayedAmountBetween(Integer value1, Integer value2) {
            addCriterion("payed_amount between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andPayedAmountLike(String value) {
            addCriterion("payed_amount like", value, "payedAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayedAmountEqualTo(Integer value) {
            addCriterion("payed_amount =", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountNotEqualTo(Integer value) {
            addCriterion("payed_amount <>", value, "payedAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayedAmountGreaterThan(Integer value) {
            addCriterion("payed_amount >", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("payed_amount >=", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountLessThan(Integer value) {
            addCriterion("payed_amount <", value, "payedAmount");
            return (Criteria) this;
        }
        public Criteria andPayedAmountLessThanOrEqualTo(Integer value) {
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
        public Criteria andThirdPayNoIn(List<String> values) {
            addCriterion("third_pay_no in", values, "thirdPayNo");
            return (Criteria) this;
        }
        public Criteria andThirdPayNoNotIn(List<String> values) {
            addCriterion("third_pay_no not in", values, "thirdPayNo");
            return (Criteria) this;
        }

        //between
        public Criteria andThirdPayNoBetween(String value1, String value2) {
            addCriterion("third_pay_no between", value1, value2, "thirdPayNo");
            return (Criteria) this;
        }

        //like
        public Criteria andThirdPayNoLike(String value) {
            addCriterion("third_pay_no like", value, "thirdPayNo");
            return (Criteria) this;
        }

        // = <>
        public Criteria andThirdPayNoEqualTo(String value) {
            addCriterion("third_pay_no =", value, "thirdPayNo");
            return (Criteria) this;
        }
        public Criteria andThirdPayNoNotEqualTo(String value) {
            addCriterion("third_pay_no <>", value, "thirdPayNo");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andThirdPayNoGreaterThan(String value) {
            addCriterion("third_pay_no >", value, "thirdPayNo");
            return (Criteria) this;
        }
        public Criteria andThirdPayNoGreaterThanOrEqualTo(String value) {
            addCriterion("third_pay_no >=", value, "thirdPayNo");
            return (Criteria) this;
        }
        public Criteria andThirdPayNoLessThan(String value) {
            addCriterion("third_pay_no <", value, "thirdPayNo");
            return (Criteria) this;
        }
        public Criteria andThirdPayNoLessThanOrEqualTo(String value) {
            addCriterion("third_pay_no <=", value, "thirdPayNo");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andThirdPayNoIsNull() {
            addCriterion("third_pay_no is null");
            return (Criteria) this;
        }

        public Criteria andThirdPayNoIsNotNull() {
            addCriterion("third_pay_no is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }
        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        //between
        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        //like
        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        // = <>
        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }
        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }
        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        //between
        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        //like
        public Criteria andPayTimeLike(String value) {
            addCriterion("pay_time like", value, "payTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }
        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }
        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }
        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }
        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPaySuccessTimeIn(List<Date> values) {
            addCriterion("pay_success_time in", values, "paySuccessTime");
            return (Criteria) this;
        }
        public Criteria andPaySuccessTimeNotIn(List<Date> values) {
            addCriterion("pay_success_time not in", values, "paySuccessTime");
            return (Criteria) this;
        }

        //between
        public Criteria andPaySuccessTimeBetween(Date value1, Date value2) {
            addCriterion("pay_success_time between", value1, value2, "paySuccessTime");
            return (Criteria) this;
        }

        //like
        public Criteria andPaySuccessTimeLike(String value) {
            addCriterion("pay_success_time like", value, "paySuccessTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPaySuccessTimeEqualTo(Date value) {
            addCriterion("pay_success_time =", value, "paySuccessTime");
            return (Criteria) this;
        }
        public Criteria andPaySuccessTimeNotEqualTo(Date value) {
            addCriterion("pay_success_time <>", value, "paySuccessTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPaySuccessTimeGreaterThan(Date value) {
            addCriterion("pay_success_time >", value, "paySuccessTime");
            return (Criteria) this;
        }
        public Criteria andPaySuccessTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_success_time >=", value, "paySuccessTime");
            return (Criteria) this;
        }
        public Criteria andPaySuccessTimeLessThan(Date value) {
            addCriterion("pay_success_time <", value, "paySuccessTime");
            return (Criteria) this;
        }
        public Criteria andPaySuccessTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_success_time <=", value, "paySuccessTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPaySuccessTimeIsNull() {
            addCriterion("pay_success_time is null");
            return (Criteria) this;
        }

        public Criteria andPaySuccessTimeIsNotNull() {
            addCriterion("pay_success_time is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTimesIn(List<Integer> values) {
            addCriterion("times in", values, "times");
            return (Criteria) this;
        }
        public Criteria andTimesNotIn(List<Integer> values) {
            addCriterion("times not in", values, "times");
            return (Criteria) this;
        }

        //between
        public Criteria andTimesBetween(Integer value1, Integer value2) {
            addCriterion("times between", value1, value2, "times");
            return (Criteria) this;
        }

        //like
        public Criteria andTimesLike(String value) {
            addCriterion("times like", value, "times");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTimesEqualTo(Integer value) {
            addCriterion("times =", value, "times");
            return (Criteria) this;
        }
        public Criteria andTimesNotEqualTo(Integer value) {
            addCriterion("times <>", value, "times");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTimesGreaterThan(Integer value) {
            addCriterion("times >", value, "times");
            return (Criteria) this;
        }
        public Criteria andTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("times >=", value, "times");
            return (Criteria) this;
        }
        public Criteria andTimesLessThan(Integer value) {
            addCriterion("times <", value, "times");
            return (Criteria) this;
        }
        public Criteria andTimesLessThanOrEqualTo(Integer value) {
            addCriterion("times <=", value, "times");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTimesIsNull() {
            addCriterion("times is null");
            return (Criteria) this;
        }

        public Criteria andTimesIsNotNull() {
            addCriterion("times is not null");
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