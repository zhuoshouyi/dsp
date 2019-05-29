package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 历史营销进场记录
 *
 * Created by haizhi on 2019/5/23.
 */
@Entity
@Data
public class HistoryMarket {

    /** id */
    @Id
    @GeneratedValue
    private Integer id;

    /** areaId */
    private String areaId;

    /** 记录时间 */
    private String createTime;

    /** 记录内容 */
    private String content;
}
