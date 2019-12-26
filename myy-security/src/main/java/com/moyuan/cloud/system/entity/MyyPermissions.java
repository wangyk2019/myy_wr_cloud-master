package com.moyuan.cloud.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_permissions")
@ApiModel(value = "MyyPermissions", description = "权限表")
@Data
public class MyyPermissions extends BaseEntity implements Serializable {


    @ApiModelProperty(value = "角色", example = "admin")
    private String role;

    @ApiModelProperty(value = "权限集", example = "camera,liveplay")
    private String perms;

    @Override
    public String toString() {
        return "MyyPermissions{" +
                "role='" + role + '\'' +
                ", perms='" + perms + '\'' +
                '}';
    }
}
