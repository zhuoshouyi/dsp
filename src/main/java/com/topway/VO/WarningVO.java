package com.topway.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/6/26.
 */
@Data
public class WarningVO {

//    @JsonProperty("数字电视终端流失数")
//    private Double watchLossNum = 0.0;

//    @JsonProperty("宽带终端流失数")
//    private Double wbLossNum = 0.0;

    @JsonProperty("24小时安装处理成功率")
    private Double installSuccess24h;

//    @JsonProperty("假单数")
//    private Double faultOrderNum = 0.0;

//    @JsonProperty("服务投诉数")
//    private Double serviceComplaintNum = 0.0;

//     @JsonProperty("营销生效额")
//     private Double marketEffectiveNum = 0.0;

    @JsonProperty("片区故障平均处理时长m")
    private Double regionTimeLength;

    @JsonProperty("故障及时处理成功率")
    private Double faultSuccess;
}
