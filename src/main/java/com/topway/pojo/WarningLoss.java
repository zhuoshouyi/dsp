package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/6/25.
 */
@Data
@Entity
public class WarningLoss {

    /** id. */
    @Id
    @GeneratedValue
    private int id;

    /** 统计月份. fk63789b70 */
    private String date;

    /** 运营商. fk521afa3d */
    private String spcode;

    /** 区域分公司. fkbd2883aa */
    private String branch;

    /** 维护站名称. fkee09067d */
    private String station;

    /** 网格编码. fkf895439a */
    private String grid_id;

    /** 网格名称. fkaf296537 */
    private String grid_name;

    /** 本月底数字用户数. fkabae59ce */
    private Integer now_month_end_watch_num;

    /** 上月底数字用户数. fk95df4ea3 */
    private Integer last_month_end_watch_num;

    /** 本月底宽带用户数. fk6d76ab95 */
    private Integer now_month_end_wb_num;

    /** 上月底宽带用户数. fkd1d2b49e */
    private Integer last_month_end_wb_num;
}
