package com.moyuaninfo.waterinfo.model;

import java.io.Serializable;

public class MyyCityDistrict implements Serializable {
    /**
     * 区域ID、
     *
     * @mbggenerated
     */
    private Integer districtid;

    /**
     * 区名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 区域行政代码
     *
     * @mbggenerated
     */
    private Integer adcode;

    private static final long serialVersionUID = 1L;

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAdcode() {
        return adcode;
    }

    public void setAdcode(Integer adcode) {
        this.adcode = adcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", districtid=").append(districtid);
        sb.append(", name=").append(name);
        sb.append(", adcode=").append(adcode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}