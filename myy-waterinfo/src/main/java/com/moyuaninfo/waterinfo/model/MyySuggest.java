package com.moyuaninfo.waterinfo.model;

import java.io.Serializable;
import java.util.Date;

public class MyySuggest implements Serializable {
    private Long id;

    /**
     * 0：问题咨询，1：巡查问题
     *
     * @mbggenerated
     */
    private Integer suggestType;

    /**
     * 问题地点
     *
     * @mbggenerated
     */
    private String site;

    /**
     * 处理状态，0：未处理，1：已处理
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 关联区域
     *
     * @mbggenerated
     */
    private Integer areaId;

    /**
     * 组织ID
     *
     * @mbggenerated
     */
    private Integer districtId;

    /**
     * 暂定为：申报人
     *
     * @mbggenerated
     */
    private String createUserName;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private Long createUser;

    /**
     * 处理人
     *
     * @mbggenerated
     */
    private String updateUserName;

    /**
     * 处理人联系方式
     *
     * @mbggenerated
     */
    private String updateUserPhonenumber;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private Long updateUser;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String comment;

    /**
     * 0：无效，1：有效
     *
     * @mbggenerated
     */
    private String state;

    /**
     * 咨询建议内容
     *
     * @mbggenerated
     */
    private String content;

    /**
     * 咨询建议拍照图片路径，多张中间用";"隔开
     *
     * @mbggenerated
     */
    private String photoPath;

    /**
     * 结果
     *
     * @mbggenerated
     */
    private String result;

    /**
     * 处理解决图片路径，多张中间用";"隔开
     *
     * @mbggenerated
     */
    private String solvePhotoPath;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSuggestType() {
        return suggestType;
    }

    public void setSuggestType(Integer suggestType) {
        this.suggestType = suggestType;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserPhonenumber() {
        return updateUserPhonenumber;
    }

    public void setUpdateUserPhonenumber(String updateUserPhonenumber) {
        this.updateUserPhonenumber = updateUserPhonenumber;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSolvePhotoPath() {
        return solvePhotoPath;
    }

    public void setSolvePhotoPath(String solvePhotoPath) {
        this.solvePhotoPath = solvePhotoPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", suggestType=").append(suggestType);
        sb.append(", site=").append(site);
        sb.append(", status=").append(status);
        sb.append(", areaId=").append(areaId);
        sb.append(", districtId=").append(districtId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUserName=").append(updateUserName);
        sb.append(", updateUserPhonenumber=").append(updateUserPhonenumber);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", comment=").append(comment);
        sb.append(", state=").append(state);
        sb.append(", content=").append(content);
        sb.append(", photoPath=").append(photoPath);
        sb.append(", result=").append(result);
        sb.append(", solvePhotoPath=").append(solvePhotoPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}