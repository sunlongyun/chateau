package com.caichao.chateau.app.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
/**
* <p>
* 国家--酒庄
* </p>
*
* @author 孙龙云
* @date 2019-07-09
*/
@Data
public class CountryChateauExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CountryChateauExample(){oredCriteria = new ArrayList<>();}

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
        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }
        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        //between
        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        //like
        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }
        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }
        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }
        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }
        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }
        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        //between
        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        //like
        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }
        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }
        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }
        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }
        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }
        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        //between
        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        //like
        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }
        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }
        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }
        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }
        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMinPicUrlIn(List<String> values) {
            addCriterion("min_pic_url in", values, "minPicUrl");
            return (Criteria) this;
        }
        public Criteria andMinPicUrlNotIn(List<String> values) {
            addCriterion("min_pic_url not in", values, "minPicUrl");
            return (Criteria) this;
        }

        //between
        public Criteria andMinPicUrlBetween(String value1, String value2) {
            addCriterion("min_pic_url between", value1, value2, "minPicUrl");
            return (Criteria) this;
        }

        //like
        public Criteria andMinPicUrlLike(String value) {
            addCriterion("min_pic_url like", value, "minPicUrl");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMinPicUrlEqualTo(String value) {
            addCriterion("min_pic_url =", value, "minPicUrl");
            return (Criteria) this;
        }
        public Criteria andMinPicUrlNotEqualTo(String value) {
            addCriterion("min_pic_url <>", value, "minPicUrl");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMinPicUrlGreaterThan(String value) {
            addCriterion("min_pic_url >", value, "minPicUrl");
            return (Criteria) this;
        }
        public Criteria andMinPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("min_pic_url >=", value, "minPicUrl");
            return (Criteria) this;
        }
        public Criteria andMinPicUrlLessThan(String value) {
            addCriterion("min_pic_url <", value, "minPicUrl");
            return (Criteria) this;
        }
        public Criteria andMinPicUrlLessThanOrEqualTo(String value) {
            addCriterion("min_pic_url <=", value, "minPicUrl");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMinPicUrlIsNull() {
            addCriterion("min_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andMinPicUrlIsNotNull() {
            addCriterion("min_pic_url is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andVideoIn(List<String> values) {
            addCriterion("video in", values, "video");
            return (Criteria) this;
        }
        public Criteria andVideoNotIn(List<String> values) {
            addCriterion("video not in", values, "video");
            return (Criteria) this;
        }

        //between
        public Criteria andVideoBetween(String value1, String value2) {
            addCriterion("video between", value1, value2, "video");
            return (Criteria) this;
        }

        //like
        public Criteria andVideoLike(String value) {
            addCriterion("video like", value, "video");
            return (Criteria) this;
        }

        // = <>
        public Criteria andVideoEqualTo(String value) {
            addCriterion("video =", value, "video");
            return (Criteria) this;
        }
        public Criteria andVideoNotEqualTo(String value) {
            addCriterion("video <>", value, "video");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andVideoGreaterThan(String value) {
            addCriterion("video >", value, "video");
            return (Criteria) this;
        }
        public Criteria andVideoGreaterThanOrEqualTo(String value) {
            addCriterion("video >=", value, "video");
            return (Criteria) this;
        }
        public Criteria andVideoLessThan(String value) {
            addCriterion("video <", value, "video");
            return (Criteria) this;
        }
        public Criteria andVideoLessThanOrEqualTo(String value) {
            addCriterion("video <=", value, "video");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andVideoIsNull() {
            addCriterion("video is null");
            return (Criteria) this;
        }

        public Criteria andVideoIsNotNull() {
            addCriterion("video is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andDailyBroadcastIngIn(List<Integer> values) {
            addCriterion("daily_broadcast_ing in", values, "dailyBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastIngNotIn(List<Integer> values) {
            addCriterion("daily_broadcast_ing not in", values, "dailyBroadcastIng");
            return (Criteria) this;
        }

        //between
        public Criteria andDailyBroadcastIngBetween(Integer value1, Integer value2) {
            addCriterion("daily_broadcast_ing between", value1, value2, "dailyBroadcastIng");
            return (Criteria) this;
        }

        //like
        public Criteria andDailyBroadcastIngLike(String value) {
            addCriterion("daily_broadcast_ing like", value, "dailyBroadcastIng");
            return (Criteria) this;
        }

        // = <>
        public Criteria andDailyBroadcastIngEqualTo(Integer value) {
            addCriterion("daily_broadcast_ing =", value, "dailyBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastIngNotEqualTo(Integer value) {
            addCriterion("daily_broadcast_ing <>", value, "dailyBroadcastIng");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andDailyBroadcastIngGreaterThan(Integer value) {
            addCriterion("daily_broadcast_ing >", value, "dailyBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastIngGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily_broadcast_ing >=", value, "dailyBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastIngLessThan(Integer value) {
            addCriterion("daily_broadcast_ing <", value, "dailyBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastIngLessThanOrEqualTo(Integer value) {
            addCriterion("daily_broadcast_ing <=", value, "dailyBroadcastIng");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andDailyBroadcastIngIsNull() {
            addCriterion("daily_broadcast_ing is null");
            return (Criteria) this;
        }

        public Criteria andDailyBroadcastIngIsNotNull() {
            addCriterion("daily_broadcast_ing is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMasterBroadcastIngIn(List<Integer> values) {
            addCriterion("master_broadcast_ing in", values, "masterBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastIngNotIn(List<Integer> values) {
            addCriterion("master_broadcast_ing not in", values, "masterBroadcastIng");
            return (Criteria) this;
        }

        //between
        public Criteria andMasterBroadcastIngBetween(Integer value1, Integer value2) {
            addCriterion("master_broadcast_ing between", value1, value2, "masterBroadcastIng");
            return (Criteria) this;
        }

        //like
        public Criteria andMasterBroadcastIngLike(String value) {
            addCriterion("master_broadcast_ing like", value, "masterBroadcastIng");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMasterBroadcastIngEqualTo(Integer value) {
            addCriterion("master_broadcast_ing =", value, "masterBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastIngNotEqualTo(Integer value) {
            addCriterion("master_broadcast_ing <>", value, "masterBroadcastIng");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMasterBroadcastIngGreaterThan(Integer value) {
            addCriterion("master_broadcast_ing >", value, "masterBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastIngGreaterThanOrEqualTo(Integer value) {
            addCriterion("master_broadcast_ing >=", value, "masterBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastIngLessThan(Integer value) {
            addCriterion("master_broadcast_ing <", value, "masterBroadcastIng");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastIngLessThanOrEqualTo(Integer value) {
            addCriterion("master_broadcast_ing <=", value, "masterBroadcastIng");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMasterBroadcastIngIsNull() {
            addCriterion("master_broadcast_ing is null");
            return (Criteria) this;
        }

        public Criteria andMasterBroadcastIngIsNotNull() {
            addCriterion("master_broadcast_ing is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andDailyBroadcastIn(List<String> values) {
            addCriterion("daily_broadcast in", values, "dailyBroadcast");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastNotIn(List<String> values) {
            addCriterion("daily_broadcast not in", values, "dailyBroadcast");
            return (Criteria) this;
        }

        //between
        public Criteria andDailyBroadcastBetween(String value1, String value2) {
            addCriterion("daily_broadcast between", value1, value2, "dailyBroadcast");
            return (Criteria) this;
        }

        //like
        public Criteria andDailyBroadcastLike(String value) {
            addCriterion("daily_broadcast like", value, "dailyBroadcast");
            return (Criteria) this;
        }

        // = <>
        public Criteria andDailyBroadcastEqualTo(String value) {
            addCriterion("daily_broadcast =", value, "dailyBroadcast");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastNotEqualTo(String value) {
            addCriterion("daily_broadcast <>", value, "dailyBroadcast");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andDailyBroadcastGreaterThan(String value) {
            addCriterion("daily_broadcast >", value, "dailyBroadcast");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastGreaterThanOrEqualTo(String value) {
            addCriterion("daily_broadcast >=", value, "dailyBroadcast");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastLessThan(String value) {
            addCriterion("daily_broadcast <", value, "dailyBroadcast");
            return (Criteria) this;
        }
        public Criteria andDailyBroadcastLessThanOrEqualTo(String value) {
            addCriterion("daily_broadcast <=", value, "dailyBroadcast");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andDailyBroadcastIsNull() {
            addCriterion("daily_broadcast is null");
            return (Criteria) this;
        }

        public Criteria andDailyBroadcastIsNotNull() {
            addCriterion("daily_broadcast is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andMasterBroadcastIn(List<String> values) {
            addCriterion("master_broadcast in", values, "masterBroadcast");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastNotIn(List<String> values) {
            addCriterion("master_broadcast not in", values, "masterBroadcast");
            return (Criteria) this;
        }

        //between
        public Criteria andMasterBroadcastBetween(String value1, String value2) {
            addCriterion("master_broadcast between", value1, value2, "masterBroadcast");
            return (Criteria) this;
        }

        //like
        public Criteria andMasterBroadcastLike(String value) {
            addCriterion("master_broadcast like", value, "masterBroadcast");
            return (Criteria) this;
        }

        // = <>
        public Criteria andMasterBroadcastEqualTo(String value) {
            addCriterion("master_broadcast =", value, "masterBroadcast");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastNotEqualTo(String value) {
            addCriterion("master_broadcast <>", value, "masterBroadcast");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andMasterBroadcastGreaterThan(String value) {
            addCriterion("master_broadcast >", value, "masterBroadcast");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastGreaterThanOrEqualTo(String value) {
            addCriterion("master_broadcast >=", value, "masterBroadcast");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastLessThan(String value) {
            addCriterion("master_broadcast <", value, "masterBroadcast");
            return (Criteria) this;
        }
        public Criteria andMasterBroadcastLessThanOrEqualTo(String value) {
            addCriterion("master_broadcast <=", value, "masterBroadcast");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andMasterBroadcastIsNull() {
            addCriterion("master_broadcast is null");
            return (Criteria) this;
        }

        public Criteria andMasterBroadcastIsNotNull() {
            addCriterion("master_broadcast is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andCountryIdIn(List<Integer> values) {
            addCriterion("country_id in", values, "countryId");
            return (Criteria) this;
        }
        public Criteria andCountryIdNotIn(List<Integer> values) {
            addCriterion("country_id not in", values, "countryId");
            return (Criteria) this;
        }

        //between
        public Criteria andCountryIdBetween(Integer value1, Integer value2) {
            addCriterion("country_id between", value1, value2, "countryId");
            return (Criteria) this;
        }

        //like
        public Criteria andCountryIdLike(String value) {
            addCriterion("country_id like", value, "countryId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andCountryIdEqualTo(Integer value) {
            addCriterion("country_id =", value, "countryId");
            return (Criteria) this;
        }
        public Criteria andCountryIdNotEqualTo(Integer value) {
            addCriterion("country_id <>", value, "countryId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andCountryIdGreaterThan(Integer value) {
            addCriterion("country_id >", value, "countryId");
            return (Criteria) this;
        }
        public Criteria andCountryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("country_id >=", value, "countryId");
            return (Criteria) this;
        }
        public Criteria andCountryIdLessThan(Integer value) {
            addCriterion("country_id <", value, "countryId");
            return (Criteria) this;
        }
        public Criteria andCountryIdLessThanOrEqualTo(Integer value) {
            addCriterion("country_id <=", value, "countryId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andCountryIdIsNull() {
            addCriterion("country_id is null");
            return (Criteria) this;
        }

        public Criteria andCountryIdIsNotNull() {
            addCriterion("country_id is not null");
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
        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }
        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        //between
        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        //like
        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        // = <>
        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }
        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }
        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }
        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }
        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
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