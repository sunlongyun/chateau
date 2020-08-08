package com.chisong.green.farm.app.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
/**
* <p>
* 酒水
* </p>
*
* @author 孙龙云
* @date 2020-05-02
*/
@Data
public class GoodsExample implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample(){oredCriteria = new ArrayList<>();}

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
        public Criteria andSkuCodeIn(List<String> values) {
            addCriterion("sku_code in", values, "skuCode");
            return (Criteria) this;
        }
        public Criteria andSkuCodeNotIn(List<String> values) {
            addCriterion("sku_code not in", values, "skuCode");
            return (Criteria) this;
        }

        //between
        public Criteria andSkuCodeBetween(String value1, String value2) {
            addCriterion("sku_code between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        //like
        public Criteria andSkuCodeLike(String value) {
            addCriterion("sku_code like", value, "skuCode");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSkuCodeEqualTo(String value) {
            addCriterion("sku_code =", value, "skuCode");
            return (Criteria) this;
        }
        public Criteria andSkuCodeNotEqualTo(String value) {
            addCriterion("sku_code <>", value, "skuCode");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSkuCodeGreaterThan(String value) {
            addCriterion("sku_code >", value, "skuCode");
            return (Criteria) this;
        }
        public Criteria andSkuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_code >=", value, "skuCode");
            return (Criteria) this;
        }
        public Criteria andSkuCodeLessThan(String value) {
            addCriterion("sku_code <", value, "skuCode");
            return (Criteria) this;
        }
        public Criteria andSkuCodeLessThanOrEqualTo(String value) {
            addCriterion("sku_code <=", value, "skuCode");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSkuCodeIsNull() {
            addCriterion("sku_code is null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIsNotNull() {
            addCriterion("sku_code is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andFirstTypeIdIn(List<Integer> values) {
            addCriterion("first_type_id in", values, "firstTypeId");
            return (Criteria) this;
        }
        public Criteria andFirstTypeIdNotIn(List<Integer> values) {
            addCriterion("first_type_id not in", values, "firstTypeId");
            return (Criteria) this;
        }

        //between
        public Criteria andFirstTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("first_type_id between", value1, value2, "firstTypeId");
            return (Criteria) this;
        }

        //like
        public Criteria andFirstTypeIdLike(String value) {
            addCriterion("first_type_id like", value, "firstTypeId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andFirstTypeIdEqualTo(Integer value) {
            addCriterion("first_type_id =", value, "firstTypeId");
            return (Criteria) this;
        }
        public Criteria andFirstTypeIdNotEqualTo(Integer value) {
            addCriterion("first_type_id <>", value, "firstTypeId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andFirstTypeIdGreaterThan(Integer value) {
            addCriterion("first_type_id >", value, "firstTypeId");
            return (Criteria) this;
        }
        public Criteria andFirstTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_type_id >=", value, "firstTypeId");
            return (Criteria) this;
        }
        public Criteria andFirstTypeIdLessThan(Integer value) {
            addCriterion("first_type_id <", value, "firstTypeId");
            return (Criteria) this;
        }
        public Criteria andFirstTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("first_type_id <=", value, "firstTypeId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andFirstTypeIdIsNull() {
            addCriterion("first_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFirstTypeIdIsNotNull() {
            addCriterion("first_type_id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }
        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        //between
        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        //like
        public Criteria andTypeIdLike(String value) {
            addCriterion("type_id like", value, "typeId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }
        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }
        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }
        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }
        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }
        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        //between
        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        //like
        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        // = <>
        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }
        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }
        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }
        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }
        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSupplierCompanyNameIn(List<String> values) {
            addCriterion("supplier_company_name in", values, "supplierCompanyName");
            return (Criteria) this;
        }
        public Criteria andSupplierCompanyNameNotIn(List<String> values) {
            addCriterion("supplier_company_name not in", values, "supplierCompanyName");
            return (Criteria) this;
        }

        //between
        public Criteria andSupplierCompanyNameBetween(String value1, String value2) {
            addCriterion("supplier_company_name between", value1, value2, "supplierCompanyName");
            return (Criteria) this;
        }

        //like
        public Criteria andSupplierCompanyNameLike(String value) {
            addCriterion("supplier_company_name like", value, "supplierCompanyName");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSupplierCompanyNameEqualTo(String value) {
            addCriterion("supplier_company_name =", value, "supplierCompanyName");
            return (Criteria) this;
        }
        public Criteria andSupplierCompanyNameNotEqualTo(String value) {
            addCriterion("supplier_company_name <>", value, "supplierCompanyName");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSupplierCompanyNameGreaterThan(String value) {
            addCriterion("supplier_company_name >", value, "supplierCompanyName");
            return (Criteria) this;
        }
        public Criteria andSupplierCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_company_name >=", value, "supplierCompanyName");
            return (Criteria) this;
        }
        public Criteria andSupplierCompanyNameLessThan(String value) {
            addCriterion("supplier_company_name <", value, "supplierCompanyName");
            return (Criteria) this;
        }
        public Criteria andSupplierCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("supplier_company_name <=", value, "supplierCompanyName");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSupplierCompanyNameIsNull() {
            addCriterion("supplier_company_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplierCompanyNameIsNotNull() {
            addCriterion("supplier_company_name is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andProduceAreaIn(List<String> values) {
            addCriterion("produce_area in", values, "produceArea");
            return (Criteria) this;
        }
        public Criteria andProduceAreaNotIn(List<String> values) {
            addCriterion("produce_area not in", values, "produceArea");
            return (Criteria) this;
        }

        //between
        public Criteria andProduceAreaBetween(String value1, String value2) {
            addCriterion("produce_area between", value1, value2, "produceArea");
            return (Criteria) this;
        }

        //like
        public Criteria andProduceAreaLike(String value) {
            addCriterion("produce_area like", value, "produceArea");
            return (Criteria) this;
        }

        // = <>
        public Criteria andProduceAreaEqualTo(String value) {
            addCriterion("produce_area =", value, "produceArea");
            return (Criteria) this;
        }
        public Criteria andProduceAreaNotEqualTo(String value) {
            addCriterion("produce_area <>", value, "produceArea");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andProduceAreaGreaterThan(String value) {
            addCriterion("produce_area >", value, "produceArea");
            return (Criteria) this;
        }
        public Criteria andProduceAreaGreaterThanOrEqualTo(String value) {
            addCriterion("produce_area >=", value, "produceArea");
            return (Criteria) this;
        }
        public Criteria andProduceAreaLessThan(String value) {
            addCriterion("produce_area <", value, "produceArea");
            return (Criteria) this;
        }
        public Criteria andProduceAreaLessThanOrEqualTo(String value) {
            addCriterion("produce_area <=", value, "produceArea");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andProduceAreaIsNull() {
            addCriterion("produce_area is null");
            return (Criteria) this;
        }

        public Criteria andProduceAreaIsNotNull() {
            addCriterion("produce_area is not null");
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
        public Criteria andSupplierIdIn(List<Integer> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }
        public Criteria andSupplierIdNotIn(List<Integer> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        //between
        public Criteria andSupplierIdBetween(Integer value1, Integer value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        //like
        public Criteria andSupplierIdLike(String value) {
            addCriterion("supplier_id like", value, "supplierId");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSupplierIdEqualTo(Integer value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }
        public Criteria andSupplierIdNotEqualTo(Integer value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSupplierIdGreaterThan(Integer value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }
        public Criteria andSupplierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }
        public Criteria andSupplierIdLessThan(Integer value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }
        public Criteria andSupplierIdLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
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
        public Criteria andDetailPicUrlIn(List<String> values) {
            addCriterion("detail_pic_url in", values, "detailPicUrl");
            return (Criteria) this;
        }
        public Criteria andDetailPicUrlNotIn(List<String> values) {
            addCriterion("detail_pic_url not in", values, "detailPicUrl");
            return (Criteria) this;
        }

        //between
        public Criteria andDetailPicUrlBetween(String value1, String value2) {
            addCriterion("detail_pic_url between", value1, value2, "detailPicUrl");
            return (Criteria) this;
        }

        //like
        public Criteria andDetailPicUrlLike(String value) {
            addCriterion("detail_pic_url like", value, "detailPicUrl");
            return (Criteria) this;
        }

        // = <>
        public Criteria andDetailPicUrlEqualTo(String value) {
            addCriterion("detail_pic_url =", value, "detailPicUrl");
            return (Criteria) this;
        }
        public Criteria andDetailPicUrlNotEqualTo(String value) {
            addCriterion("detail_pic_url <>", value, "detailPicUrl");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andDetailPicUrlGreaterThan(String value) {
            addCriterion("detail_pic_url >", value, "detailPicUrl");
            return (Criteria) this;
        }
        public Criteria andDetailPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("detail_pic_url >=", value, "detailPicUrl");
            return (Criteria) this;
        }
        public Criteria andDetailPicUrlLessThan(String value) {
            addCriterion("detail_pic_url <", value, "detailPicUrl");
            return (Criteria) this;
        }
        public Criteria andDetailPicUrlLessThanOrEqualTo(String value) {
            addCriterion("detail_pic_url <=", value, "detailPicUrl");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andDetailPicUrlIsNull() {
            addCriterion("detail_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andDetailPicUrlIsNotNull() {
            addCriterion("detail_pic_url is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPromoteIn(List<Integer> values) {
            addCriterion("promote in", values, "promote");
            return (Criteria) this;
        }
        public Criteria andPromoteNotIn(List<Integer> values) {
            addCriterion("promote not in", values, "promote");
            return (Criteria) this;
        }

        //between
        public Criteria andPromoteBetween(Integer value1, Integer value2) {
            addCriterion("promote between", value1, value2, "promote");
            return (Criteria) this;
        }

        //like
        public Criteria andPromoteLike(String value) {
            addCriterion("promote like", value, "promote");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPromoteEqualTo(Integer value) {
            addCriterion("promote =", value, "promote");
            return (Criteria) this;
        }
        public Criteria andPromoteNotEqualTo(Integer value) {
            addCriterion("promote <>", value, "promote");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPromoteGreaterThan(Integer value) {
            addCriterion("promote >", value, "promote");
            return (Criteria) this;
        }
        public Criteria andPromoteGreaterThanOrEqualTo(Integer value) {
            addCriterion("promote >=", value, "promote");
            return (Criteria) this;
        }
        public Criteria andPromoteLessThan(Integer value) {
            addCriterion("promote <", value, "promote");
            return (Criteria) this;
        }
        public Criteria andPromoteLessThanOrEqualTo(Integer value) {
            addCriterion("promote <=", value, "promote");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPromoteIsNull() {
            addCriterion("promote is null");
            return (Criteria) this;
        }

        public Criteria andPromoteIsNotNull() {
            addCriterion("promote is not null");
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
        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }
        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        //between
        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        //like
        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }
        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }
        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }
        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }
        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSalesAmountIn(List<Long> values) {
            addCriterion("sales_amount in", values, "salesAmount");
            return (Criteria) this;
        }
        public Criteria andSalesAmountNotIn(List<Long> values) {
            addCriterion("sales_amount not in", values, "salesAmount");
            return (Criteria) this;
        }

        //between
        public Criteria andSalesAmountBetween(Long value1, Long value2) {
            addCriterion("sales_amount between", value1, value2, "salesAmount");
            return (Criteria) this;
        }

        //like
        public Criteria andSalesAmountLike(String value) {
            addCriterion("sales_amount like", value, "salesAmount");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSalesAmountEqualTo(Long value) {
            addCriterion("sales_amount =", value, "salesAmount");
            return (Criteria) this;
        }
        public Criteria andSalesAmountNotEqualTo(Long value) {
            addCriterion("sales_amount <>", value, "salesAmount");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSalesAmountGreaterThan(Long value) {
            addCriterion("sales_amount >", value, "salesAmount");
            return (Criteria) this;
        }
        public Criteria andSalesAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("sales_amount >=", value, "salesAmount");
            return (Criteria) this;
        }
        public Criteria andSalesAmountLessThan(Long value) {
            addCriterion("sales_amount <", value, "salesAmount");
            return (Criteria) this;
        }
        public Criteria andSalesAmountLessThanOrEqualTo(Long value) {
            addCriterion("sales_amount <=", value, "salesAmount");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSalesAmountIsNull() {
            addCriterion("sales_amount is null");
            return (Criteria) this;
        }

        public Criteria andSalesAmountIsNotNull() {
            addCriterion("sales_amount is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPromotePriceIn(List<Long> values) {
            addCriterion("promote_price in", values, "promotePrice");
            return (Criteria) this;
        }
        public Criteria andPromotePriceNotIn(List<Long> values) {
            addCriterion("promote_price not in", values, "promotePrice");
            return (Criteria) this;
        }

        //between
        public Criteria andPromotePriceBetween(Long value1, Long value2) {
            addCriterion("promote_price between", value1, value2, "promotePrice");
            return (Criteria) this;
        }

        //like
        public Criteria andPromotePriceLike(String value) {
            addCriterion("promote_price like", value, "promotePrice");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPromotePriceEqualTo(Long value) {
            addCriterion("promote_price =", value, "promotePrice");
            return (Criteria) this;
        }
        public Criteria andPromotePriceNotEqualTo(Long value) {
            addCriterion("promote_price <>", value, "promotePrice");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPromotePriceGreaterThan(Long value) {
            addCriterion("promote_price >", value, "promotePrice");
            return (Criteria) this;
        }
        public Criteria andPromotePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("promote_price >=", value, "promotePrice");
            return (Criteria) this;
        }
        public Criteria andPromotePriceLessThan(Long value) {
            addCriterion("promote_price <", value, "promotePrice");
            return (Criteria) this;
        }
        public Criteria andPromotePriceLessThanOrEqualTo(Long value) {
            addCriterion("promote_price <=", value, "promotePrice");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPromotePriceIsNull() {
            addCriterion("promote_price is null");
            return (Criteria) this;
        }

        public Criteria andPromotePriceIsNotNull() {
            addCriterion("promote_price is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andSalesNumIn(List<Integer> values) {
            addCriterion("sales_num in", values, "salesNum");
            return (Criteria) this;
        }
        public Criteria andSalesNumNotIn(List<Integer> values) {
            addCriterion("sales_num not in", values, "salesNum");
            return (Criteria) this;
        }

        //between
        public Criteria andSalesNumBetween(Integer value1, Integer value2) {
            addCriterion("sales_num between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        //like
        public Criteria andSalesNumLike(String value) {
            addCriterion("sales_num like", value, "salesNum");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSalesNumEqualTo(Integer value) {
            addCriterion("sales_num =", value, "salesNum");
            return (Criteria) this;
        }
        public Criteria andSalesNumNotEqualTo(Integer value) {
            addCriterion("sales_num <>", value, "salesNum");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSalesNumGreaterThan(Integer value) {
            addCriterion("sales_num >", value, "salesNum");
            return (Criteria) this;
        }
        public Criteria andSalesNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_num >=", value, "salesNum");
            return (Criteria) this;
        }
        public Criteria andSalesNumLessThan(Integer value) {
            addCriterion("sales_num <", value, "salesNum");
            return (Criteria) this;
        }
        public Criteria andSalesNumLessThanOrEqualTo(Integer value) {
            addCriterion("sales_num <=", value, "salesNum");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSalesNumIsNull() {
            addCriterion("sales_num is null");
            return (Criteria) this;
        }

        public Criteria andSalesNumIsNotNull() {
            addCriterion("sales_num is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPromoteStartTimeIn(List<Date> values) {
            addCriterion("promote_start_time in", values, "promoteStartTime");
            return (Criteria) this;
        }
        public Criteria andPromoteStartTimeNotIn(List<Date> values) {
            addCriterion("promote_start_time not in", values, "promoteStartTime");
            return (Criteria) this;
        }

        //between
        public Criteria andPromoteStartTimeBetween(Date value1, Date value2) {
            addCriterion("promote_start_time between", value1, value2, "promoteStartTime");
            return (Criteria) this;
        }

        //like
        public Criteria andPromoteStartTimeLike(String value) {
            addCriterion("promote_start_time like", value, "promoteStartTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPromoteStartTimeEqualTo(Date value) {
            addCriterion("promote_start_time =", value, "promoteStartTime");
            return (Criteria) this;
        }
        public Criteria andPromoteStartTimeNotEqualTo(Date value) {
            addCriterion("promote_start_time <>", value, "promoteStartTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPromoteStartTimeGreaterThan(Date value) {
            addCriterion("promote_start_time >", value, "promoteStartTime");
            return (Criteria) this;
        }
        public Criteria andPromoteStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("promote_start_time >=", value, "promoteStartTime");
            return (Criteria) this;
        }
        public Criteria andPromoteStartTimeLessThan(Date value) {
            addCriterion("promote_start_time <", value, "promoteStartTime");
            return (Criteria) this;
        }
        public Criteria andPromoteStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("promote_start_time <=", value, "promoteStartTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPromoteStartTimeIsNull() {
            addCriterion("promote_start_time is null");
            return (Criteria) this;
        }

        public Criteria andPromoteStartTimeIsNotNull() {
            addCriterion("promote_start_time is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andPromoteEndTimeIn(List<Date> values) {
            addCriterion("promote_end_time in", values, "promoteEndTime");
            return (Criteria) this;
        }
        public Criteria andPromoteEndTimeNotIn(List<Date> values) {
            addCriterion("promote_end_time not in", values, "promoteEndTime");
            return (Criteria) this;
        }

        //between
        public Criteria andPromoteEndTimeBetween(Date value1, Date value2) {
            addCriterion("promote_end_time between", value1, value2, "promoteEndTime");
            return (Criteria) this;
        }

        //like
        public Criteria andPromoteEndTimeLike(String value) {
            addCriterion("promote_end_time like", value, "promoteEndTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andPromoteEndTimeEqualTo(Date value) {
            addCriterion("promote_end_time =", value, "promoteEndTime");
            return (Criteria) this;
        }
        public Criteria andPromoteEndTimeNotEqualTo(Date value) {
            addCriterion("promote_end_time <>", value, "promoteEndTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andPromoteEndTimeGreaterThan(Date value) {
            addCriterion("promote_end_time >", value, "promoteEndTime");
            return (Criteria) this;
        }
        public Criteria andPromoteEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("promote_end_time >=", value, "promoteEndTime");
            return (Criteria) this;
        }
        public Criteria andPromoteEndTimeLessThan(Date value) {
            addCriterion("promote_end_time <", value, "promoteEndTime");
            return (Criteria) this;
        }
        public Criteria andPromoteEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("promote_end_time <=", value, "promoteEndTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andPromoteEndTimeIsNull() {
            addCriterion("promote_end_time is null");
            return (Criteria) this;
        }

        public Criteria andPromoteEndTimeIsNotNull() {
            addCriterion("promote_end_time is not null");
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
        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        //between
        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        //like
        public Criteria andSendTimeLike(String value) {
            addCriterion("send_time like", value, "sendTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }
        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }
        // in,   not in
        public Criteria andWeightProjectTimeIn(List<Date> values) {
            addCriterion("weight_project_time in", values, "weightProjectTime");
            return (Criteria) this;
        }
        public Criteria andWeightProjectTimeNotIn(List<Date> values) {
            addCriterion("weight_project_time not in", values, "weightProjectTime");
            return (Criteria) this;
        }

        //between
        public Criteria andWeightProjectTimeBetween(Date value1, Date value2) {
            addCriterion("weight_project_time between", value1, value2, "weightProjectTime");
            return (Criteria) this;
        }

        //like
        public Criteria andWeightProjectTimeLike(String value) {
            addCriterion("weight_project_time like", value, "weightProjectTime");
            return (Criteria) this;
        }

        // = <>
        public Criteria andWeightProjectTimeEqualTo(Date value) {
            addCriterion("weight_project_time =", value, "weightProjectTime");
            return (Criteria) this;
        }
        public Criteria andWeightProjectTimeNotEqualTo(Date value) {
            addCriterion("weight_project_time <>", value, "weightProjectTime");
            return (Criteria) this;
        }


        // > >= < <=
        public Criteria andWeightProjectTimeGreaterThan(Date value) {
            addCriterion("weight_project_time >", value, "weightProjectTime");
            return (Criteria) this;
        }
        public Criteria andWeightProjectTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("weight_project_time >=", value, "weightProjectTime");
            return (Criteria) this;
        }
        public Criteria andWeightProjectTimeLessThan(Date value) {
            addCriterion("weight_project_time <", value, "weightProjectTime");
            return (Criteria) this;
        }
        public Criteria andWeightProjectTimeLessThanOrEqualTo(Date value) {
            addCriterion("weight_project_time <=", value, "weightProjectTime");
            return (Criteria) this;
        }

        //null  not null
        public Criteria andWeightProjectTimeIsNull() {
            addCriterion("weight_project_time is null");
            return (Criteria) this;
        }

        public Criteria andWeightProjectTimeIsNotNull() {
            addCriterion("weight_project_time is not null");
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


        ///////////////////////
        // in,   not in
        public Criteria andHotIn(List<Integer> values) {
            addCriterion("hot in", values, "validity");
            return (Criteria) this;
        }
         public Criteria andHotNotIn(List<Integer> values) {
             addCriterion("hot not in", values, "validity");
             return (Criteria) this;
         }

         //between
         public Criteria andHotBetween(Integer value1, Integer value2) {
             addCriterion("hot between", value1, value2, "validity");
             return (Criteria) this;
         }

         //like
         public Criteria andHotLike(String value) {
             addCriterion("hot like", value, "validity");
             return (Criteria) this;
         }

         // = <>
         public Criteria andHotEqualTo(Integer value) {
             addCriterion("hot =", value, "validity");
             return (Criteria) this;
         }
         public Criteria andHotNotEqualTo(Integer value) {
             addCriterion("hot <>", value, "validity");
             return (Criteria) this;
         }


         // > >= < <=
         public Criteria andHotGreaterThan(Integer value) {
             addCriterion("hot >", value, "validity");
             return (Criteria) this;
         }
         public Criteria andHotGreaterThanOrEqualTo(Integer value) {
             addCriterion("hot >=", value, "validity");
             return (Criteria) this;
         }
         public Criteria andHotLessThan(Integer value) {
             addCriterion("hot <", value, "validity");
             return (Criteria) this;
         }
         public Criteria andHotLessThanOrEqualTo(Integer value) {
             addCriterion("hot <=", value, "validity");
             return (Criteria) this;
         }

         //null  not null
         public Criteria andHotIsNull() {
             addCriterion("hot is null");
             return (Criteria) this;
         }

         public Criteria andHotIsNotNull() {
             addCriterion("hot is not null");
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