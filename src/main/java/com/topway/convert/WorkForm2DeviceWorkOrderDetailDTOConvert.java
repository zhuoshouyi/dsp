package com.topway.convert;

import com.topway.dto.DeviceWorkOrderDetailDTO;
import com.topway.dto.FollowDetailDTO;
import com.topway.dto.MasterDetailDTO;
import com.topway.pojo.WorkForm;

/**
 * Created by haizhi on 2019/5/27.
 */
public class WorkForm2DeviceWorkOrderDetailDTOConvert {

    public static DeviceWorkOrderDetailDTO convert(WorkForm workForm){
        DeviceWorkOrderDetailDTO deviceWorkOrderDetailDTO = new DeviceWorkOrderDetailDTO();
        MasterDetailDTO masterDetailDTO = new MasterDetailDTO();
        FollowDetailDTO followDetailDTO = new FollowDetailDTO();

        /** 主单 */
        // fk368c3e9d 创建时间_工单
        masterDetailDTO.setCreateTime(workForm.getMasterCreateTime());
        // fk69edae7e 派单时间_工单
        masterDetailDTO.setSendTime(workForm.getMasterSendTime());
        // fk2939678f 单据属性_工单
        masterDetailDTO.setType(workForm.getMasterType());
        // fkf784bca2 派单内容_工单
        masterDetailDTO.setContent(workForm.getMasterSendContent());
        // fk1f41ae6e 返单类型_工单 处理详情
        masterDetailDTO.setHandleDetails(workForm.getMasterBackType());
        // fk5b9b3e0a 派单备注_工单
        masterDetailDTO.setRemarks(workForm.getMasterSendRemark());


        /** 从单 */
        // fk232178c3 派单时间_从单
        followDetailDTO.setSendTime(workForm.getFollowSendTime());
        // fkb0444a1b 返单时间_从单
        followDetailDTO.setBackTime(workForm.getFollowBackTime());
        // 联系人电话_从单
        followDetailDTO.setPhone("");
        // fk0f6fb368 返单人编码_从单 处理人员_从单
        followDetailDTO.setDealWithPerson(workForm.getFollowBackPersonNo());
        // fkb4886aac 派单内容_从单
        followDetailDTO.setSendContent(workForm.getFollowSendContent());
        // fkacbe11b5 派单备注_从单
        followDetailDTO.setSendRemark(workForm.getFollowSendRemark());
        // fk0bfa8691 返单类型_从单 处理详细_从单
        followDetailDTO.setDealWithDetails(workForm.getFollowBackType());
        // fkc7192294 返单备注_从单
        followDetailDTO.setBackRemark(workForm.getFollowBackRemark());

        deviceWorkOrderDetailDTO.setMasterDetail(masterDetailDTO);
        deviceWorkOrderDetailDTO.setFollowDetail(followDetailDTO);
        return deviceWorkOrderDetailDTO;
    }
}
