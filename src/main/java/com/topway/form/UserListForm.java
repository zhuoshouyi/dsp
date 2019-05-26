package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class UserListForm {

    /**
     * 用户填写的搜索信息
     */
    @NotEmpty(message = "搜索信息,必填")
    private String keyword;

    /**
     * 用户填写的搜索方式,支持资源号、电话、客户名称、客户编码的模糊搜索
     * 对应字段:
     *   资源号    deviceNo
     *   电话      phone
     *   客户名称  customerName
     *   客户编码  customerId
     */
    @NotEmpty(message = "搜索方式,必填")
    private String searchType;

    /**
     * 查看的页数
     */
    private Integer pageNo = 0;

    /**
     * 每页显示的个数
     */
    private Integer pageSize = 5;

}
