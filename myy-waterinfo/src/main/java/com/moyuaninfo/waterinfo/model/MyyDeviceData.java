package com.moyuaninfo.waterinfo.model;

import java.io.Serializable;
import java.util.Date;

public class MyyDeviceData implements Serializable {
    /**
     * 水质信息详情表主键
     *
     * @mbggenerated
     */
    private Integer deviceDataId;

    /**
     * 水质设备分配id
     *
     * @mbggenerated
     */
    private Integer deviceListId;

    /**
     * 是否有效1，有效，0，删除或失效
     *
     * @mbggenerated
     */
    private Integer effective;

    /**
     * 数字式PH值
     *
     * @mbggenerated
     */
    private String digitalPhValue;

    /**
     * 温度
     *
     * @mbggenerated
     */
    private String temperatureValue;

    /**
     * 数字式浊度
     *
     * @mbggenerated
     */
    private String digitalTurbidityValue;

    /**
     * 数字式溶氧
     *
     * @mbggenerated
     */
    private String dissolvedOxygenValue;

    /**
     * 氨氮值
     *
     * @mbggenerated
     */
    private String ammoniaNTrogenValue;

    /**
     * 总磷值
     *
     * @mbggenerated
     */
    private String totalPhosphorusValue;

    /**
     * 高锰酸盐值
     *
     * @mbggenerated
     */
    private String permanganateValue;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getDeviceDataId() {
        return deviceDataId;
    }

    public void setDeviceDataId(Integer deviceDataId) {
        this.deviceDataId = deviceDataId;
    }

    public Integer getDeviceListId() {
        return deviceListId;
    }

    public void setDeviceListId(Integer deviceListId) {
        this.deviceListId = deviceListId;
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    public String getDigitalPhValue() {
        return digitalPhValue;
    }

    public void setDigitalPhValue(String digitalPhValue) {
        this.digitalPhValue = digitalPhValue;
    }

    public String getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(String temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getDigitalTurbidityValue() {
        return digitalTurbidityValue;
    }

    public void setDigitalTurbidityValue(String digitalTurbidityValue) {
        this.digitalTurbidityValue = digitalTurbidityValue;
    }

    public String getDissolvedOxygenValue() {
        return dissolvedOxygenValue;
    }

    public void setDissolvedOxygenValue(String dissolvedOxygenValue) {
        this.dissolvedOxygenValue = dissolvedOxygenValue;
    }

    public String getAmmoniaNTrogenValue() {
        return ammoniaNTrogenValue;
    }

    public void setAmmoniaNTrogenValue(String ammoniaNTrogenValue) {
        this.ammoniaNTrogenValue = ammoniaNTrogenValue;
    }

    public String getTotalPhosphorusValue() {
        return totalPhosphorusValue;
    }

    public void setTotalPhosphorusValue(String totalPhosphorusValue) {
        this.totalPhosphorusValue = totalPhosphorusValue;
    }

    public String getPermanganateValue() {
        return permanganateValue;
    }

    public void setPermanganateValue(String permanganateValue) {
        this.permanganateValue = permanganateValue;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceDataId=").append(deviceDataId);
        sb.append(", deviceListId=").append(deviceListId);
        sb.append(", effective=").append(effective);
        sb.append(", digitalPhValue=").append(digitalPhValue);
        sb.append(", temperatureValue=").append(temperatureValue);
        sb.append(", digitalTurbidityValue=").append(digitalTurbidityValue);
        sb.append(", dissolvedOxygenValue=").append(dissolvedOxygenValue);
        sb.append(", ammoniaNTrogenValue=").append(ammoniaNTrogenValue);
        sb.append(", totalPhosphorusValue=").append(totalPhosphorusValue);
        sb.append(", permanganateValue=").append(permanganateValue);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}