package com.moyuaninfo.waterinfo.model;

import java.io.Serializable;
import java.util.Date;

public class MyyDeviceItem implements Serializable {
    /**
     * 采集设备表主键
     *
     * @mbggenerated
     */
    private Integer deviceItemId;

    /**
     * 分配表id
     *
     * @mbggenerated
     */
    private Integer deviceListId;

    /**
     * 设备类型
     *
     * @mbggenerated
     */
    private String deviceType;

    /**
     * 设备名称
     *
     * @mbggenerated
     */
    private String deviceName;

    /**
     * 设备价格
     *
     * @mbggenerated
     */
    private Long devicePrice;

    /**
     * 监测范围
     *
     * @mbggenerated
     */
    private String detectRange;

    /**
     * 单位
     *
     * @mbggenerated
     */
    private String unit;

    /**
     * 精度
     *
     * @mbggenerated
     */
    private String precisionValue;

    /**
     * 原理
     *
     * @mbggenerated
     */
    private String principle;

    /**
     * 通讯方式
     *
     * @mbggenerated
     */
    private String comm;

    /**
     * 监控对象
     *
     * @mbggenerated
     */
    private String detectObject;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date updatetime;

    /**
     * 是否有效：0无效，1有效
     *
     * @mbggenerated
     */
    private Integer state;

    private String ex1;

    private String ex2;

    private String ex3;

    private String ex4;

    private String ex5;

    /**
     * 描述
     *
     * @mbggenerated
     */
    private byte[] describe;

    private static final long serialVersionUID = 1L;

    public Integer getDeviceItemId() {
        return deviceItemId;
    }

    public void setDeviceItemId(Integer deviceItemId) {
        this.deviceItemId = deviceItemId;
    }

    public Integer getDeviceListId() {
        return deviceListId;
    }

    public void setDeviceListId(Integer deviceListId) {
        this.deviceListId = deviceListId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(Long devicePrice) {
        this.devicePrice = devicePrice;
    }

    public String getDetectRange() {
        return detectRange;
    }

    public void setDetectRange(String detectRange) {
        this.detectRange = detectRange;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrecisionValue() {
        return precisionValue;
    }

    public void setPrecisionValue(String precisionValue) {
        this.precisionValue = precisionValue;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getDetectObject() {
        return detectObject;
    }

    public void setDetectObject(String detectObject) {
        this.detectObject = detectObject;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public String getEx2() {
        return ex2;
    }

    public void setEx2(String ex2) {
        this.ex2 = ex2;
    }

    public String getEx3() {
        return ex3;
    }

    public void setEx3(String ex3) {
        this.ex3 = ex3;
    }

    public String getEx4() {
        return ex4;
    }

    public void setEx4(String ex4) {
        this.ex4 = ex4;
    }

    public String getEx5() {
        return ex5;
    }

    public void setEx5(String ex5) {
        this.ex5 = ex5;
    }

    public byte[] getDescribe() {
        return describe;
    }

    public void setDescribe(byte[] describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceItemId=").append(deviceItemId);
        sb.append(", deviceListId=").append(deviceListId);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", devicePrice=").append(devicePrice);
        sb.append(", detectRange=").append(detectRange);
        sb.append(", unit=").append(unit);
        sb.append(", precisionValue=").append(precisionValue);
        sb.append(", principle=").append(principle);
        sb.append(", comm=").append(comm);
        sb.append(", detectObject=").append(detectObject);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", state=").append(state);
        sb.append(", ex1=").append(ex1);
        sb.append(", ex2=").append(ex2);
        sb.append(", ex3=").append(ex3);
        sb.append(", ex4=").append(ex4);
        sb.append(", ex5=").append(ex5);
        sb.append(", describe=").append(describe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}