package com.jhinno.sdk.openapi.api.file;

import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.client.JHApiClient;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanlongqi
 * @date 2024/2/4 18:58
 */
public class JHFileApiExecution extends JHApiExecution {

    public JHFileApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
    }


    /**
     * 重命名文件
     *
     * @param username       用户名
     * @param sourceFileName 源文件
     * @param targetName     目标文件
     */
    public void renameFile(String username, String sourceFileName, String targetName) {
        if (StringUtils.isBlank(sourceFileName)) {
            throw new ArgsException("sourceFileName不能为空！");
        }
        if (StringUtils.isBlank(targetName)) {
            throw new ArgsException("targetName不能为空！");
        }
        Map<String, Object> body = new HashMap<>(2);
        body.put("oldFileName", sourceFileName);
        body.put("newFileName", targetName);
        put(FilePathConstant.FILE_RENAME_PATH, username, body);
    }


    /**
     * 删除文件
     *
     * @param username 用户名
     * @param fileName 文件路径
     */
    public void deleteFile(String username, String fileName) {
        if (StringUtils.isBlank(fileName)) {
            throw new ArgsException("fileName不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("fileName", fileName);
        String path = JHApiClient.getUrl(FilePathConstant.FILE_DELETE_PATH, params);
        delete(path, username);
    }

}
