package com.topway.dto;

import com.topway.pojo.WorkForm;

/**
 * Created by haizhi on 2019/5/27.
 */
public class WorkForm2DeviceWorkOrderDetailDTOConvert {
    // TODO
    public static DeviceWorkOrderDetailDTO convert(WorkForm workForm){
        DeviceWorkOrderDetailDTO deviceWorkOrderDetailDTO = new DeviceWorkOrderDetailDTO();
        MasterDetailDTO masterDetailDTO = new MasterDetailDTO();
        FollowDetailDTO followDetailDTO = new FollowDetailDTO();

        // 创建时间
        masterDetailDTO.setCreateTime("");
        // 派单时间

        // 单据类型

        // 派单内容

        // 处理详情

        // 派单备注

        deviceWorkOrderDetailDTO.setMasterDetail(masterDetailDTO);
        deviceWorkOrderDetailDTO.setFollowDetail(followDetailDTO);
        return null;
    }
}
