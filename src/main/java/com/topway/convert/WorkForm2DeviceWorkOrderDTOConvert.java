package com.topway.convert;

import com.topway.dto.DeviceWorkOrderDTO;
import com.topway.pojo.WorkForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/5/27.
 */
public class WorkForm2DeviceWorkOrderDTOConvert {

    public static DeviceWorkOrderDTO convert(WorkForm workForm){
        DeviceWorkOrderDTO deviceWorkOrderDTO = new DeviceWorkOrderDTO();
        // id
        deviceWorkOrderDTO.setId(workForm.getId());
        // fk2939678f 单据属性_工单 工单类型
        deviceWorkOrderDTO.setOrderType(workForm.getMasterType());
        // fk69edae7e 派单时间_工单
        deviceWorkOrderDTO.setCreateTime(workForm.getMasterSendTime());
        // fkf784bca2 派单内容_工单
        deviceWorkOrderDTO.setContent(workForm.getMasterSendContent());
        return deviceWorkOrderDTO;

    }

    public static List<DeviceWorkOrderDTO> convert(List<WorkForm> workFormList){

        return workFormList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }


    public static Page<DeviceWorkOrderDTO> convert(Page<WorkForm> workFormPage){
        Pageable pageable = workFormPage.getPageable();
        List<WorkForm> workFormList = workFormPage.getContent();
        List<DeviceWorkOrderDTO> deviceWorkOrderDTOList = convert(workFormList);
        Page<DeviceWorkOrderDTO> deviceWorkOrderDTOPage =
                new PageImpl(deviceWorkOrderDTOList, pageable, workFormPage.getTotalElements());

        return deviceWorkOrderDTOPage;

    }
}
