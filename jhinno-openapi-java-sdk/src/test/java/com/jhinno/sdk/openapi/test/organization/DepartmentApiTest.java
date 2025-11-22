package com.jhinno.sdk.openapi.test.organization;

import com.jhinno.sdk.openapi.api.organization.AddUpdateDepartment;
import com.jhinno.sdk.openapi.api.organization.JHDepartmentApiExecution;
import com.jhinno.sdk.openapi.test.JHClientConfig;
import org.junit.Test;

/**
 * 组织接口相关单元测试
 *
 * @author yanlongqi
 * @date 2024/2/6 18:09
 */
public class DepartmentApiTest {

    private static final JHDepartmentApiExecution execution = JHClientConfig.API_EXECUTION_MANAGE.getApiExecution(JHDepartmentApiExecution.class);


    /**
     * 测试获取部门
     */
    @Test
    public void testGetDepartment() {
        System.out.println(execution.getDepartmentList());
    }

    /**
     * 测试添加部门
     */
    @Test
    public void testAddDepartment() {
        AddUpdateDepartment addUpdateDepartment = new AddUpdateDepartment();
        addUpdateDepartment.setDepName("test1");
        addUpdateDepartment.setDepNameCN("测试部门1");
        addUpdateDepartment.setParentDepName("defaultDep");
        execution.addDepartment(addUpdateDepartment);
    }

    /**
     * 测试修改部门
     */
    @Test
    public void testUpdateDepartment() {
        AddUpdateDepartment addUpdateDepartment = new AddUpdateDepartment();
        addUpdateDepartment.setDepName("test2");
        addUpdateDepartment.setDepNameCN("测试部门2111");
        addUpdateDepartment.setParentDepName("defaultDep");
        execution.updateDepartment(addUpdateDepartment);
    }

    /**
     * 测试删除部门
     */
    @Test
    public void testDeleteDepartment() {
        execution.deleteDepartment("test1");
    }
}
