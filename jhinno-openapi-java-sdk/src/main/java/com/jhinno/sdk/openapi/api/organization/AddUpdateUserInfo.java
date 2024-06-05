package com.jhinno.sdk.openapi.api.organization;

import lombok.Data;

import java.util.Date;

/**
 * 添加用户
 *
 * @author yanlongqi
 * @date 2024/2/19 10:14
 */
@Data
public class AddUpdateUserInfo {

    /**
     * 用户名（必填）
     */
    private String userName;

    /**
     * 用户中文名（必填）
     */
    private String userNameCn;

    /**
     * 用户密码（必填，需要修改）
     */
    private String userPassword;

    /**
     * 用户身份证（非必填）
     */
    private String userCard;

    /**
     * 部门名称（必填）
     */
    private String depName;

    /**
     * 部门中文名称（没必要）
     */
    private String depNameCn;

    /**
     * 用户说明（非必填）
     */
    private String userNote;

    /**
     * 用户电话（非必填）
     */
    private String userTel;

    /**
     * 用户邮箱（非必填）
     */
    private String userMail;

    /**
     * 用户密级配置（非必填）
     */
    private String userConf;

    /**
     * 用户密级配置中文名（没必要）
     */
    private String userConfCn;

    /**
     * 用户角色（非必填）
     */
    private String userRole;

    /**
     * 用户角色中文名（没必要）
     */
    private String userRoleCn;

    /**
     * 账号有效时间（非必填，为空这表示永不失效）
     */
    private Date userExpireTime;


    /**
     * 属性（不知道有什么用）
     */
    private String attribute;
}
