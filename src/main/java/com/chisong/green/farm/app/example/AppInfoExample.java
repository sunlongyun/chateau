package com.chisong.green.farm.app.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
/**
* <p>
* APP名称
* </p>
*
* @author 孙龙云
* @date 2020-10-07
*/
@Data
public class AppInfoExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppInfoExample(){oredCriteria = new ArrayList<>();}

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
        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }
        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        //between
        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        //like
        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }
        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }
        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }
        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }
        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }
        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        //between
        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        //like
        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        // = <>
        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }
        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }
        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }
        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }
        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andContactUserIn(List<String> values) {
            addCriterion("contact_user in", values, "contactUser");
            return (Criteria) this;
        }
        public Criteria andContactUserNotIn(List<String> values) {
            addCriterion("contact_user not in", values, "contactUser");
            return (Criteria) this;
        }

        //between
        public Criteria andContactUserBetween(String value1, String value2) {
            addCriterion("contact_user between", value1, value2, "contactUser");
            return (Criteria) this;
        }

        //like
        public Criteria andContactUserLike(String value) {
            addCriterion("contact_user like", value, "contactUser");
            return (Criteria) this;
        }

        // = <>
        public Criteria andContactUserEqualTo(String value) {
            addCriterion("contact_user =", value, "contactUser");
            return (Criteria) this;
        }
        public Criteria andContactUserNotEqualTo(String value) {
            addCriterion("contact_user <>", value, "contactUser");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andContactUserGreaterThan(String value) {
            addCriterion("contact_user >", value, "contactUser");
            return (Criteria) this;
        }
        public Criteria andContactUserGreaterThanOrEqualTo(String value) {
            addCriterion("contact_user >=", value, "contactUser");
            return (Criteria) this;
        }
        public Criteria andContactUserLessThan(String value) {
            addCriterion("contact_user <", value, "contactUser");
            return (Criteria) this;
        }
        public Criteria andContactUserLessThanOrEqualTo(String value) {
            addCriterion("contact_user <=", value, "contactUser");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andContactUserIsNull() {
            addCriterion("contact_user is null");
            return (Criteria) this;
        }

        public Criteria andContactUserIsNotNull() {
            addCriterion("contact_user is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }
        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        //between
        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        //like
        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        // = <>
        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }
        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }
        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }
        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }
        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPaySupplierIn(List<Boolean> values) {
            addCriterion("pay_supplier in", values, "paySupplier");
            return (Criteria) this;
        }
        public Criteria andPaySupplierNotIn(List<Boolean> values) {
            addCriterion("pay_supplier not in", values, "paySupplier");
            return (Criteria) this;
        }

        //between
        public Criteria andPaySupplierBetween(Boolean value1, Boolean value2) {
            addCriterion("pay_supplier between", value1, value2, "paySupplier");
            return (Criteria) this;
        }

        //like
        public Criteria andPaySupplierLike(String value) {
            addCriterion("pay_supplier like", value, "paySupplier");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPaySupplierEqualTo(Boolean value) {
            addCriterion("pay_supplier =", value, "paySupplier");
            return (Criteria) this;
        }
        public Criteria andPaySupplierNotEqualTo(Boolean value) {
            addCriterion("pay_supplier <>", value, "paySupplier");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPaySupplierGreaterThan(Boolean value) {
            addCriterion("pay_supplier >", value, "paySupplier");
            return (Criteria) this;
        }
        public Criteria andPaySupplierGreaterThanOrEqualTo(Boolean value) {
            addCriterion("pay_supplier >=", value, "paySupplier");
            return (Criteria) this;
        }
        public Criteria andPaySupplierLessThan(Boolean value) {
            addCriterion("pay_supplier <", value, "paySupplier");
            return (Criteria) this;
        }
        public Criteria andPaySupplierLessThanOrEqualTo(Boolean value) {
            addCriterion("pay_supplier <=", value, "paySupplier");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPaySupplierIsNull() {
            addCriterion("pay_supplier is null");
            return (Criteria) this;
        }

        public Criteria andPaySupplierIsNotNull() {
            addCriterion("pay_supplier is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayRecommendIn(List<Boolean> values) {
            addCriterion("pay_recommend in", values, "payRecommend");
            return (Criteria) this;
        }
        public Criteria andPayRecommendNotIn(List<Boolean> values) {
            addCriterion("pay_recommend not in", values, "payRecommend");
            return (Criteria) this;
        }

        //between
        public Criteria andPayRecommendBetween(Boolean value1, Boolean value2) {
            addCriterion("pay_recommend between", value1, value2, "payRecommend");
            return (Criteria) this;
        }

        //like
        public Criteria andPayRecommendLike(String value) {
            addCriterion("pay_recommend like", value, "payRecommend");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayRecommendEqualTo(Boolean value) {
            addCriterion("pay_recommend =", value, "payRecommend");
            return (Criteria) this;
        }
        public Criteria andPayRecommendNotEqualTo(Boolean value) {
            addCriterion("pay_recommend <>", value, "payRecommend");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayRecommendGreaterThan(Boolean value) {
            addCriterion("pay_recommend >", value, "payRecommend");
            return (Criteria) this;
        }
        public Criteria andPayRecommendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("pay_recommend >=", value, "payRecommend");
            return (Criteria) this;
        }
        public Criteria andPayRecommendLessThan(Boolean value) {
            addCriterion("pay_recommend <", value, "payRecommend");
            return (Criteria) this;
        }
        public Criteria andPayRecommendLessThanOrEqualTo(Boolean value) {
            addCriterion("pay_recommend <=", value, "payRecommend");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayRecommendIsNull() {
            addCriterion("pay_recommend is null");
            return (Criteria) this;
        }

        public Criteria andPayRecommendIsNotNull() {
            addCriterion("pay_recommend is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andRecommendRateIn(List<Integer> values) {
            addCriterion("recommend_rate in", values, "recommendRate");
            return (Criteria) this;
        }
        public Criteria andRecommendRateNotIn(List<Integer> values) {
            addCriterion("recommend_rate not in", values, "recommendRate");
            return (Criteria) this;
        }

        //between
        public Criteria andRecommendRateBetween(Integer value1, Integer value2) {
            addCriterion("recommend_rate between", value1, value2, "recommendRate");
            return (Criteria) this;
        }

        //like
        public Criteria andRecommendRateLike(String value) {
            addCriterion("recommend_rate like", value, "recommendRate");
            return (Criteria) this;
        }

        // = <>
        public Criteria andRecommendRateEqualTo(Integer value) {
            addCriterion("recommend_rate =", value, "recommendRate");
            return (Criteria) this;
        }
        public Criteria andRecommendRateNotEqualTo(Integer value) {
            addCriterion("recommend_rate <>", value, "recommendRate");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andRecommendRateGreaterThan(Integer value) {
            addCriterion("recommend_rate >", value, "recommendRate");
            return (Criteria) this;
        }
        public Criteria andRecommendRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend_rate >=", value, "recommendRate");
            return (Criteria) this;
        }
        public Criteria andRecommendRateLessThan(Integer value) {
            addCriterion("recommend_rate <", value, "recommendRate");
            return (Criteria) this;
        }
        public Criteria andRecommendRateLessThanOrEqualTo(Integer value) {
            addCriterion("recommend_rate <=", value, "recommendRate");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andRecommendRateIsNull() {
            addCriterion("recommend_rate is null");
            return (Criteria) this;
        }

        public Criteria andRecommendRateIsNotNull() {
            addCriterion("recommend_rate is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andRecommendCustomeridIn(List<Long> values) {
            addCriterion("recommend_customerId in", values, "recommendCustomerid");
            return (Criteria) this;
        }
        public Criteria andRecommendCustomeridNotIn(List<Long> values) {
            addCriterion("recommend_customerId not in", values, "recommendCustomerid");
            return (Criteria) this;
        }

        //between
        public Criteria andRecommendCustomeridBetween(Long value1, Long value2) {
            addCriterion("recommend_customerId between", value1, value2, "recommendCustomerid");
            return (Criteria) this;
        }

        //like
        public Criteria andRecommendCustomeridLike(String value) {
            addCriterion("recommend_customerId like", value, "recommendCustomerid");
            return (Criteria) this;
        }

        // = <>
        public Criteria andRecommendCustomeridEqualTo(Long value) {
            addCriterion("recommend_customerId =", value, "recommendCustomerid");
            return (Criteria) this;
        }
        public Criteria andRecommendCustomeridNotEqualTo(Long value) {
            addCriterion("recommend_customerId <>", value, "recommendCustomerid");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andRecommendCustomeridGreaterThan(Long value) {
            addCriterion("recommend_customerId >", value, "recommendCustomerid");
            return (Criteria) this;
        }
        public Criteria andRecommendCustomeridGreaterThanOrEqualTo(Long value) {
            addCriterion("recommend_customerId >=", value, "recommendCustomerid");
            return (Criteria) this;
        }
        public Criteria andRecommendCustomeridLessThan(Long value) {
            addCriterion("recommend_customerId <", value, "recommendCustomerid");
            return (Criteria) this;
        }
        public Criteria andRecommendCustomeridLessThanOrEqualTo(Long value) {
            addCriterion("recommend_customerId <=", value, "recommendCustomerid");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andRecommendCustomeridIsNull() {
            addCriterion("recommend_customerId is null");
            return (Criteria) this;
        }

        public Criteria andRecommendCustomeridIsNotNull() {
            addCriterion("recommend_customerId is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPayManagerIn(List<Boolean> values) {
            addCriterion("pay_manager in", values, "payManager");
            return (Criteria) this;
        }
        public Criteria andPayManagerNotIn(List<Boolean> values) {
            addCriterion("pay_manager not in", values, "payManager");
            return (Criteria) this;
        }

        //between
        public Criteria andPayManagerBetween(Boolean value1, Boolean value2) {
            addCriterion("pay_manager between", value1, value2, "payManager");
            return (Criteria) this;
        }

        //like
        public Criteria andPayManagerLike(String value) {
            addCriterion("pay_manager like", value, "payManager");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPayManagerEqualTo(Boolean value) {
            addCriterion("pay_manager =", value, "payManager");
            return (Criteria) this;
        }
        public Criteria andPayManagerNotEqualTo(Boolean value) {
            addCriterion("pay_manager <>", value, "payManager");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPayManagerGreaterThan(Boolean value) {
            addCriterion("pay_manager >", value, "payManager");
            return (Criteria) this;
        }
        public Criteria andPayManagerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("pay_manager >=", value, "payManager");
            return (Criteria) this;
        }
        public Criteria andPayManagerLessThan(Boolean value) {
            addCriterion("pay_manager <", value, "payManager");
            return (Criteria) this;
        }
        public Criteria andPayManagerLessThanOrEqualTo(Boolean value) {
            addCriterion("pay_manager <=", value, "payManager");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPayManagerIsNull() {
            addCriterion("pay_manager is null");
            return (Criteria) this;
        }

        public Criteria andPayManagerIsNotNull() {
            addCriterion("pay_manager is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPaySalerIn(List<Boolean> values) {
            addCriterion("pay_saler in", values, "paySaler");
            return (Criteria) this;
        }
        public Criteria andPaySalerNotIn(List<Boolean> values) {
            addCriterion("pay_saler not in", values, "paySaler");
            return (Criteria) this;
        }

        //between
        public Criteria andPaySalerBetween(Boolean value1, Boolean value2) {
            addCriterion("pay_saler between", value1, value2, "paySaler");
            return (Criteria) this;
        }

        //like
        public Criteria andPaySalerLike(String value) {
            addCriterion("pay_saler like", value, "paySaler");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPaySalerEqualTo(Boolean value) {
            addCriterion("pay_saler =", value, "paySaler");
            return (Criteria) this;
        }
        public Criteria andPaySalerNotEqualTo(Boolean value) {
            addCriterion("pay_saler <>", value, "paySaler");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPaySalerGreaterThan(Boolean value) {
            addCriterion("pay_saler >", value, "paySaler");
            return (Criteria) this;
        }
        public Criteria andPaySalerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("pay_saler >=", value, "paySaler");
            return (Criteria) this;
        }
        public Criteria andPaySalerLessThan(Boolean value) {
            addCriterion("pay_saler <", value, "paySaler");
            return (Criteria) this;
        }
        public Criteria andPaySalerLessThanOrEqualTo(Boolean value) {
            addCriterion("pay_saler <=", value, "paySaler");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPaySalerIsNull() {
            addCriterion("pay_saler is null");
            return (Criteria) this;
        }

        public Criteria andPaySalerIsNotNull() {
            addCriterion("pay_saler is not null");
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
        public Criteria andValidityIn(List<Boolean> values) {
            addCriterion("validity in", values, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityNotIn(List<Boolean> values) {
            addCriterion("validity not in", values, "validity");
            return (Criteria) this;
        }

        //between
        public Criteria andValidityBetween(Boolean value1, Boolean value2) {
            addCriterion("validity between", value1, value2, "validity");
            return (Criteria) this;
        }

        //like
        public Criteria andValidityLike(String value) {
            addCriterion("validity like", value, "validity");
            return (Criteria) this;
        }

        // = <>
        public Criteria andValidityEqualTo(Boolean value) {
            addCriterion("validity =", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityNotEqualTo(Boolean value) {
            addCriterion("validity <>", value, "validity");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andValidityGreaterThan(Boolean value) {
            addCriterion("validity >", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityGreaterThanOrEqualTo(Boolean value) {
            addCriterion("validity >=", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityLessThan(Boolean value) {
            addCriterion("validity <", value, "validity");
            return (Criteria) this;
        }
        public Criteria andValidityLessThanOrEqualTo(Boolean value) {
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
        public Criteria andRateIn(List<Integer> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }
        public Criteria andRateNotIn(List<Integer> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        //between
        public Criteria andRateBetween(Integer value1, Integer value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        //like
        public Criteria andRateLike(String value) {
            addCriterion("rate like", value, "rate");
            return (Criteria) this;
        }

        // = <>
        public Criteria andRateEqualTo(Integer value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }
        public Criteria andRateNotEqualTo(Integer value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andRateGreaterThan(Integer value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }
        public Criteria andRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }
        public Criteria andRateLessThan(Integer value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }
        public Criteria andRateLessThanOrEqualTo(Integer value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSameCityIn(List<Boolean> values) {
            addCriterion("same_city in", values, "sameCity");
            return (Criteria) this;
        }
        public Criteria andSameCityNotIn(List<Boolean> values) {
            addCriterion("same_city not in", values, "sameCity");
            return (Criteria) this;
        }

        //between
        public Criteria andSameCityBetween(Boolean value1, Boolean value2) {
            addCriterion("same_city between", value1, value2, "sameCity");
            return (Criteria) this;
        }

        //like
        public Criteria andSameCityLike(String value) {
            addCriterion("same_city like", value, "sameCity");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSameCityEqualTo(Boolean value) {
            addCriterion("same_city =", value, "sameCity");
            return (Criteria) this;
        }
        public Criteria andSameCityNotEqualTo(Boolean value) {
            addCriterion("same_city <>", value, "sameCity");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSameCityGreaterThan(Boolean value) {
            addCriterion("same_city >", value, "sameCity");
            return (Criteria) this;
        }
        public Criteria andSameCityGreaterThanOrEqualTo(Boolean value) {
            addCriterion("same_city >=", value, "sameCity");
            return (Criteria) this;
        }
        public Criteria andSameCityLessThan(Boolean value) {
            addCriterion("same_city <", value, "sameCity");
            return (Criteria) this;
        }
        public Criteria andSameCityLessThanOrEqualTo(Boolean value) {
            addCriterion("same_city <=", value, "sameCity");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSameCityIsNull() {
            addCriterion("same_city is null");
            return (Criteria) this;
        }

        public Criteria andSameCityIsNotNull() {
            addCriterion("same_city is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andRangeLimitIn(List<Integer> values) {
            addCriterion("range_limit in", values, "rangeLimit");
            return (Criteria) this;
        }
        public Criteria andRangeLimitNotIn(List<Integer> values) {
            addCriterion("range_limit not in", values, "rangeLimit");
            return (Criteria) this;
        }

        //between
        public Criteria andRangeLimitBetween(Integer value1, Integer value2) {
            addCriterion("range_limit between", value1, value2, "rangeLimit");
            return (Criteria) this;
        }

        //like
        public Criteria andRangeLimitLike(String value) {
            addCriterion("range_limit like", value, "rangeLimit");
            return (Criteria) this;
        }

        // = <>
        public Criteria andRangeLimitEqualTo(Integer value) {
            addCriterion("range_limit =", value, "rangeLimit");
            return (Criteria) this;
        }
        public Criteria andRangeLimitNotEqualTo(Integer value) {
            addCriterion("range_limit <>", value, "rangeLimit");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andRangeLimitGreaterThan(Integer value) {
            addCriterion("range_limit >", value, "rangeLimit");
            return (Criteria) this;
        }
        public Criteria andRangeLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("range_limit >=", value, "rangeLimit");
            return (Criteria) this;
        }
        public Criteria andRangeLimitLessThan(Integer value) {
            addCriterion("range_limit <", value, "rangeLimit");
            return (Criteria) this;
        }
        public Criteria andRangeLimitLessThanOrEqualTo(Integer value) {
            addCriterion("range_limit <=", value, "rangeLimit");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andRangeLimitIsNull() {
            addCriterion("range_limit is null");
            return (Criteria) this;
        }

        public Criteria andRangeLimitIsNotNull() {
            addCriterion("range_limit is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTransferDateIn(List<Integer> values) {
            addCriterion("transfer_date in", values, "transferDate");
            return (Criteria) this;
        }
        public Criteria andTransferDateNotIn(List<Integer> values) {
            addCriterion("transfer_date not in", values, "transferDate");
            return (Criteria) this;
        }

        //between
        public Criteria andTransferDateBetween(Integer value1, Integer value2) {
            addCriterion("transfer_date between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        //like
        public Criteria andTransferDateLike(String value) {
            addCriterion("transfer_date like", value, "transferDate");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTransferDateEqualTo(Integer value) {
            addCriterion("transfer_date =", value, "transferDate");
            return (Criteria) this;
        }
        public Criteria andTransferDateNotEqualTo(Integer value) {
            addCriterion("transfer_date <>", value, "transferDate");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTransferDateGreaterThan(Integer value) {
            addCriterion("transfer_date >", value, "transferDate");
            return (Criteria) this;
        }
        public Criteria andTransferDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_date >=", value, "transferDate");
            return (Criteria) this;
        }
        public Criteria andTransferDateLessThan(Integer value) {
            addCriterion("transfer_date <", value, "transferDate");
            return (Criteria) this;
        }
        public Criteria andTransferDateLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_date <=", value, "transferDate");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTransferDateIsNull() {
            addCriterion("transfer_date is null");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNotNull() {
            addCriterion("transfer_date is not null");
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