package com.jhinno.sdk.openapi.api.organization;

import lombok.Data;

import java.util.Date;

/**
 * @author yanlongqi
 * @date 2024/2/6 18:15
 */
@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户处中文名
     */
    private String userNameCn;


    private String userLevel;

    /**
     * 用户身份证
     */
    private String userCard;

    /**
     * 用户部门id
     */
    private Integer userDep;

    /**
     * 用户部门名称
     */
    private String userDepName;

    /**
     * 用户部门中文名称
     */
    private String userDepNameCn;

    /**
     * 用户说明
     */
    private String userNote;

    /**
     * 用户电话
     */
    private String userTel;

    /**
     * 用户邮箱
     */
    private String userMail;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户密级
     */
    private String userConf;

    /**
     * 用户状态
     * <ul>
     *     <li>normal（正常）</li>
     *     <li>unaudit（未审核）</li>
     *     <li>freezing（冻结）</li>
     * </ul>
     */
    private String userStat;

    /**
     * 用户最大作业数
     */
    private Integer userMaxJobs;


    /**
     * 修改时间
     */
    private String updateTime;

    /**
     *
     */
    private Integer count;

    /**
     * 用户登录时间
     */
    private Date userLoginTime;

    /**
     * 用户的创建者
     */
    private String userCreator;

    /**
     * 首次登录提醒
     */
    private String firstLoginAlert;

    /**
     * 最后修改密码的时间
     */
    private Date lastUpdatePasswordTime;

    /**
     * 密码是否初始化
     */
    private Boolean pwdInit;

    /**
     * 组编号
     */
    private Integer uidNumber;

    /**
     * 排序
     */
    private Date sort;

    /**
     * 用户账号过期时间
     */
    private String userExpireTime;
}
