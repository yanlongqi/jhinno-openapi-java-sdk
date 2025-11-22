package com.jhinno.sdk.openapi.test.file;

import com.jhinno.sdk.openapi.api.file.Confidential;
import com.jhinno.sdk.openapi.api.file.FileInfo;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import com.jhinno.sdk.openapi.utils.JsonUtil;
import org.junit.Test;

import java.io.InputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yanlongqi
 * @date 2024/2/5 10:20
 */
public class FileApiTest {

    private static final JHFileApiExecution execution = JHClientConfig.API_EXECUTION_MANAGE.getApiExecution(JHFileApiExecution.class);

    /**
     * 测试重命名文件或文件夹
     */
    @Test
    public void testRenameFile() {
        execution.renameFile("$HOME/test.sh", "test1.sh");
    }

    /**
     * 测试删除文件
     */
    @Test
    public void testDeleteFile() {
        execution.deleteFile("$HOME/test1/test.sh");
    }

    /**
     * 测试文件拷贝
     */
    @Test
    public void testCopyFile() {
        execution.copyFile("$HOME/test.sh", "$HOME/test1");
    }

    /**
     * 测试获取文件列表
     */
    @Test
    public void testGetFileList() {
        List<FileInfo> fileList = execution.getFileList("$HOME");
        System.out.println(fileList);
    }

    /**
     * 测试创建文夹
     */
    @Test
    public void testMkdir() {
        System.out.println(execution.mkdir("$HOMEtest1", true));
    }

    /**
     * 新建文件夹不强制新建
     */
    @Test
    public void testMkdirNoForce() {
        System.out.println(execution.mkdir("$HOME/temp/bbb/ddd"));
    }

    /**
     * 测试新建文件（接口执行失败）
     */
    @Test
    public void testMkFile() {
        System.out.println(execution.mkFile("$HOME/temp/ddd.txt"));
    }

    /**
     * 测试文件上传 (是否覆盖存在问题)
     */
    @Test
    public void testUploadFile() throws IOException {
        File file = new File("D:\\Program Files\\Java\\apache-maven-3.9.11\\conf\\settings.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        execution.uploadFile(fileInputStream, file.getName(), "$HOME", true);
    }

    /**
     * 测试上传文件，（不覆盖源文件，如果isCover是true，上传后的文件有数字下标）
     */
    @Test
    public void testUploadFileNoCover() throws IOException {
        File file = new File("D:\\Program Files\\Java\\apache-maven-3.9.11\\conf\\settings.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        execution.uploadFile(fileInputStream, file.getName(), "$HOME/temp");
    }


    /**
     * 测试上传文件，开启密级的情况
     */
    @Test
    public void testUploadFileConf() throws IOException {
        File file = new File("D:\\Program Files\\Java\\apache-maven-3.9.11\\conf\\settings.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        execution.uploadFile(fileInputStream, file.getName(), "$HOME/temp111", false, "public");
    }

    /**
     * 测试获取文件下载的链接
     */
    @Test
    public void testGetFileDownloadUrl() {
        System.out.println(execution.getFileDownloadUrl("$HOME/test.sh"));
    }

    @Test
    public void testGetFileStream() throws IOException {
        InputStream in = execution.getFileInputStream("$HOME/test.sh");
        byte[] bytes = new byte[1024];
        while (in.read(bytes) != -1) {
            System.out.println(new String(bytes));
        }
    }

    @Test
    public void testCompress() {
        execution.compress("$HOME/temp", "$HOME/temp.zip");
    }

    @Test
    public void testUncompress() {
        execution.uncompress("$HOME/temp.zip", "$HOME/test");
    }

    @Test
    public void testGetConfList() {
        List<Confidential> confList = execution.getConfList("yanlongqi");
        System.out.println(JsonUtil.objectToString(confList));
    }

    @Test
    public void testMarkConf() {
        execution.markConf("999999998", "/home/yanlongqi/test.sh");
    }
}
