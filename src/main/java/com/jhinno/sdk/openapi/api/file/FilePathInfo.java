package com.jhinno.sdk.openapi.api.file;

import lombok.Data;

/**
 * 文件创建消息
 *
 * @author yanlongqi
 * @date 2024/2/5 14:17
 */
@Data
public class FilePathInfo {

    /**
     * 新的文件夹的位置
     */
    private String dirPath;
}
