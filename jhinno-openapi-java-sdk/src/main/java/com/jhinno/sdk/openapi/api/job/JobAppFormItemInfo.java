package com.jhinno.sdk.openapi.api.job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class JobAppFormItemInfo {

    /**
     * 参数描述
     */
    private String valueDesc;

    /**
     * 参数key
     */
    private String valueKey;

    /**
     * 参数正则
     */
    private String validateRegXp;

    /**
     * 参数类型
     * 从参数范围内多选（多个值用英文逗号隔开）还是单选,
     * string、singleSelect、multiSelect
     * <ol>
     *     <li>选择: select</li>
     *     <li>开关: switch</li>
     *     <li>值: text</li>
     *     <li>文件: upload</li>
     * </ol>
     */

    private String valueType;

    /**
     * 参数范围
     */
    private List<ParamsValueScope> valueScope;

    /**
     * 是否必填
     */
    private boolean required;

    /**
     * 默认值
     */
    private String valueDefault;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParamsValueScope {
        private String name;
        private String value;
    }
}
