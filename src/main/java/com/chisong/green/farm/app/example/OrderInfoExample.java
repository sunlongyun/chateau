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
* @date 2020-04-06
*/
@Data
public class OrderInfoExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample(){oredCriteria = new ArrayList<>();}

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
        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }
        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        //between
        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        //like
        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        // = <>
        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }
        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }
        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }
        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }
        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }
        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        //between
        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        //like
        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }
        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }
        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }
        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }
        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }
        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        //between
        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        //like
        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }
        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }
        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }
        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }
        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCustomerIdIn(List<Long> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }
        public Criteria andCustomerIdNotIn(List<Long> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        //between
        public Criteria andCustomerIdBetween(Long value1, Long value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        //like
        public Criteria andCustomerIdLike(String value) {
            addCriterion("customer_id like", value, "customerId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCustomerIdEqualTo(Long value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }
        public Criteria andCustomerIdNotEqualTo(Long value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCustomerIdGreaterThan(Long value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }
        public Criteria andCustomerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }
        public Criteria andCustomerIdLessThan(Long value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }
        public Criteria andCustomerIdLessThanOrEqualTo(Long value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
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
        public Criteria andCancelOptIn(List<Integer> values) {
            addCriterion("cancel_opt in", values, "cancelOpt");
            return (Criteria) this;
        }
        public Criteria andCancelOptNotIn(List<Integer> values) {
            addCriterion("cancel_opt not in", values, "cancelOpt");
            return (Criteria) this;
        }

        //between
        public Criteria andCancelOptBetween(Integer value1, Integer value2) {
            addCriterion("cancel_opt between", value1, value2, "cancelOpt");
            return (Criteria) this;
        }

        //like
        public Criteria andCancelOptLike(String value) {
            addCriterion("cancel_opt like", value, "cancelOpt");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCancelOptEqualTo(Integer value) {
            addCriterion("cancel_opt =", value, "cancelOpt");
            return (Criteria) this;
        }
        public Criteria andCancelOptNotEqualTo(Integer value) {
            addCriterion("cancel_opt <>", value, "cancelOpt");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCancelOptGreaterThan(Integer value) {
            addCriterion("cancel_opt >", value, "cancelOpt");
            return (Criteria) this;
        }
        public Criteria andCancelOptGreaterThanOrEqualTo(Integer value) {
            addCriterion("cancel_opt >=", value, "cancelOpt");
            return (Criteria) this;
        }
        public Criteria andCancelOptLessThan(Integer value) {
            addCriterion("cancel_opt <", value, "cancelOpt");
            return (Criteria) this;
        }
        public Criteria andCancelOptLessThanOrEqualTo(Integer value) {
            addCriterion("cancel_opt <=", value, "cancelOpt");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCancelOptIsNull() {
            addCriterion("cancel_opt is null");
            return (Criteria) this;
        }

        public Criteria andCancelOptIsNotNull() {
            addCriterion("cancel_opt is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCancelRemarkIn(List<String> values) {
            addCriterion("cancel_remark in", values, "cancelRemark");
            return (Criteria) this;
        }
        public Criteria andCancelRemarkNotIn(List<String> values) {
            addCriterion("cancel_remark not in", values, "cancelRemark");
            return (Criteria) this;
        }

        //between
        public Criteria andCancelRemarkBetween(String value1, String value2) {
            addCriterion("cancel_remark between", value1, value2, "cancelRemark");
            return (Criteria) this;
        }

        //like
        public Criteria andCancelRemarkLike(String value) {
            addCriterion("cancel_remark like", value, "cancelRemark");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCancelRemarkEqualTo(String value) {
            addCriterion("cancel_remark =", value, "cancelRemark");
            return (Criteria) this;
        }
        public Criteria andCancelRemarkNotEqualTo(String value) {
            addCriterion("cancel_remark <>", value, "cancelRemark");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCancelRemarkGreaterThan(String value) {
            addCriterion("cancel_remark >", value, "cancelRemark");
            return (Criteria) this;
        }
        public Criteria andCancelRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_remark >=", value, "cancelRemark");
            return (Criteria) this;
        }
        public Criteria andCancelRemarkLessThan(String value) {
            addCriterion("cancel_remark <", value, "cancelRemark");
            return (Criteria) this;
        }
        public Criteria andCancelRemarkLessThanOrEqualTo(String value) {
            addCriterion("cancel_remark <=", value, "cancelRemark");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCancelRemarkIsNull() {
            addCriterion("cancel_remark is null");
            return (Criteria) this;
        }

        public Criteria andCancelRemarkIsNotNull() {
            addCriterion("cancel_remark is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPostageIn(List<Integer> values) {
            addCriterion("postage in", values, "postage");
            return (Criteria) this;
        }
        public Criteria andPostageNotIn(List<Integer> values) {
            addCriterion("postage not in", values, "postage");
            return (Criteria) this;
        }

        //between
        public Criteria andPostageBetween(Integer value1, Integer value2) {
            addCriterion("postage between", value1, value2, "postage");
            return (Criteria) this;
        }

        //like
        public Criteria andPostageLike(String value) {
            addCriterion("postage like", value, "postage");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPostageEqualTo(Integer value) {
            addCriterion("postage =", value, "postage");
            return (Criteria) this;
        }
        public Criteria andPostageNotEqualTo(Integer value) {
            addCriterion("postage <>", value, "postage");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPostageGreaterThan(Integer value) {
            addCriterion("postage >", value, "postage");
            return (Criteria) this;
        }
        public Criteria andPostageGreaterThanOrEqualTo(Integer value) {
            addCriterion("postage >=", value, "postage");
            return (Criteria) this;
        }
        public Criteria andPostageLessThan(Integer value) {
            addCriterion("postage <", value, "postage");
            return (Criteria) this;
        }
        public Criteria andPostageLessThanOrEqualTo(Integer value) {
            addCriterion("postage <=", value, "postage");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPostageIsNull() {
            addCriterion("postage is null");
            return (Criteria) this;
        }

        public Criteria andPostageIsNotNull() {
            addCriterion("postage is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andLogisticsNumberIn(List<String> values) {
            addCriterion("logistics_number in", values, "logisticsNumber");
            return (Criteria) this;
        }
        public Criteria andLogisticsNumberNotIn(List<String> values) {
            addCriterion("logistics_number not in", values, "logisticsNumber");
            return (Criteria) this;
        }

        //between
        public Criteria andLogisticsNumberBetween(String value1, String value2) {
            addCriterion("logistics_number between", value1, value2, "logisticsNumber");
            return (Criteria) this;
        }

        //like
        public Criteria andLogisticsNumberLike(String value) {
            addCriterion("logistics_number like", value, "logisticsNumber");
            return (Criteria) this;
        }

        // = <>
        public Criteria andLogisticsNumberEqualTo(String value) {
            addCriterion("logistics_number =", value, "logisticsNumber");
            return (Criteria) this;
        }
        public Criteria andLogisticsNumberNotEqualTo(String value) {
            addCriterion("logistics_number <>", value, "logisticsNumber");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andLogisticsNumberGreaterThan(String value) {
            addCriterion("logistics_number >", value, "logisticsNumber");
            return (Criteria) this;
        }
        public Criteria andLogisticsNumberGreaterThanOrEqualTo(String value) {
            addCriterion("logistics_number >=", value, "logisticsNumber");
            return (Criteria) this;
        }
        public Criteria andLogisticsNumberLessThan(String value) {
            addCriterion("logistics_number <", value, "logisticsNumber");
            return (Criteria) this;
        }
        public Criteria andLogisticsNumberLessThanOrEqualTo(String value) {
            addCriterion("logistics_number <=", value, "logisticsNumber");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andLogisticsNumberIsNull() {
            addCriterion("logistics_number is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsNumberIsNotNull() {
            addCriterion("logistics_number is not null");
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
        public Criteria andIncomeIn(List<Long> values) {
            addCriterion("income in", values, "income");
            return (Criteria) this;
        }
        public Criteria andIncomeNotIn(List<Long> values) {
            addCriterion("income not in", values, "income");
            return (Criteria) this;
        }

        //between
        public Criteria andIncomeBetween(Long value1, Long value2) {
            addCriterion("income between", value1, value2, "income");
            return (Criteria) this;
        }

        //like
        public Criteria andIncomeLike(String value) {
            addCriterion("income like", value, "income");
            return (Criteria) this;
        }

        // = <>
        public Criteria andIncomeEqualTo(Long value) {
            addCriterion("income =", value, "income");
            return (Criteria) this;
        }
        public Criteria andIncomeNotEqualTo(Long value) {
            addCriterion("income <>", value, "income");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andIncomeGreaterThan(Long value) {
            addCriterion("income >", value, "income");
            return (Criteria) this;
        }
        public Criteria andIncomeGreaterThanOrEqualTo(Long value) {
            addCriterion("income >=", value, "income");
            return (Criteria) this;
        }
        public Criteria andIncomeLessThan(Long value) {
            addCriterion("income <", value, "income");
            return (Criteria) this;
        }
        public Criteria andIncomeLessThanOrEqualTo(Long value) {
            addCriterion("income <=", value, "income");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andIncomeIsNull() {
            addCriterion("income is null");
            return (Criteria) this;
        }

        public Criteria andIncomeIsNotNull() {
            addCriterion("income is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSharedIn(List<Integer> values) {
            addCriterion("shared in", values, "shared");
            return (Criteria) this;
        }
        public Criteria andSharedNotIn(List<Integer> values) {
            addCriterion("shared not in", values, "shared");
            return (Criteria) this;
        }

        //between
        public Criteria andSharedBetween(Integer value1, Integer value2) {
            addCriterion("shared between", value1, value2, "shared");
            return (Criteria) this;
        }

        //like
        public Criteria andSharedLike(String value) {
            addCriterion("shared like", value, "shared");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSharedEqualTo(Integer value) {
            addCriterion("shared =", value, "shared");
            return (Criteria) this;
        }
        public Criteria andSharedNotEqualTo(Integer value) {
            addCriterion("shared <>", value, "shared");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSharedGreaterThan(Integer value) {
            addCriterion("shared >", value, "shared");
            return (Criteria) this;
        }
        public Criteria andSharedGreaterThanOrEqualTo(Integer value) {
            addCriterion("shared >=", value, "shared");
            return (Criteria) this;
        }
        public Criteria andSharedLessThan(Integer value) {
            addCriterion("shared <", value, "shared");
            return (Criteria) this;
        }
        public Criteria andSharedLessThanOrEqualTo(Integer value) {
            addCriterion("shared <=", value, "shared");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSharedIsNull() {
            addCriterion("shared is null");
            return (Criteria) this;
        }

        public Criteria andSharedIsNotNull() {
            addCriterion("shared is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSupplierPostageIn(List<Integer> values) {
            addCriterion("supplier_postage in", values, "supplierPostage");
            return (Criteria) this;
        }
        public Criteria andSupplierPostageNotIn(List<Integer> values) {
            addCriterion("supplier_postage not in", values, "supplierPostage");
            return (Criteria) this;
        }

        //between
        public Criteria andSupplierPostageBetween(Integer value1, Integer value2) {
            addCriterion("supplier_postage between", value1, value2, "supplierPostage");
            return (Criteria) this;
        }

        //like
        public Criteria andSupplierPostageLike(String value) {
            addCriterion("supplier_postage like", value, "supplierPostage");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSupplierPostageEqualTo(Integer value) {
            addCriterion("supplier_postage =", value, "supplierPostage");
            return (Criteria) this;
        }
        public Criteria andSupplierPostageNotEqualTo(Integer value) {
            addCriterion("supplier_postage <>", value, "supplierPostage");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSupplierPostageGreaterThan(Integer value) {
            addCriterion("supplier_postage >", value, "supplierPostage");
            return (Criteria) this;
        }
        public Criteria andSupplierPostageGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_postage >=", value, "supplierPostage");
            return (Criteria) this;
        }
        public Criteria andSupplierPostageLessThan(Integer value) {
            addCriterion("supplier_postage <", value, "supplierPostage");
            return (Criteria) this;
        }
        public Criteria andSupplierPostageLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_postage <=", value, "supplierPostage");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSupplierPostageIsNull() {
            addCriterion("supplier_postage is null");
            return (Criteria) this;
        }

        public Criteria andSupplierPostageIsNotNull() {
            addCriterion("supplier_postage is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSupplierAmountIn(List<Long> values) {
            addCriterion("supplier_amount in", values, "supplierAmount");
            return (Criteria) this;
        }
        public Criteria andSupplierAmountNotIn(List<Long> values) {
            addCriterion("supplier_amount not in", values, "supplierAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andSupplierAmountBetween(Long value1, Long value2) {
            addCriterion("supplier_amount between", value1, value2, "supplierAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andSupplierAmountLike(String value) {
            addCriterion("supplier_amount like", value, "supplierAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSupplierAmountEqualTo(Long value) {
            addCriterion("supplier_amount =", value, "supplierAmount");
            return (Criteria) this;
        }
        public Criteria andSupplierAmountNotEqualTo(Long value) {
            addCriterion("supplier_amount <>", value, "supplierAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSupplierAmountGreaterThan(Long value) {
            addCriterion("supplier_amount >", value, "supplierAmount");
            return (Criteria) this;
        }
        public Criteria andSupplierAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier_amount >=", value, "supplierAmount");
            return (Criteria) this;
        }
        public Criteria andSupplierAmountLessThan(Long value) {
            addCriterion("supplier_amount <", value, "supplierAmount");
            return (Criteria) this;
        }
        public Criteria andSupplierAmountLessThanOrEqualTo(Long value) {
            addCriterion("supplier_amount <=", value, "supplierAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSupplierAmountIsNull() {
            addCriterion("supplier_amount is null");
            return (Criteria) this;
        }

        public Criteria andSupplierAmountIsNotNull() {
            addCriterion("supplier_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSupplierStatusIn(List<Integer> values) {
            addCriterion("supplier_status in", values, "supplierStatus");
            return (Criteria) this;
        }
        public Criteria andSupplierStatusNotIn(List<Integer> values) {
            addCriterion("supplier_status not in", values, "supplierStatus");
            return (Criteria) this;
        }

        //between
        public Criteria andSupplierStatusBetween(Integer value1, Integer value2) {
            addCriterion("supplier_status between", value1, value2, "supplierStatus");
            return (Criteria) this;
        }

        //like
        public Criteria andSupplierStatusLike(String value) {
            addCriterion("supplier_status like", value, "supplierStatus");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSupplierStatusEqualTo(Integer value) {
            addCriterion("supplier_status =", value, "supplierStatus");
            return (Criteria) this;
        }
        public Criteria andSupplierStatusNotEqualTo(Integer value) {
            addCriterion("supplier_status <>", value, "supplierStatus");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSupplierStatusGreaterThan(Integer value) {
            addCriterion("supplier_status >", value, "supplierStatus");
            return (Criteria) this;
        }
        public Criteria andSupplierStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_status >=", value, "supplierStatus");
            return (Criteria) this;
        }
        public Criteria andSupplierStatusLessThan(Integer value) {
            addCriterion("supplier_status <", value, "supplierStatus");
            return (Criteria) this;
        }
        public Criteria andSupplierStatusLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_status <=", value, "supplierStatus");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSupplierStatusIsNull() {
            addCriterion("supplier_status is null");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusIsNotNull() {
            addCriterion("supplier_status is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCostAmountIn(List<Long> values) {
            addCriterion("cost_amount in", values, "costAmount");
            return (Criteria) this;
        }
        public Criteria andCostAmountNotIn(List<Long> values) {
            addCriterion("cost_amount not in", values, "costAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andCostAmountBetween(Long value1, Long value2) {
            addCriterion("cost_amount between", value1, value2, "costAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andCostAmountLike(String value) {
            addCriterion("cost_amount like", value, "costAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCostAmountEqualTo(Long value) {
            addCriterion("cost_amount =", value, "costAmount");
            return (Criteria) this;
        }
        public Criteria andCostAmountNotEqualTo(Long value) {
            addCriterion("cost_amount <>", value, "costAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCostAmountGreaterThan(Long value) {
            addCriterion("cost_amount >", value, "costAmount");
            return (Criteria) this;
        }
        public Criteria andCostAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("cost_amount >=", value, "costAmount");
            return (Criteria) this;
        }
        public Criteria andCostAmountLessThan(Long value) {
            addCriterion("cost_amount <", value, "costAmount");
            return (Criteria) this;
        }
        public Criteria andCostAmountLessThanOrEqualTo(Long value) {
            addCriterion("cost_amount <=", value, "costAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCostAmountIsNull() {
            addCriterion("cost_amount is null");
            return (Criteria) this;
        }

        public Criteria andCostAmountIsNotNull() {
            addCriterion("cost_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }
        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        //between
        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        //like
        public Criteria andManagerIdLike(String value) {
            addCriterion("manager_id like", value, "managerId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }
        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }
        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }
        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }
        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTotalAmountIn(List<Long> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }
        public Criteria andTotalAmountNotIn(List<Long> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andTotalAmountBetween(Long value1, Long value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andTotalAmountLike(String value) {
            addCriterion("total_amount like", value, "totalAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTotalAmountEqualTo(Long value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }
        public Criteria andTotalAmountNotEqualTo(Long value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTotalAmountGreaterThan(Long value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }
        public Criteria andTotalAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }
        public Criteria andTotalAmountLessThan(Long value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }
        public Criteria andTotalAmountLessThanOrEqualTo(Long value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
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