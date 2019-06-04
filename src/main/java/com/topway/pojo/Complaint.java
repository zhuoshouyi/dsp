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
public class Complaint {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 投诉编码.fk2d546781 */
    private String complaintId;

    /** 受理时间.fk0b5c4bd1 */
    private String acceptTime;

    /** 用户编码.fk4b0f0ba1 */
    private String userId;

    /** 投诉单内容.fkcf8c69a6 */
    private String complaintContent;

    /** 投诉来源.fkee9a3c22 */
    private String complaintForm;

    /** 结单时间.fk25c3d8d3 */
    private String overTime;

    /** 处理方式分类一.fk7fadbec0 */
    private String processMode1;

    /** 处理方式分类二.fk14d9b227 */
    private String processMode2;



}
