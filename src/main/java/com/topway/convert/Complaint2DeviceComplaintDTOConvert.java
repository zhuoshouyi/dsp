package com.topway.convert;

import com.topway.dto.DeviceComplaintDTO;
import com.topway.pojo.Complaint;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/5/27.
 */
public class Complaint2DeviceComplaintDTOConvert {

    public static DeviceComplaintDTO convert(Complaint complaint){
        DeviceComplaintDTO deviceComplaintDTO = new DeviceComplaintDTO();
        // id
        deviceComplaintDTO.setId(complaint.getId());
        // fk0b5c4bd1 受理时间
        deviceComplaintDTO.setAcceptTime(complaint.getFk0b5c4bd1());
        // 联系电话
        deviceComplaintDTO.setPhone("");
        // fkcf8c69a6 投诉单内容
        deviceComplaintDTO.setContent(complaint.getFkcf8c69a6());
        return deviceComplaintDTO;

    }

    public static List<DeviceComplaintDTO> convert(List<Complaint> complaintList){
        return complaintList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }
}
