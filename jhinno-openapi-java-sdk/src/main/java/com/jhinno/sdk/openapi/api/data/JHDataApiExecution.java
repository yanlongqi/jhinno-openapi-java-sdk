package com.jhinno.sdk.openapi.api.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.*;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;
import com.jhinno.sdk.openapi.utils.CollectionUtil;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件接口执行器
 *
 * @author yanlongqi
 * @date 2024/2/4 17:09
 */
@NoArgsConstructor
public class JHDataApiExecution extends JHApiExecutionAbstract {

    /**
     * 根据用户scope查询数据目录列表
     *
     * @param username 用户名
     * @return 用户数据目录列表
     */
    public List<SpoolerDataInfo> getSpoolersData(String username) {
        return execution.get(DataPathConstant.DATA_SPOOLERS_PATH, username,
                new TypeReference<ResponseResult<List<SpoolerDataInfo>>>() {
                });
    }

    /**
     * 根据作业id查作业数据目录信息
     *
     * @param username 用户名
     * @param jobId    作业id
     * @return 作业目录信息
     */
    public SpoolerDataInfo getSpoolersDataById(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = DataPathConstant.DATA_SPOOLER_JOB_ID_PATH.replace("{jobId}", jobId);
        List<SpoolerDataInfo> list = execution.get(path, username,
                new TypeReference<ResponseResult<List<SpoolerDataInfo>>>() {
                });
        if (CollectionUtil.isEmpty(list)) {
            throw new ServiceException(path, 500, "作业数据目录信息为空！");
        }
        return list.get(0);
    }

    /**
     * 根据作业id集合查询数据目录列表
     *
     * @param username 用户名
     * @param jobIds   作业id列表
     * @return 用户数据目录列表
     */
    public List<SpoolerDataInfo> getSpoolersDataByIds(String username, List<String> jobIds) {
        if (CollectionUtil.isEmpty(jobIds)) {
            throw new ArgsException("jobIds不能为空");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("jobIds", String.join(CommonConstant.NORMAL_CHARACTER_COMMA, jobIds));
        String path = JHApiClient.getUrl(DataPathConstant.DATA_SPOOLERS_LIST_PATH, params);
        return execution.get(path, username, new TypeReference<ResponseResult<List<SpoolerDataInfo>>>() {
        });
    }

    /**
     * 根据数据目录名称查询数据目录列表 (产品接口有问题)
     *
     * @param username 用户名
     * @param dataName 数据目录名称
     * @return 作业目录信息
     */
    public SpoolerDataInfo getSpoolersByName(String username, String dataName) {
        if (StringUtils.isBlank(dataName)) {
            throw new ArgsException("dataName不能为空！");
        }
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", dataName);
        String path = JHApiClient.getUrl(DataPathConstant.DATA_SPOOLERS_NAME_PATH, params);
        List<SpoolerDataInfo> list = execution.get(path, username,
                new TypeReference<ResponseResult<List<SpoolerDataInfo>>>() {
                });
        if (CollectionUtil.isEmpty(list)) {
            throw new ServiceException(path, 500, "作业数据目录信息为空！");
        }
        return list.get(0);
    }

    /**
     * 立即删除作业数据目录
     *
     * @param username 用户名
     * @param jobId    作业id
     */
    public void deleteSpoolerData(String username, String jobId) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        String path = DataPathConstant.DATA_SPOOLERS_DELETE_ID_PATH.replace("{id}", jobId);
        execution.get(path, username, new TypeReference<ResponseResult<Object>>() {
        });
    }

    /**
     * 设置用户数据目录的过期时间，也给以通过设置过期时间来删除用户数据区
     *
     * @param username       用户名
     * @param jobId          作业id
     * @param expirationTime 过期时间
     */
    public void purgeSpooler(String username, String jobId, Date expirationTime) {
        if (StringUtils.isBlank(jobId)) {
            throw new ArgsException("jobId不能为空！");
        }
        if (expirationTime == null) {
            throw new ArgsException("expirationTime不能为空！");
        }
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", jobId);
        params.put("expiration_time", expirationTime);
        String path = JHApiClient.getUrl(DataPathConstant.DATA_SPOOLERS_PURGE_PATH, params);
        execution.get(path, username, new TypeReference<ResponseResult<Object>>() {
        });
    }

}
