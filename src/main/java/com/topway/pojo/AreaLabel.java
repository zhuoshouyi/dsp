package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/23.
 */
@Entity
@Data
public class AreaLabel {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 小区编码. */
    private String areaId;

    /** 创建日期. */
    private String createTime;

    /** 楼盘属性. */
    private String buildAttrbute;

    /** 小区自主比例. */
    private String areaLiveProportion;

    /** 是否合同小区. */
    private String isContractArea;

    /** 是否可以进场. */
    private String isPermittedAdmission;

    /** 是否竞争小区. */
    private String isCompeteArea;

    /** 是否正规覆盖. */
    private String isRegularCover;

    /** 网络覆盖属性. */
    private String networkCoverageProperties;

    /** 小区住户稳定性. */
    private String isStabilityLiver;

    /** 客户自定义标签. */
    private String customFields;
}
