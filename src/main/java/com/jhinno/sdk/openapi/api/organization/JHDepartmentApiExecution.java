package com.jhinno.sdk.openapi.api.organization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.api.JHApiExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import com.jhinno.sdk.openapi.client.JHApiClient;

import java.util.List;
import java.util.Map;

/**
 * 部门相关接口执行器
 * <p>
 * 注：要调用以下接口，传入的username必须有user_manager的权限
 *
 * @author yanlongqi
 * @date 2024/2/6 17:37
 */
public class JHDepartmentApiExecution extends JHApiExecution {

    /**
     * 获取一个执行器的实例
     *
     * @param jhApiClient 请求的客户端
     */
    public JHDepartmentApiExecution(JHApiClient jhApiClient) {
        super(jhApiClient);
    }


    /**
     * 查询用户列表
     *
     * @param username 用户名
     * @return 用户列表
     */
    public List<Map<String, Object>> getDepartmentList(String username) {
        return get(DepartmentPathConstant.DEPARTMENT_PATH, username, new TypeReference<ResponseResult<List<Map<String, Object>>>>() {
        });
    }


    /**
     * 添加部门
     *
     * @param username       用户名
     * @param departmentInfo 部门信息
     */
    public void addDepartment(String username, AddUpdateDepartment departmentInfo) {
        String path = DepartmentPathConstant.DEPARTMENT_NAME_PATH.replace("{depName}", departmentInfo.getDepName());
        post(path, username, departmentInfo);
    }


    /**
     * 修改部门信息
     *
     * @param username       用户名
     * @param departmentInfo 部门信息
     */
    public void updateDepartment(String username, AddUpdateDepartment departmentInfo) {
        String path = DepartmentPathConstant.DEPARTMENT_NAME_PATH.replace("{depName}", departmentInfo.getDepName());
        put(path, username, departmentInfo);
    }


    /**
     * 删除部门信息
     *
     * @param username       用户名
     * @param departmentName 部门名称
     */
    public void deleteDepartment(String username, String departmentName) {
        String path = DepartmentPathConstant.DEPARTMENT_NAME_PATH.replace("{depName}", departmentName);
        delete(path, username);
    }
}
