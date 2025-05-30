package com.jhinno.sdk.openapi.test.file;

import com.jhinno.sdk.openapi.api.file.FileInfo;
import com.jhinno.sdk.openapi.api.file.JHFileApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
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

    private static final JHFileApiExecution execution = JHClientConfig.API_EXECUTRON_MANAGE
            .getApiExecution(JHFileApiExecution.class);

    /**
     * 测试重命名文件或文件夹
     */
    @Test
    public void testRenameFile() {
        execution.renameFile("lqyan", "/apps/JHDP/lqyan/temp/PSUserService.class", "aaa.class");
    }

    /**
     * 测试删除文件
     */
    @Test
    public void testDeleteFile() {
        execution.deleteFile("lqyan", "/apps/JHDP/lqyan/temp/aaa.class");
    }

    /**
     * 测试文件拷贝
     */
    @Test
    public void testCopyFile() {
        execution.copyFile("lqyan", "/apps/JHDP/lqyan/PSUserService.class", "/apps/JHDP/lqyan/temp");
    }

    /**
     * 测试获取文件列表
     */
    @Test
    public void testGetFileList() {
        List<FileInfo> fileList = execution.getFileList("jhadmin", "$HOME");
        System.out.println(fileList);
    }

    /**
     * 测试创建文夹
     */
    @Test
    public void testMkdir() {
        System.out.println(execution.mkdir("jhadmin", "$HOMEtest1", true));
    }

    /**
     * 新建文件夹不强制新建
     */
    @Test
    public void testMkdirNoForce() {
        System.out.println(execution.mkdir("lqyan", "/apps/JHDP/lqyan/temp/bbb/ddd"));
    }

    /**
     * 测试新建文件（接口执行失败）
     */
    @Test
    public void testMkFile() {
        System.out.println(execution.mkFile("lqyan", "/apps/JHDP/lqyan/temp/ddd.txt"));
    }

    /**
     * 测试文件上传 (是否覆盖存在问题)
     */
    @Test
    public void testUploadFile() throws IOException {
        File file = new File("C:\\Users\\yanlongqi\\Desktop\\LdapAdminv1830.exe");
        FileInputStream fileInputStream = new FileInputStream(file);
        execution.uploadFile("jhadmin", fileInputStream, file.getName(), "$HOME", true);
    }

    /**
     * 测试上传文件，（不覆盖源文件，如果isCover是true，上传后的文件有数字下标）
     */
    @Test
    public void testUploadFileNoCover() throws IOException {
        File file = new File("C:\\Users\\yanlongqi\\Desktop\\Hash.exe");
        FileInputStream fileInputStream = new FileInputStream(file);
        execution.uploadFile("lqyan", fileInputStream, file.getName(), "$HOME/temp");
    }


    /**
     * 测试上传文件，开启密级的情况
     */
    @Test
    public void testUploadFileConf() throws IOException {
        File file = new File("C:\\Users\\yanlongqi\\Desktop\\Hash.exe");
        FileInputStream fileInputStream = new FileInputStream(file);
        execution.uploadFile("lqyan", fileInputStream, file.getName(), "$HOME/temp111", false,"public");
    }

    /**
     * 测试获取文件下载的链接
     */
    @Test
    public void testGetFileDownloadUrl() {
        System.out.println(execution.getFileDownloadUrl("jhadmin", "$HOME/aa2a.sh"));
    }

    @Test
    public void testGetFileStream() throws IOException {
        InputStream in = execution.getFileInputStream("jhadmin", "$HOME/aaa.sh");
        byte[] bytes = new byte[1024];
        while (in.read(bytes) != -1) {
            System.out.println(new String(bytes));
        }
    }

    @Test
    public void testCompress() {
        execution.compress("jhadmin", "$HOME/temp", "$HOME/temp.zip");
    }

    @Test
    public void testUncompress() {
        execution.uncompress("jhadmin", "$HOME/temp.zip", "$HOME/test");
    }
}
