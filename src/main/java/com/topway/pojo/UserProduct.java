package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/26.
 */
@Entity
@Data
public class UserProduct {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 用户编码 fk451a778a. */
    private String userId;

    /** 产品编码 fkb7939445. */
    private String productId;

    /** 产品名称 fk795b247c. */
    private String productName;

    /** 订购时间 fk477a0abf. */
    private String orderTime;

    /** 订购状态 fkb8be2a22. */
    private String orderStatus;

    /** 计费标准 fke127b206. */
    private String freightBasis;

    /** 使用时长 fke9201422. */
    private String useTime;

    /** 订购价格 fkef7330c8. */
    private String orderPrice;

    /** 受理渠道 fk928d6ed0. */
    private String acceptChannel;

    /** 授权开始时间 fk918097a6. */
    private String authorizationStartTime;

    /** 授权结束时间 fk313044e4. */
    private String authorizationEndTime;

}
