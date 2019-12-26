package com.moyuaninfo.waterinfo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyyDeviceDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MyyDeviceDataExample() {
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

        public Criteria andDeviceDataIdIsNull() {
            addCriterion("device_data_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdIsNotNull() {
            addCriterion("device_data_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdEqualTo(Integer value) {
            addCriterion("device_data_id =", value, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdNotEqualTo(Integer value) {
            addCriterion("device_data_id <>", value, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdGreaterThan(Integer value) {
            addCriterion("device_data_id >", value, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_data_id >=", value, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdLessThan(Integer value) {
            addCriterion("device_data_id <", value, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_data_id <=", value, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdIn(List<Integer> values) {
            addCriterion("device_data_id in", values, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdNotIn(List<Integer> values) {
            addCriterion("device_data_id not in", values, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdBetween(Integer value1, Integer value2) {
            addCriterion("device_data_id between", value1, value2, "deviceDataId");
            return (Criteria) this;
        }

        public Criteria andDeviceDataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_data_id not between", value1, value2, "deviceDataId");
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

        public Criteria andEffectiveIsNull() {
            addCriterion("effective is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveIsNotNull() {
            addCriterion("effective is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveEqualTo(Integer value) {
            addCriterion("effective =", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveNotEqualTo(Integer value) {
            addCriterion("effective <>", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveGreaterThan(Integer value) {
            addCriterion("effective >", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("effective >=", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveLessThan(Integer value) {
            addCriterion("effective <", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveLessThanOrEqualTo(Integer value) {
            addCriterion("effective <=", value, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveIn(List<Integer> values) {
            addCriterion("effective in", values, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveNotIn(List<Integer> values) {
            addCriterion("effective not in", values, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveBetween(Integer value1, Integer value2) {
            addCriterion("effective between", value1, value2, "effective");
            return (Criteria) this;
        }

        public Criteria andEffectiveNotBetween(Integer value1, Integer value2) {
            addCriterion("effective not between", value1, value2, "effective");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueIsNull() {
            addCriterion("digital_PH_value is null");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueIsNotNull() {
            addCriterion("digital_PH_value is not null");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueEqualTo(String value) {
            addCriterion("digital_PH_value =", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueNotEqualTo(String value) {
            addCriterion("digital_PH_value <>", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueGreaterThan(String value) {
            addCriterion("digital_PH_value >", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueGreaterThanOrEqualTo(String value) {
            addCriterion("digital_PH_value >=", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueLessThan(String value) {
            addCriterion("digital_PH_value <", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueLessThanOrEqualTo(String value) {
            addCriterion("digital_PH_value <=", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueLike(String value) {
            addCriterion("digital_PH_value like", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueNotLike(String value) {
            addCriterion("digital_PH_value not like", value, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueIn(List<String> values) {
            addCriterion("digital_PH_value in", values, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueNotIn(List<String> values) {
            addCriterion("digital_PH_value not in", values, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueBetween(String value1, String value2) {
            addCriterion("digital_PH_value between", value1, value2, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andDigitalPhValueNotBetween(String value1, String value2) {
            addCriterion("digital_PH_value not between", value1, value2, "digitalPhValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueIsNull() {
            addCriterion("temperature_value is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueIsNotNull() {
            addCriterion("temperature_value is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueEqualTo(String value) {
            addCriterion("temperature_value =", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueNotEqualTo(String value) {
            addCriterion("temperature_value <>", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueGreaterThan(String value) {
            addCriterion("temperature_value >", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueGreaterThanOrEqualTo(String value) {
            addCriterion("temperature_value >=", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueLessThan(String value) {
            addCriterion("temperature_value <", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueLessThanOrEqualTo(String value) {
            addCriterion("temperature_value <=", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueLike(String value) {
            addCriterion("temperature_value like", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueNotLike(String value) {
            addCriterion("temperature_value not like", value, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueIn(List<String> values) {
            addCriterion("temperature_value in", values, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueNotIn(List<String> values) {
            addCriterion("temperature_value not in", values, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueBetween(String value1, String value2) {
            addCriterion("temperature_value between", value1, value2, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andTemperatureValueNotBetween(String value1, String value2) {
            addCriterion("temperature_value not between", value1, value2, "temperatureValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueIsNull() {
            addCriterion("digital_turbidity_value is null");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueIsNotNull() {
            addCriterion("digital_turbidity_value is not null");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueEqualTo(String value) {
            addCriterion("digital_turbidity_value =", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueNotEqualTo(String value) {
            addCriterion("digital_turbidity_value <>", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueGreaterThan(String value) {
            addCriterion("digital_turbidity_value >", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueGreaterThanOrEqualTo(String value) {
            addCriterion("digital_turbidity_value >=", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueLessThan(String value) {
            addCriterion("digital_turbidity_value <", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueLessThanOrEqualTo(String value) {
            addCriterion("digital_turbidity_value <=", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueLike(String value) {
            addCriterion("digital_turbidity_value like", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueNotLike(String value) {
            addCriterion("digital_turbidity_value not like", value, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueIn(List<String> values) {
            addCriterion("digital_turbidity_value in", values, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueNotIn(List<String> values) {
            addCriterion("digital_turbidity_value not in", values, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueBetween(String value1, String value2) {
            addCriterion("digital_turbidity_value between", value1, value2, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDigitalTurbidityValueNotBetween(String value1, String value2) {
            addCriterion("digital_turbidity_value not between", value1, value2, "digitalTurbidityValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueIsNull() {
            addCriterion("dissolved_oxygen_value is null");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueIsNotNull() {
            addCriterion("dissolved_oxygen_value is not null");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueEqualTo(String value) {
            addCriterion("dissolved_oxygen_value =", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueNotEqualTo(String value) {
            addCriterion("dissolved_oxygen_value <>", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueGreaterThan(String value) {
            addCriterion("dissolved_oxygen_value >", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueGreaterThanOrEqualTo(String value) {
            addCriterion("dissolved_oxygen_value >=", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueLessThan(String value) {
            addCriterion("dissolved_oxygen_value <", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueLessThanOrEqualTo(String value) {
            addCriterion("dissolved_oxygen_value <=", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueLike(String value) {
            addCriterion("dissolved_oxygen_value like", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueNotLike(String value) {
            addCriterion("dissolved_oxygen_value not like", value, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueIn(List<String> values) {
            addCriterion("dissolved_oxygen_value in", values, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueNotIn(List<String> values) {
            addCriterion("dissolved_oxygen_value not in", values, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueBetween(String value1, String value2) {
            addCriterion("dissolved_oxygen_value between", value1, value2, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andDissolvedOxygenValueNotBetween(String value1, String value2) {
            addCriterion("dissolved_oxygen_value not between", value1, value2, "dissolvedOxygenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueIsNull() {
            addCriterion("`ammonia n_trogen_value` is null");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueIsNotNull() {
            addCriterion("`ammonia n_trogen_value` is not null");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueEqualTo(String value) {
            addCriterion("`ammonia n_trogen_value` =", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueNotEqualTo(String value) {
            addCriterion("`ammonia n_trogen_value` <>", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueGreaterThan(String value) {
            addCriterion("`ammonia n_trogen_value` >", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueGreaterThanOrEqualTo(String value) {
            addCriterion("`ammonia n_trogen_value` >=", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueLessThan(String value) {
            addCriterion("`ammonia n_trogen_value` <", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueLessThanOrEqualTo(String value) {
            addCriterion("`ammonia n_trogen_value` <=", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueLike(String value) {
            addCriterion("`ammonia n_trogen_value` like", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueNotLike(String value) {
            addCriterion("`ammonia n_trogen_value` not like", value, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueIn(List<String> values) {
            addCriterion("`ammonia n_trogen_value` in", values, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueNotIn(List<String> values) {
            addCriterion("`ammonia n_trogen_value` not in", values, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueBetween(String value1, String value2) {
            addCriterion("`ammonia n_trogen_value` between", value1, value2, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andAmmoniaNTrogenValueNotBetween(String value1, String value2) {
            addCriterion("`ammonia n_trogen_value` not between", value1, value2, "ammoniaNTrogenValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueIsNull() {
            addCriterion("total_phosphorus_value is null");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueIsNotNull() {
            addCriterion("total_phosphorus_value is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueEqualTo(String value) {
            addCriterion("total_phosphorus_value =", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueNotEqualTo(String value) {
            addCriterion("total_phosphorus_value <>", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueGreaterThan(String value) {
            addCriterion("total_phosphorus_value >", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueGreaterThanOrEqualTo(String value) {
            addCriterion("total_phosphorus_value >=", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueLessThan(String value) {
            addCriterion("total_phosphorus_value <", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueLessThanOrEqualTo(String value) {
            addCriterion("total_phosphorus_value <=", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueLike(String value) {
            addCriterion("total_phosphorus_value like", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueNotLike(String value) {
            addCriterion("total_phosphorus_value not like", value, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueIn(List<String> values) {
            addCriterion("total_phosphorus_value in", values, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueNotIn(List<String> values) {
            addCriterion("total_phosphorus_value not in", values, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueBetween(String value1, String value2) {
            addCriterion("total_phosphorus_value between", value1, value2, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andTotalPhosphorusValueNotBetween(String value1, String value2) {
            addCriterion("total_phosphorus_value not between", value1, value2, "totalPhosphorusValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueIsNull() {
            addCriterion("permanganate_value is null");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueIsNotNull() {
            addCriterion("permanganate_value is not null");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueEqualTo(String value) {
            addCriterion("permanganate_value =", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueNotEqualTo(String value) {
            addCriterion("permanganate_value <>", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueGreaterThan(String value) {
            addCriterion("permanganate_value >", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueGreaterThanOrEqualTo(String value) {
            addCriterion("permanganate_value >=", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueLessThan(String value) {
            addCriterion("permanganate_value <", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueLessThanOrEqualTo(String value) {
            addCriterion("permanganate_value <=", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueLike(String value) {
            addCriterion("permanganate_value like", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueNotLike(String value) {
            addCriterion("permanganate_value not like", value, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueIn(List<String> values) {
            addCriterion("permanganate_value in", values, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueNotIn(List<String> values) {
            addCriterion("permanganate_value not in", values, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueBetween(String value1, String value2) {
            addCriterion("permanganate_value between", value1, value2, "permanganateValue");
            return (Criteria) this;
        }

        public Criteria andPermanganateValueNotBetween(String value1, String value2) {
            addCriterion("permanganate_value not between", value1, value2, "permanganateValue");
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