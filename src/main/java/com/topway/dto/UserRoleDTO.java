package com.topway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/12.
 */
@Data
public class UserRoleDTO {

    /** 工单用户Id. */
    private String userId = "";

    /** 用户角色. */
    private String userRole = "";

    /** 用户姓名. */
    private String userName = "";

    /** 门户Id. */
    private String openId = "";

    /** 运营商Id. */
    private List<String> spcodeId = new ArrayList<>();

    /** 分公司Id. */
    private List<String> businessOfficeId = new ArrayList<>();

    /** 网格Id. */
    private List<String> serviceGridId = new ArrayList<>();
}
