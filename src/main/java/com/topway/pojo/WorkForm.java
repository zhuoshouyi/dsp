package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/31.
 */
@Entity
@Data
public class WorkForm {

    /** id */
    @Id
    @GeneratedValue
    private int id;

    /** 工单编码_工单.fk4bb12569 */
    private String masterId;

    /** 用户编码_工单.fk5ede7403 */
    private String userId;

    /** 单据属性_工单.fk2939678f */
    private String masterType;

    /** 派单时间_工单.fk69edae7e */
    private String masterSendTime;

    /** 派单内容_工单.fkf784bca2 */
    private String masterSendContent;

    /** 创建时间_工单.fk368c3e9d */
    private String masterCreateTime;

    /** 返单类型_工单.fk1f41ae6e */
    private String masterBackType;

    /** 派单备注_工单.fk5b9b3e0a */
    private String masterSendRemark;

    /** 派单时间_从单.fk232178c3 */
    private String followSendTime;

    /** 返单时间_从单.fkb0444a1b */
    private String followBackTime;

    /** 返单人编码_从单.fk0f6fb368 */
    private String followBackPersonNo;

    /** 派单内容_从单.fkb4886aac */
    private String followSendContent;

    /** 派单备注_从单.fkacbe11b5 */
    private String followSendRemark;

    /** 返单类型_从单.fk0bfa8691 */
    private String followBackType;

    /** 返单备注_从单.fkc7192294 */
    private String followBackRemark;


}
