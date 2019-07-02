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
public class WarningService {

    /** id. */
    @Id
    @GeneratedValue
    private int id;

    /** 返单日期. fkb99778ab */
    private String back_time;

    /** 运营商_工单. fk4661ab79 */
    private String master_spcode;

    /** 区域_工单. fkf32b7750 */
    private String master_branch;

    /** 返单部门编码_从单. fkd7a1cd6d */
    private String follow_back_department_id;

    /** 返单部门名称_从单. fk0e90135e */
    private String follow_back_department_name;

    /** 业务网格编码_工单. fkd9a1a6c1 */
    private String master_grid_id;

    /** 业务网格_工单. fkf92654c3 */
    private String master_grid_name;

    /** 返单人编码_从单. fka97e72a5 */
    private String follow_back_person_id;

    /** 返单人名称_从单. fk3c9956cd */
    private String follow_back_person_name;

    /** 安装单处理量. fk782dc664 */
    private Double install_num;

    /** _24小时安装成功量. fkeb686eb5 */
    private Double install_success24h_num;

    /** _48小时内安装量. fkfa265b12 */
    private Double install_success48h_num;

    /** 电视故障处理量. fk03e3361a */
    private Double watch_fault_num;

    /** 电视故障及时处理成功量. fk47bc6475 */
    private Double watch_fault_timely_success_num;

    /** 宽带故障处理量. fkf8d84b63 */
    private Double wb_fault_num;

    /** 宽带故障及时处理成功量. fk0c46dea1 */
    private Double wb_fault_timely_success_num;

    /** 片区故障单量. fk522f298f */
    private Double region_fault_num;

    /** 片区故障处理时长m. fk97fc9361 */
    private Double region_fault_duration;

}
