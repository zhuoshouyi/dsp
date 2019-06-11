package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/15.
 */
@Entity
@Data
public class UserRole {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 工单用户Id. */
    private String userId = "";

    /** 用户角色. */
    private String userRole = "";

    /** 用户姓名. */
    private String userName = "";

    /** 门户Id. */
    private String openId = "";

    /** 运营商Id. */
    private String spcodeId = "";

    /** 分公司Id. */
    private String businessOfficeId = "";

    /** 网格Id. */
    private String serviceGridId = "";
}
