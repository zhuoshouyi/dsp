package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/30.
 */
@Entity
@Data
public class User {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 用户编码.fk74dd6ddc */
    private String userId;

    /** 客户编码.fk572f5a34 */
    private String customerId;

    /** 业务类型.fkb391dcd3 */
    private String businessType;

    /** 资源号.fkdf1e945e */
    private String deviceNo;

    /** 型号.fk98d0d937 */
    private String model;

    /** 品牌.fk7eb032bb */
    private String brand;

    /** 地址.fkc398514b */
    private String address;

    /** 融合用户编码.fk4f5972b7 */
    private String mixNo;

    /** 数据日期.fkb57a3e00 */
    private String date;

    /** 网格名称.fk1d06e6a7 */
    private String grid;

    /** 机房名称_小区.fk4fb2b37b */
    private String computer;

    /** 账户银行名称.fkb1affa87 */
    private String bankName;

    /** 账户银行账号.fk53c01bbd */
    private String bankNo;

    /** 运营商. fk2ed8f61c */
    private String spcode;

    /** 区域分公司. fk41873d1c */
    private String branch;

    /** 网格编码. fk00e197e4 */
    private String gridId;

    /** 维护站名称. fk00e197e4 */
    private String station_name;

}
