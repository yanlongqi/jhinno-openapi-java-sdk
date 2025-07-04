package com.jhinno.sdk.openapi.api.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.CommonConstant;
import com.jhinno.sdk.openapi.JHApiExecution;
import com.jhinno.sdk.openapi.ServiceException;
import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import com.jhinno.sdk.openapi.utils.CollectionUtil;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件相关接口执行器
 *
 * @author yanlongqi
 * @date 2024/2/4 18:58
 */
@NoArgsConstructor
public class JHFileApiExecution implements JHApiExecution {

    private JHRequestExecution execution;

    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }

    /**
     * 重命名文件
     *
     * @param username           用户名
     * @param sourceFileNamePath 源文件路径
     * @param targetFileName     目标文件路径
     */
    public void renameFile(String username, String sourceFileNamePath, String targetFileName) {
        if (StringUtils.isBlank(sourceFileNamePath)) {
            throw new ArgsException("sourceFileNamePath不能为空！");
        }
        if (StringUtils.isBlank(targetFileName)) {
            throw new ArgsException("targetFileName不能为空！");
        }
        Map<String, Object> body = new HashMap<>(2);
        body.put("oldFileName", sourceFileNamePath);
        body.put("newFileName", targetFileName);
        execution.put(FilePathConstant.FILE_RENAME_PATH, username, body);
    }

    /**
     * 删除文件
     *
     * @param username       用户名
     * @param sourceFilePath 源文件路径
     */
    public void deleteFile(String username, String sourceFilePath) {
        if (StringUtils.isBlank(sourceFilePath)) {
            throw new ArgsException("sourceFilePath不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("fileName", sourceFilePath);
        String path = JHApiClient.getUrl(FilePathConstant.FILE_DELETE_PATH, params);
        execution.delete(path, username);
    }

    /**
     * 拷贝文件到目标文件夹
     *
     * @param username            用户名
     * @param sourceFilePath      源文件路径
     * @param targetDirectoryPath 目标文件路径
     */
    public void copyFile(String username, String sourceFilePath, String targetDirectoryPath) {
        if (StringUtils.isBlank(sourceFilePath)) {
            throw new ArgsException("sourceFilePath不能为空！");
        }
        if (StringUtils.isBlank(targetDirectoryPath)) {
            throw new ArgsException("targetDirectoryPath不能为空！");
        }
        Map<String, Object> body = new HashMap<>(2);
        body.put("sourceFileName", sourceFilePath);
        body.put("targetDirectory", targetDirectoryPath);
        execution.put(FilePathConstant.FILE_COPY_PATH, username, body);
    }

    /**
     * 获取文件列表
     *
     * @param username 用户名
     * @param dirPath  文件路径
     * @return 文件列表
     */
    public List<FileInfo> getFileList(String username, String dirPath) {
        if (StringUtils.isBlank(dirPath)) {
            throw new ArgsException("path不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("dir", dirPath);
        String path = JHApiClient.getUrl(FilePathConstant.FILE_LIST_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<List<FileInfo>>>() {
        });
    }

    /**
     * 创建文件夹
     *
     * @param username 用户名
     * @param dirPath  文件夹路径
     * @param isForce  是否强制创建（非必传，默认：false）
     * @return 新建后的文件路径
     */
    public String mkdir(String username, String dirPath, Boolean isForce) {
        if (StringUtils.isBlank(dirPath)) {
            throw new ArgsException("dirPath不能为空！");
        }
        Map<String, Object> body = new HashMap<>(2);
        body.put("dirPath", dirPath);
        if (isForce != null) {
            body.put("isForce", isForce.toString());
        }
        Map<String, String> result = execution.post(FilePathConstant.FILE_MKDIR_PATH, username, body,
                new TypeReference<ResponseResult<Map<String, String>>>() {
                });
        if (CollectionUtil.isEmpty(result)) {
            return null;
        }
        return result.get("dirPath");
    }

    /**
     * 新建文件夹，默认不强制新建
     *
     * @param username 用户名
     * @param dirPath  文件路径
     * @return 新建后的文件路径
     */
    public String mkdir(String username, String dirPath) {
        return mkdir(username, dirPath, null);
    }

    /**
     * 创建文件
     *
     * @param username 用户名
     * @param filePath 文件路径
     * @return 新的文件路径
     */
    public String mkFile(String username, String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new ArgsException("filePath不能为空！");
        }
        Map<String, Object> body = new HashMap<>(1);
        body.put("filePath", filePath);
        Map<String, String> result = execution.post(FilePathConstant.FILE_MKFILE_PATH, username, body,
                new TypeReference<ResponseResult<Map<String, String>>>() {
                });
        if (CollectionUtil.isEmpty(result)) {
            return null;
        }
        return result.get("dirPath");
    }

    /**
     * 上传文件
     * <p>
     * 如果isCover为空或者为false，源文件目录下存在相同文件则会报错
     * </p>
     *
     * @param username   用户名
     * @param is         文件流
     * @param fileName   文件名称
     * @param uploadPath 上传路径
     * @param isCover    是否覆盖（非必填，默认：false）
     * @param fileConf   密级（只有开启了密级才需要此参数，可以是密级的中文名，也可以是密级的中文名、英文名、或者密级的key）
     */
    public void uploadFile(String username, InputStream is, String fileName, String uploadPath, Boolean isCover, String fileConf) {
        if (is == null) {
            throw new ArgsException("is是必填参数");
        }
        if (StringUtils.isBlank(uploadPath)) {
            throw new ArgsException("uploadPath是必填参数");
        }
        Map<String, Object> body = new HashMap<>(3);

        if (isCover != null) {
            body.put("isCover", isCover);
        }
        if (StringUtils.isNotBlank(fileConf)) {
            body.put("fileConf", fileConf);
        }
        body.put("uploadPath", uploadPath);
        ResponseResult<Object> result = execution.getJhApiClient().upload(
                FilePathConstant.FILE_UPLOAD_PATH,
                "file",
                fileName,
                is,
                execution.getHeaders(username, false),
                body,
                new TypeReference<ResponseResult<Object>>() {
                });
        if (StringUtils.equals(result.getResult(), CommonConstant.FAILED)) {
            throw new ServiceException(FilePathConstant.FILE_UPLOAD_PATH, result.getCode(), result.getMessage());
        }
    }

    /**
     * 上传文件
     * <p>
     * 如果isCover为空或者为false，源文件目录下存在相同文件则会报错
     * </p>
     *
     * @param username   用户名
     * @param is         文件流
     * @param fileName   文件名称
     * @param uploadPath 上传路径
     * @param isCover    是否覆盖（非必填，默认：false）
     */
    public void uploadFile(String username, InputStream is, String fileName, String uploadPath, Boolean isCover) {
        uploadFile(username, is, fileName, uploadPath, isCover, null);
    }


    /**
     * 上传文件（不覆盖源文件）
     * <p>
     * 源文件目录下存在相同文件则会报错
     * </p>
     *
     * @param username   用户名
     * @param is         文件流
     * @param fileName   文件名
     * @param uploadPath 上传路径
     */
    public void uploadFile(String username, InputStream is, String fileName, String uploadPath) {
        uploadFile(username, is, fileName, uploadPath, null);
    }

    /**
     * 上传一个本地的路径
     *
     * @param username   用户名
     * @param path       本地文件路径
     * @param fileName   文件名
     * @param uploadPath 上传路径，服务器路径
     * @param isCover    是否覆盖（非必填，默认：false）
     */
    public void uploadFile(String username, String path, String fileName, String uploadPath, Boolean isCover)
            throws FileNotFoundException {
        if (StringUtils.isBlank(path)) {
            throw new ArgsException("path是必填参数");
        }
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        uploadFile(username, fileInputStream, fileName, uploadPath, isCover);
    }

    /**
     * 上传一个本地的路径（不覆盖源文件）
     * <p>
     * 源文件目录下存在相同文件则会报错
     * </p>
     *
     * @param username   用户名
     * @param path       本地文件路径
     * @param fileName   文件名
     * @param uploadPath 上传路径，服务器路径
     */
    public void uploadFile(String username, String path, String fileName, String uploadPath)
            throws FileNotFoundException {
        uploadFile(username, path, fileName, uploadPath, null);
    }

    /**
     * 上传一个本地的路径
     *
     * @param username   用户名
     * @param path       本地文件路径
     * @param uploadPath 上传路径，服务器路径
     * @param isCover    是否覆盖（非必填，默认：false）
     */
    public void uploadFile(String username, String path, String uploadPath, Boolean isCover)
            throws FileNotFoundException {
        File file = new File(path);
        uploadFile(username, path, file.getName(), uploadPath, isCover);
    }

    /**
     * 上传一个本地的路径（不覆盖源文件）
     *
     * @param username   用户名
     * @param path       本地文件路径
     * @param uploadPath 上传路径，服务器路径
     */
    public void uploadFile(String username, String path, String uploadPath) throws FileNotFoundException {
        File file = new File(path);
        uploadFile(username, path, file.getName(), uploadPath, null);
    }

    /**
     * 获取文件下载地址
     *
     * @param username 用户名
     * @param filePath 文件路径
     * @return 文件地址信息
     */
    public String getFileDownloadUrl(String username, String filePath) {
        return getFileDownloadUrl(username, filePath, null);
    }

    /**
     * 获取文件下载地址
     *
     * @param username      用户名
     * @param filePath      文件路径
     * @param forceDownload 是否强制下载，打开密级之后未标密的文件无法下载，可以通过设置当前参数为true来强制下载，默认：false
     * @return 文件地址信息
     */
    public String getFileDownloadUrl(String username, String filePath, Boolean forceDownload) {
        if (StringUtils.isBlank(filePath)) {
            throw new ArgsException("filePath不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("filePath", filePath);
        params.put("forceDownload", forceDownload);
        String path = JHApiClient.getUrl(FilePathConstant.FILE_DOWNLOAD_PATH, params);
        Map<String, String> downloadInfo = execution.get(path, username,
                new TypeReference<ResponseResult<Map<String, String>>>() {
                });
        if (CollectionUtil.isEmpty(downloadInfo)) {
            return null;
        }
        return downloadInfo.get("url");
    }

    /**
     * 获取文件输入流
     *
     * @param username 用户名
     * @param filePath 文件路径
     * @return 文件流
     * @throws IOException
     */
    public InputStream getFileInputStream(String username, String filePath) throws IOException {
        return getFileInputStream(username, filePath, null);
    }

    /**
     * 获取文件输入流
     *
     * @param username      用户名
     * @param filePath      文件路径
     * @param forceDownload 是否强制下载，打开密级之后未标密的文件无法下载，可以通过设置当前参数为true来强制下载，默认：false
     * @return 文件流
     * @throws IOException
     */
    public InputStream getFileInputStream(String username, String filePath, Boolean forceDownload) throws IOException {
        String fileUrl = getFileDownloadUrl(username, filePath, forceDownload);
        return execution.getJhApiClient().getApiHttpClient().get(fileUrl, null);
    }

    /**
     * 文件压缩
     *
     * @param username       用户名
     * @param sourceDirName  源文件目录
     * @param targetFilePath 目标文件路径
     * @param compressType   压缩类型 （未使用以后扩展）
     */
    public void compress(String username, String sourceDirName, String targetFilePath, String compressType) {
        if (StringUtils.isBlank(sourceDirName)) {
            throw new ArgsException("sourceDirName不能为空！");
        }
        if (StringUtils.isBlank(targetFilePath)) {
            throw new ArgsException("targetFilePath不能为空！");
        }
        Map<String, Object> params = new HashMap<>(3);
        params.put("sourceDirName", sourceDirName);
        params.put("targetFilePath", targetFilePath);
        if (StringUtils.isNotBlank(compressType)) {
            params.put("compressType", compressType);
        }
        String path = JHApiClient.getUrl(FilePathConstant.FILE_COMPRESS_PATH, params);
        execution.post(path, username);
    }

    /**
     * 文件压缩
     *
     * @param username       用户名
     * @param sourceDirName  源文件目录(多个文件适用英文逗号隔开)
     * @param targetFilePath 目标文件路径
     */
    public void compress(String username, String sourceDirName, String targetFilePath) {
        compress(username, sourceDirName, targetFilePath, null);
    }

    /**
     * 解压文件
     *
     * @param username       用户名
     * @param sourceFilePath 源文件路径
     * @param targetDirPath  目标文件路径
     * @param isCover        是否覆盖
     * @param password       密码
     * @param compressType   压缩类型 （未使用以后扩展）
     */
    public void uncompress(String username, String sourceFilePath, String targetDirPath, Boolean isCover,
                           String password, String compressType) {
        if (StringUtils.isBlank(sourceFilePath)) {
            throw new ArgsException("sourceFilePath不能为空！");
        }
        if (StringUtils.isBlank(targetDirPath)) {
            throw new ArgsException("targetDirPath不能为空！");
        }
        Map<String, Object> params = new HashMap<>(5);
        params.put("zipFile", sourceFilePath);
        params.put("targetDir", targetDirPath);
        if (isCover != null) {
            params.put("isCoverZipDir", isCover);
        }
        if (StringUtils.isNotBlank(password)) {
            params.put("password", password);
        }
        if (StringUtils.isNotBlank(compressType)) {
            params.put("compressType", compressType);
        }
        String path = JHApiClient.getUrl(FilePathConstant.FILE_UNCOMPRESS_PATH, params);
        execution.post(path, username);
    }

    /**
     * 解压文件
     *
     * @param username       用户名
     * @param sourceFilePath 源文件路径
     * @param targetDirPath  目标文件路径
     * @param isCover        是否覆盖
     * @param password       密码
     */
    public void uncompress(String username, String sourceFilePath, String targetDirPath, Boolean isCover,
                           String password) {
        uncompress(username, sourceFilePath, targetDirPath, isCover, password, null);
    }

    /**
     * 解压文件
     *
     * @param username       用户名
     * @param sourceFilePath 源文件路径
     * @param targetDirPath  目标文件路径
     * @param isCover        是否覆盖
     */
    public void uncompress(String username, String sourceFilePath, String targetDirPath, Boolean isCover) {
        uncompress(username, sourceFilePath, targetDirPath, isCover, null);
    }

    /**
     * 解压文件
     *
     * @param username       用户名
     * @param sourceFilePath 源文件路径
     * @param targetDirPath  目标文件路径
     */
    public void uncompress(String username, String sourceFilePath, String targetDirPath) {
        uncompress(username, sourceFilePath, targetDirPath, null);
    }
}
