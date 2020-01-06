package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/6/5.
 */
@Data
public class RankListForm {

    /**
     * 排行范围: 本分公司  本分部
     */
    @NotEmpty(message = "请选择排行范围")
    private String branchOrStation;


    /**
     * 排行维度: 个人维度  网格维度
     */
    @NotEmpty(message = "请选择排行维度")
    private String gridOrPerson;

    /**
     * 分公司
     */
    @NotEmpty(message = "前端未传输分公司信息")
    private String branch;

    /**
     * 分部
     */
    @NotEmpty(message = "前端未传输分部信息")
    private String station;



}
