package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: zhuoshouyi
 * @Description:
 * @Date: 2020/1/6
 * @Param:
 * @return:
 */

@Data
public class WarningForm {

    /**
     * 运营商id
     * 0-天威，2-光明，5-天宝，6-天隆，7-深汕
     */
    @NotEmpty(message = "operator 必填")
    private String operator;

    /**
     * 分公司id
     * 01-罗湖，02-福田，03-南山，00-天威
     */
    @NotEmpty(message = "branch 必填")
    private String branch;

    /**
     * 网格编码串
     * 多个id用逗号分隔开，传-1时查询分公司或运营商的所有网格
     */
    @NotEmpty(message = "grids 必填")
    private String grids = "";

    /**
     * 请求结果集编号
     * 指标id，多个指标id间用逗号隔开
     * 1  24小时安装处理成功率（考核月：上月21日-本月20日）
     * 2  营销生效额（考核月）
     * 3  片区故障平均处理时长（考核月）
     * 4  故障及时处理成功率（考核月，数字电视：24小时成功处理/总处理单量）
     */
    @NotEmpty(message = "targets 必填")
    private String targets = "";


}
