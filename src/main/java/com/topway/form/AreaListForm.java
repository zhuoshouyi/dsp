package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/20.
 */
@Data
public class AreaListForm {

    /**
     * 用户填写的搜索信息,可以搜索小区名称或者小区编码
     */
    @NotEmpty(message = "用户搜索信息,必填")
    private String keyword;

    /**
     * 查看第几页
     */
    private int pageNo = -1;

    /**
     * 每一页显示的个数
     */
    private int pageSize = -1;
}
