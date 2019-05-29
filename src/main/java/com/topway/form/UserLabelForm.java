package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/29.
 */
@Data
public class UserLabelForm {

    /**
     * customerId
     */
    @NotEmpty(message = "customerId,必填")
    private String customerId;

    /**
     * 是否智能电视
     */
    private String isSmartTV;

    /**
     * 是否自住
     */
    private String isRenting;

    /**
     * 用户类型
     */
    private String customType;

    /**
     * 有无小孩
     */
    private String isHaveChildren;

    /**
     * 有无老人
     */
    private String isHaveElderly;

    /**
     * 是否用其他运营商产品
     */
    private String isUseOtherProduct;

    /**
     * 收视兴趣(可多选)
     */
    private String watchInterest;

    /**
     * 用户类型(可多选)
     */
    private String customCharacteristic;

    /**
     * 沟通类型
     */
    private String communicationType;

    /**
     * 客户自定义标签
     */
    private String customFileds;
}
