package com.jhinno.sdk.openapi.api.file;

import lombok.Data;

/**
 * 文件信息
 *
 * @author yanlongqi
 * @date 2024/2/5 10:47
 */
@Data
public class FileInfo {
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
     * 文件属主
     */
    private String owner;

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


}
