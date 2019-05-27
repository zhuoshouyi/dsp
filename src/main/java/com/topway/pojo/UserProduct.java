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

    /** 订购标识. */
    private String fk75e925ce;

    /** 用户编码. */
    private String fk451a778a;

    /** 用户状态. */
    private String fkce8c4507;

    /** 产品编码. */
    private String fkb7939445;

    /** 产品名称. */
    private String fk795b247c;

    /** 受理单编码. */
    private String fk13dd2c23;

    /** 订购时间. */
    private String fk477a0abf;

    /** 订购类型. */
    private String fk6be53031;

    /** 订购状态. */
    private String fkb8be2a22;

    /** 使用状态. */
    private String fk49a441af;

    /** 计费标准. */
    private String fke127b206;

    /** 使用时长. */
    private String fke9201422;

    /** 使用时长单位. */
    private String fke57d99ab;

    /** 订购价格. */
    private String fkef7330c8;

    /** 发展人. */
    private String fkb2f2937f;

    /** 受理渠道编码. */
    private String fkb6059b28;

    /** 受理渠道. */
    private String fk928d6ed0;

    /** 操作员编码. */
    private String fk86655d4b;

    /** 收取押金. */
    private String fkef940597;

    /** 授权开始时间. */
    private String fk918097a6;

    /** 授权结束时间. */
    private String fk313044e4;

    /** 计费开始时间. */
    private String fk63d3fe27;

    /** 计费结束时间. */
    private String fk506dbbe6;

    /** 产品预停时间. */
    private String fka8b23eb4;

    /** 产品预开时间. */
    private String fkbece3664;

    /** 状态变更时间. */
    private String fk09af8044;

    /** 转订前产品. */
    private String fk4cf30e64;

    /** 订购带宽. */
    private String fka3354fbc;

    /** 是否计费标识. */
    private String fke3a0c394;

    /** 是否融合免费产品标识. */
    private String fk733050fc;

    /** 是否融合产品标识. */
    private String fk93f72c4b;

    /** 是否上网产品. */
    private String fk482f7361;

    /** 是否融合互动产品标识. */
    private String fk1018a398;

    /** 宽带接入方式. */
    private String fk805f6aca;

    /** 宽带上网方式. */
    private String fk75eca7b2;

    /** 是否新融合产品标识. */
    private String fkf490e994;

    /** 是否单互动产品标识. */
    private String fk46105142;

}
