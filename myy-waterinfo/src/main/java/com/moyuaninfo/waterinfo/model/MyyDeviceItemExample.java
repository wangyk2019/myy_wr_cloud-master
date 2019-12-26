package com.moyuaninfo.waterinfo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyyDeviceItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyyDeviceItemExample() {
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

        public Criteria andDeviceItemIdIsNull() {
            addCriterion("device_item_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdIsNotNull() {
            addCriterion("device_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdEqualTo(Integer value) {
            addCriterion("device_item_id =", value, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdNotEqualTo(Integer value) {
            addCriterion("device_item_id <>", value, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdGreaterThan(Integer value) {
            addCriterion("device_item_id >", value, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_item_id >=", value, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdLessThan(Integer value) {
            addCriterion("device_item_id <", value, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_item_id <=", value, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdIn(List<Integer> values) {
            addCriterion("device_item_id in", values, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdNotIn(List<Integer> values) {
            addCriterion("device_item_id not in", values, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdBetween(Integer value1, Integer value2) {
            addCriterion("device_item_id between", value1, value2, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_item_id not between", value1, value2, "deviceItemId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdIsNull() {
            addCriterion("device_list_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdIsNotNull() {
            addCriterion("device_list_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdEqualTo(Integer value) {
            addCriterion("device_list_id =", value, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdNotEqualTo(Integer value) {
            addCriterion("device_list_id <>", value, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdGreaterThan(Integer value) {
            addCriterion("device_list_id >", value, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_list_id >=", value, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdLessThan(Integer value) {
            addCriterion("device_list_id <", value, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_list_id <=", value, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdIn(List<Integer> values) {
            addCriterion("device_list_id in", values, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdNotIn(List<Integer> values) {
            addCriterion("device_list_id not in", values, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdBetween(Integer value1, Integer value2) {
            addCriterion("device_list_id between", value1, value2, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceListIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_list_id not between", value1, value2, "deviceListId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(String value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(String value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(String value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(String value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(String value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLike(String value) {
            addCriterion("device_type like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotLike(String value) {
            addCriterion("device_type not like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<String> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<String> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(String value1, String value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(String value1, String value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("device_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("device_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("device_name =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("device_name <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("device_name >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_name >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("device_name <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("device_name <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("device_name like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("device_name not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("device_name in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("device_name not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("device_name between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("device_name not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDevicePriceIsNull() {
            addCriterion("device_price is null");
            return (Criteria) this;
        }

        public Criteria andDevicePriceIsNotNull() {
            addCriterion("device_price is not null");
            return (Criteria) this;
        }

        public Criteria andDevicePriceEqualTo(Long value) {
            addCriterion("device_price =", value, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceNotEqualTo(Long value) {
            addCriterion("device_price <>", value, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceGreaterThan(Long value) {
            addCriterion("device_price >", value, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("device_price >=", value, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceLessThan(Long value) {
            addCriterion("device_price <", value, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceLessThanOrEqualTo(Long value) {
            addCriterion("device_price <=", value, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceIn(List<Long> values) {
            addCriterion("device_price in", values, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceNotIn(List<Long> values) {
            addCriterion("device_price not in", values, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceBetween(Long value1, Long value2) {
            addCriterion("device_price between", value1, value2, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDevicePriceNotBetween(Long value1, Long value2) {
            addCriterion("device_price not between", value1, value2, "devicePrice");
            return (Criteria) this;
        }

        public Criteria andDetectRangeIsNull() {
            addCriterion("detect_range is null");
            return (Criteria) this;
        }

        public Criteria andDetectRangeIsNotNull() {
            addCriterion("detect_range is not null");
            return (Criteria) this;
        }

        public Criteria andDetectRangeEqualTo(String value) {
            addCriterion("detect_range =", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeNotEqualTo(String value) {
            addCriterion("detect_range <>", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeGreaterThan(String value) {
            addCriterion("detect_range >", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeGreaterThanOrEqualTo(String value) {
            addCriterion("detect_range >=", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeLessThan(String value) {
            addCriterion("detect_range <", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeLessThanOrEqualTo(String value) {
            addCriterion("detect_range <=", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeLike(String value) {
            addCriterion("detect_range like", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeNotLike(String value) {
            addCriterion("detect_range not like", value, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeIn(List<String> values) {
            addCriterion("detect_range in", values, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeNotIn(List<String> values) {
            addCriterion("detect_range not in", values, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeBetween(String value1, String value2) {
            addCriterion("detect_range between", value1, value2, "detectRange");
            return (Criteria) this;
        }

        public Criteria andDetectRangeNotBetween(String value1, String value2) {
            addCriterion("detect_range not between", value1, value2, "detectRange");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueIsNull() {
            addCriterion("precision_value is null");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueIsNotNull() {
            addCriterion("precision_value is not null");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueEqualTo(String value) {
            addCriterion("precision_value =", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueNotEqualTo(String value) {
            addCriterion("precision_value <>", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueGreaterThan(String value) {
            addCriterion("precision_value >", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueGreaterThanOrEqualTo(String value) {
            addCriterion("precision_value >=", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueLessThan(String value) {
            addCriterion("precision_value <", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueLessThanOrEqualTo(String value) {
            addCriterion("precision_value <=", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueLike(String value) {
            addCriterion("precision_value like", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueNotLike(String value) {
            addCriterion("precision_value not like", value, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueIn(List<String> values) {
            addCriterion("precision_value in", values, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueNotIn(List<String> values) {
            addCriterion("precision_value not in", values, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueBetween(String value1, String value2) {
            addCriterion("precision_value between", value1, value2, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrecisionValueNotBetween(String value1, String value2) {
            addCriterion("precision_value not between", value1, value2, "precisionValue");
            return (Criteria) this;
        }

        public Criteria andPrincipleIsNull() {
            addCriterion("principle is null");
            return (Criteria) this;
        }

        public Criteria andPrincipleIsNotNull() {
            addCriterion("principle is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipleEqualTo(String value) {
            addCriterion("principle =", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleNotEqualTo(String value) {
            addCriterion("principle <>", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleGreaterThan(String value) {
            addCriterion("principle >", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleGreaterThanOrEqualTo(String value) {
            addCriterion("principle >=", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleLessThan(String value) {
            addCriterion("principle <", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleLessThanOrEqualTo(String value) {
            addCriterion("principle <=", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleLike(String value) {
            addCriterion("principle like", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleNotLike(String value) {
            addCriterion("principle not like", value, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleIn(List<String> values) {
            addCriterion("principle in", values, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleNotIn(List<String> values) {
            addCriterion("principle not in", values, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleBetween(String value1, String value2) {
            addCriterion("principle between", value1, value2, "principle");
            return (Criteria) this;
        }

        public Criteria andPrincipleNotBetween(String value1, String value2) {
            addCriterion("principle not between", value1, value2, "principle");
            return (Criteria) this;
        }

        public Criteria andCommIsNull() {
            addCriterion("comm is null");
            return (Criteria) this;
        }

        public Criteria andCommIsNotNull() {
            addCriterion("comm is not null");
            return (Criteria) this;
        }

        public Criteria andCommEqualTo(String value) {
            addCriterion("comm =", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommNotEqualTo(String value) {
            addCriterion("comm <>", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommGreaterThan(String value) {
            addCriterion("comm >", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommGreaterThanOrEqualTo(String value) {
            addCriterion("comm >=", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommLessThan(String value) {
            addCriterion("comm <", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommLessThanOrEqualTo(String value) {
            addCriterion("comm <=", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommLike(String value) {
            addCriterion("comm like", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommNotLike(String value) {
            addCriterion("comm not like", value, "comm");
            return (Criteria) this;
        }

        public Criteria andCommIn(List<String> values) {
            addCriterion("comm in", values, "comm");
            return (Criteria) this;
        }

        public Criteria andCommNotIn(List<String> values) {
            addCriterion("comm not in", values, "comm");
            return (Criteria) this;
        }

        public Criteria andCommBetween(String value1, String value2) {
            addCriterion("comm between", value1, value2, "comm");
            return (Criteria) this;
        }

        public Criteria andCommNotBetween(String value1, String value2) {
            addCriterion("comm not between", value1, value2, "comm");
            return (Criteria) this;
        }

        public Criteria andDetectObjectIsNull() {
            addCriterion("detect_object is null");
            return (Criteria) this;
        }

        public Criteria andDetectObjectIsNotNull() {
            addCriterion("detect_object is not null");
            return (Criteria) this;
        }

        public Criteria andDetectObjectEqualTo(String value) {
            addCriterion("detect_object =", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectNotEqualTo(String value) {
            addCriterion("detect_object <>", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectGreaterThan(String value) {
            addCriterion("detect_object >", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectGreaterThanOrEqualTo(String value) {
            addCriterion("detect_object >=", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectLessThan(String value) {
            addCriterion("detect_object <", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectLessThanOrEqualTo(String value) {
            addCriterion("detect_object <=", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectLike(String value) {
            addCriterion("detect_object like", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectNotLike(String value) {
            addCriterion("detect_object not like", value, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectIn(List<String> values) {
            addCriterion("detect_object in", values, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectNotIn(List<String> values) {
            addCriterion("detect_object not in", values, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectBetween(String value1, String value2) {
            addCriterion("detect_object between", value1, value2, "detectObject");
            return (Criteria) this;
        }

        public Criteria andDetectObjectNotBetween(String value1, String value2) {
            addCriterion("detect_object not between", value1, value2, "detectObject");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andEx1IsNull() {
            addCriterion("ex1 is null");
            return (Criteria) this;
        }

        public Criteria andEx1IsNotNull() {
            addCriterion("ex1 is not null");
            return (Criteria) this;
        }

        public Criteria andEx1EqualTo(String value) {
            addCriterion("ex1 =", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1NotEqualTo(String value) {
            addCriterion("ex1 <>", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1GreaterThan(String value) {
            addCriterion("ex1 >", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1GreaterThanOrEqualTo(String value) {
            addCriterion("ex1 >=", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1LessThan(String value) {
            addCriterion("ex1 <", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1LessThanOrEqualTo(String value) {
            addCriterion("ex1 <=", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1Like(String value) {
            addCriterion("ex1 like", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1NotLike(String value) {
            addCriterion("ex1 not like", value, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1In(List<String> values) {
            addCriterion("ex1 in", values, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1NotIn(List<String> values) {
            addCriterion("ex1 not in", values, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1Between(String value1, String value2) {
            addCriterion("ex1 between", value1, value2, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx1NotBetween(String value1, String value2) {
            addCriterion("ex1 not between", value1, value2, "ex1");
            return (Criteria) this;
        }

        public Criteria andEx2IsNull() {
            addCriterion("ex2 is null");
            return (Criteria) this;
        }

        public Criteria andEx2IsNotNull() {
            addCriterion("ex2 is not null");
            return (Criteria) this;
        }

        public Criteria andEx2EqualTo(String value) {
            addCriterion("ex2 =", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2NotEqualTo(String value) {
            addCriterion("ex2 <>", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2GreaterThan(String value) {
            addCriterion("ex2 >", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2GreaterThanOrEqualTo(String value) {
            addCriterion("ex2 >=", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2LessThan(String value) {
            addCriterion("ex2 <", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2LessThanOrEqualTo(String value) {
            addCriterion("ex2 <=", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2Like(String value) {
            addCriterion("ex2 like", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2NotLike(String value) {
            addCriterion("ex2 not like", value, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2In(List<String> values) {
            addCriterion("ex2 in", values, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2NotIn(List<String> values) {
            addCriterion("ex2 not in", values, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2Between(String value1, String value2) {
            addCriterion("ex2 between", value1, value2, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx2NotBetween(String value1, String value2) {
            addCriterion("ex2 not between", value1, value2, "ex2");
            return (Criteria) this;
        }

        public Criteria andEx3IsNull() {
            addCriterion("ex3 is null");
            return (Criteria) this;
        }

        public Criteria andEx3IsNotNull() {
            addCriterion("ex3 is not null");
            return (Criteria) this;
        }

        public Criteria andEx3EqualTo(String value) {
            addCriterion("ex3 =", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3NotEqualTo(String value) {
            addCriterion("ex3 <>", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3GreaterThan(String value) {
            addCriterion("ex3 >", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3GreaterThanOrEqualTo(String value) {
            addCriterion("ex3 >=", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3LessThan(String value) {
            addCriterion("ex3 <", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3LessThanOrEqualTo(String value) {
            addCriterion("ex3 <=", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3Like(String value) {
            addCriterion("ex3 like", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3NotLike(String value) {
            addCriterion("ex3 not like", value, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3In(List<String> values) {
            addCriterion("ex3 in", values, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3NotIn(List<String> values) {
            addCriterion("ex3 not in", values, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3Between(String value1, String value2) {
            addCriterion("ex3 between", value1, value2, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx3NotBetween(String value1, String value2) {
            addCriterion("ex3 not between", value1, value2, "ex3");
            return (Criteria) this;
        }

        public Criteria andEx4IsNull() {
            addCriterion("ex4 is null");
            return (Criteria) this;
        }

        public Criteria andEx4IsNotNull() {
            addCriterion("ex4 is not null");
            return (Criteria) this;
        }

        public Criteria andEx4EqualTo(String value) {
            addCriterion("ex4 =", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4NotEqualTo(String value) {
            addCriterion("ex4 <>", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4GreaterThan(String value) {
            addCriterion("ex4 >", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4GreaterThanOrEqualTo(String value) {
            addCriterion("ex4 >=", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4LessThan(String value) {
            addCriterion("ex4 <", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4LessThanOrEqualTo(String value) {
            addCriterion("ex4 <=", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4Like(String value) {
            addCriterion("ex4 like", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4NotLike(String value) {
            addCriterion("ex4 not like", value, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4In(List<String> values) {
            addCriterion("ex4 in", values, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4NotIn(List<String> values) {
            addCriterion("ex4 not in", values, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4Between(String value1, String value2) {
            addCriterion("ex4 between", value1, value2, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx4NotBetween(String value1, String value2) {
            addCriterion("ex4 not between", value1, value2, "ex4");
            return (Criteria) this;
        }

        public Criteria andEx5IsNull() {
            addCriterion("ex5 is null");
            return (Criteria) this;
        }

        public Criteria andEx5IsNotNull() {
            addCriterion("ex5 is not null");
            return (Criteria) this;
        }

        public Criteria andEx5EqualTo(String value) {
            addCriterion("ex5 =", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5NotEqualTo(String value) {
            addCriterion("ex5 <>", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5GreaterThan(String value) {
            addCriterion("ex5 >", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5GreaterThanOrEqualTo(String value) {
            addCriterion("ex5 >=", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5LessThan(String value) {
            addCriterion("ex5 <", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5LessThanOrEqualTo(String value) {
            addCriterion("ex5 <=", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5Like(String value) {
            addCriterion("ex5 like", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5NotLike(String value) {
            addCriterion("ex5 not like", value, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5In(List<String> values) {
            addCriterion("ex5 in", values, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5NotIn(List<String> values) {
            addCriterion("ex5 not in", values, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5Between(String value1, String value2) {
            addCriterion("ex5 between", value1, value2, "ex5");
            return (Criteria) this;
        }

        public Criteria andEx5NotBetween(String value1, String value2) {
            addCriterion("ex5 not between", value1, value2, "ex5");
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