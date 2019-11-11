package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/6/5.
 */
@Entity
@Data
public class RankListGrid {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 统计月.fk1de6ce9f */
    private String month;

    /** 运营商.fka7321e69 */
    private String spcode;

    /** 区域分公司.fk41873d1c */
    private String branchOffice;

    /** 分部名称.fkd757f8f6 */
    private String departement;

    /** 维护站名称.fkeb82a46c */
    private String station;

    /** 业务网格编码_工单.fk27060cbb */
    private String master_grid_id;

    /** 业务网格_工单.fk6854bb6e */
    private String master_grid;

    /** 业务类型_从单.fka7579569 */
    private String follow_business_type;

    /** 月度重复报障终端.fk78f2034e */
    private String month_repeat_fault_num;

    /** 月度报障终端.fka636ac5a */
    private String month_fault_num;

    /** 上月底收费用户数.fk92d0e650 */
    private String last_month_no_free_num;

}
