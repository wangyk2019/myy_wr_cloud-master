package com.moyuaninfo.waterinfo.model;

import java.io.Serializable;
import java.util.Date;

public class MyyDeviceList implements Serializable {
    /**
     * 采集设备分配表主键id
     *
     * @mbggenerated
     */
    private Integer deviceListId;

    /**
     * 所属河流
     *
     * @mbggenerated
     */
    private Integer areaoneId;

    /**
     * 地址
     *
     * @mbggenerated
     */
    private String addr;

    /**
     * 经度
     *
     * @mbggenerated
     */
    private String latitude;

    /**
     * 纬度
     *
     * @mbggenerated
     */
    private String longitude;

    /**
     * 是否有效1，有效，0，删除或失效
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * 创建用户
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String comment;

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

    private static final long serialVersionUID = 1L;

    public Integer getDeviceListId() {
        return deviceListId;
    }

    public void setDeviceListId(Integer deviceListId) {
        this.deviceListId = deviceListId;
    }

    public Integer getAreaoneId() {
        return areaoneId;
    }

    public void setAreaoneId(Integer areaoneId) {
        this.areaoneId = areaoneId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        sb.append(", deviceListId=").append(deviceListId);
        sb.append(", areaoneId=").append(areaoneId);
        sb.append(", addr=").append(addr);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", state=").append(state);
        sb.append(", createUser=").append(createUser);
        sb.append(", comment=").append(comment);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}