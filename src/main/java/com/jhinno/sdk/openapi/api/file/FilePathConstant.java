package com.jhinno.sdk.openapi.api.file;

/**
 * 文件请求路径
 *
 * @author yanlongqi
 * @date 2024/2/4 18:59
 */
public class FilePathConstant {


    /**
     * 重命名文件
     */
    public static final String FILE_RENAME_PATH = "/ws/api/files/rename";


    /**
     * 删除文件
     */
    public static final String FILE_DELETE_PATH = "/ws/api/files/delete";


    /**
     * 拷贝文件
     */
    public static final String FILE_COPY_PATH = "/ws/api/files/copy";


    /**
     * 获取文件列表
     */
    public static final String FILE_LIST_PATH = "/ws/api/files";

    /**
     * 创建文件夹
     */
    public static final String FILE_MKDIR_PATH = "/ws/api/files/mkdir";

    /**
     * 创建文件
     */
    public static final String FILE_MKFILE_PATH = "/ws/api/files/mkfile";

    /**
     * 文件上传
     */
    public static final String FILE_UPLOAD_PATH = "/ws/api/files/upload";


    /**
     * 文件下载
     */
    public static final String FILE_DOWNLOAD_PATH = "/ws/api/files/download";


    /**
     * 压缩文件
     */
    public static final String FILE_COMPRESS_PATH = "/ws/api/files/compress";

    /**
     * 解压文件
     */
    public static final String FILE_UNCOMPRESS_PATH = "/ws/api/files/uncompress";

    /**
     * 浏览文件
     */
    public static final String FILE_BROWSE_PATH = "/ws/api/browseFile";
}
