package com.jhinno.sdk.openapi.test.data;

import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

import java.util.Arrays;

/**
 * 作业数据区相关单元测试类
 *
 * @author yanlongqi
 * @date 2024/2/4 17:10
 */
public class DataApiTest {

    public static final JHDataApiExecution execution = new JHDataApiExecution(JHClientConfig.client);

    /**
     * 测试获取作业数据区目录列表
     */
    @Test
    public void testGetSpoolers() {
        System.out.println(execution.getSpoolersData("jhadmin"));
    }


    /**
     * 测试根作业id获取作业数据区列表
     */
    @Test
    public void testGetSpoolersDataById() {
        System.out.println(execution.getSpoolersDataById("jhadmin", "5909"));
    }


    /**
     * 测试根作业id列表获取作业数据区列表
     */
    @Test
    public void testGetSpoolersDataByIds() {
        System.out.println(execution.getSpoolersDataByIds("jhadmin", Arrays.asList("6799", "6686")));
    }

    /**
     * 测试通过作业数据区名称获作业数据列表
     */
    @Test
    public void testGetSpoolersDataByName() {
        System.out.println(execution.getSpoolersByName("jhadmin", "common_sub__t1_aaa.sh_20240206103137"));
    }


}
