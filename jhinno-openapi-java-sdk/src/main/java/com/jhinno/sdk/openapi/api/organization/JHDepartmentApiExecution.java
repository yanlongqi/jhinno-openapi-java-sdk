package com.jhinno.sdk.openapi.api.organization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jhinno.sdk.openapi.ArgsException;
import com.jhinno.sdk.openapi.JHApiExecution;
import com.jhinno.sdk.openapi.api.JHRequestExecution;
import com.jhinno.sdk.openapi.api.ResponseResult;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
@NoArgsConstructor
public class JHDepartmentApiExecution implements JHApiExecution {

    private JHRequestExecution execution;

    public void init(JHRequestExecution execution) {
        this.execution = execution;
    }

    /**
     * 查询用户列表
     *
     * @param username 用户名
     * @return 用户列表
     */
    public List<Map<String, Object>> getDepartmentList(String username) {
        return execution.get(DepartmentPathConstant.DEPARTMENT_PATH, username,
                new TypeReference<ResponseResult<List<Map<String, Object>>>>() {
                });
    }

    /**
     * 添加部门
     *
     * @param username       用户名
     * @param departmentInfo 部门信息
     */
    public void addDepartment(String username, AddUpdateDepartment departmentInfo) {
        execution.post(DepartmentPathConstant.DEPARTMENT_PATH, username, departmentInfo);
    }

    /**
     * 修改部门信息
     *
     * @param username       用户名
     * @param departmentInfo 部门信息
     */
    public void updateDepartment(String username, AddUpdateDepartment departmentInfo) {
        if (StringUtils.isBlank(departmentInfo.getDepName())) {
            throw new ArgsException("departmentInfo中的depName不能为空！");
        }
        String path = DepartmentPathConstant.DEPARTMENT_NAME_PATH.replace("{depName}", departmentInfo.getDepName());
        execution.put(path, username, departmentInfo);
    }

    /**
     * 删除部门信息
     *
     * @param username       用户名
     * @param departmentName 部门名称
     */
    public void deleteDepartment(String username, String departmentName) {
        if (StringUtils.isBlank(departmentName)) {
            throw new ArgsException("departmentName不能为空！");
        }
        String path = DepartmentPathConstant.DEPARTMENT_NAME_PATH.replace("{depName}", departmentName);
        execution.delete(path, username);
    }
}
