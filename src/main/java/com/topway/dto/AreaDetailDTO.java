package com.topway.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class AreaDetailDTO<T> {

    /**
     * 小区基础信息
     */
    private T basicInfo;

    /**
     * 小区标签展示
     */
    private List<String> label;

    /**
     * 业务在线情况
     */
    private T business;

    /**
     * 终端月度发展情况
     */
    private T monthlyDevelopment;

    /**
     * 物业信息
     */
    private T propertyInfo;

}
