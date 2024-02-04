package com.jhinno.sdk.openapi.test.data;

import com.jhinno.sdk.openapi.api.data.JHDataApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author yanlongqi
 * @date 2024/2/4 17:10
 */
public class DataApiTest {

    public static final JHDataApiExecution execution = new JHDataApiExecution(JHClientConfig.client);

    @Test
    public void testGetSpoolers() {
        System.out.println(execution.getSpoolersData("jhadmin"));
    }


    @Test
    public void testGetSpoolersDataById() {
        System.out.println(execution.getSpoolersDataById("jhadmin", "5909"));
    }


    @Test
    public void testGetSpoolersDataByIds() {
        System.out.println(execution.getSpoolersDataByIds("jhadmin", Arrays.asList("6799", "6686")));
    }

    @Test
    public void testGetSpoolersDataByName() {
        System.out.println(execution.getSpoolersByName("jhadmin", "fluent__N_2d_t1_fluent-test_20220722172408"));
    }


}
