package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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
    private Integer isSmartTV = -1;

    /**
     * 是否自住
     */
    private Integer isRenting = -1;

    /**
     * 用户类型
     */
    private String customType = "";

    /**
     * 有无小孩
     */
    private Integer isHaveChildren = -1;

    /**
     * 有无老人
     */
    private Integer isHaveElderly = -1;

    /**
     * 是否用其他运营商产品
     */
    private Integer isUseOtherProduct = -1;

    /**
     * 收视兴趣(可多选)
     */
    private List<String> watchInterest = new ArrayList<>();

    /**
     * 用户类型(可多选)
     */
    private List<String> customCharacteristic = new ArrayList<>();

    /**
     * 沟通类型
     */
    private String communicationType = "";

    /**
     * 客户自定义标签
     */
    private List<String> customFileds = new ArrayList<>();
}
