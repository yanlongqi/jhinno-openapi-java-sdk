package com.jhinno.sdk.openapi.api.organization;

import lombok.Data;

/**
 * @author yanlongqi
 * @date 2024/2/19 11:24
 */
@Data
public class AddUpdateDepartment {

    /**
     * 部门名称（必填）
     */
    private String depName;

    /**
     * 部门中文名称（必填）
     */
    private String depNameCN;

    /**
     * 父级部门名称（必填）
     */
    private String parentDepName;

    /**
     * 部门说明（非必填）
     */
    private String depNote;
}
