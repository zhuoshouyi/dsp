package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/19.
 */
@Entity
@Data
public class TestArea {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 维护站名称. */
    private String fk75638e31;

    /** 小区名称. */
    private String fk999cd340;

    /** 小区代码. */
    private String fka9350c89;

    /** 网格编码. */
    private String fk89cc3f4d;

    /** 网格名称. */
    private String fk3c052170;

    /** 城中村标识. */
    private String fkfeffc5ea;

    /** 缴费类型. */
    private String fk560a959b;



}

