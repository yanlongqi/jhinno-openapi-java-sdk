package com.jhinno.sdk.openapi.api.file;

import lombok.Data;

import java.util.Date;

/**
 * 文件信息
 *
 * @author yanlongqi
 * @date 2024/2/5 10:47
 */
@Data
public class FileInfo {


    /**
     * 绝对路径
     */
    private String absolutePath;

    /**
     * 是否为空
     */
    private Boolean empty;


    /**
     * 文件密级
     */
    private Integer fileConfLevel;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 是否文件夹
     */
    private Boolean folder;

    /**
     * ID
     */
    private Long id;

    /**
     * 最后修改时间
     */
    private Date lastModified;

    /**
     * 权限
     */
    private String permission;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件类型
     * <ul>
     *     <li>directory：文件夹</li>
     *     <li>file：文件</li>
     * </ul>
     */
    private String fileType;

    /**
     * 名称
     */
    private String name;

    /**
     * 文件属主
     */
    private String owner;

    /**
     * 组
     */
    private String group;

    /**
     * 其他
     */
    private String other;

    /**
     * 是否可读
     */
    private Boolean read;

    /**
     * 是否可写
     */
    private Boolean write;

    /**
     * 是否可执行
     */
    private Boolean execute;

    /**
     * 是否软链接
     */
    private Integer link;


}
