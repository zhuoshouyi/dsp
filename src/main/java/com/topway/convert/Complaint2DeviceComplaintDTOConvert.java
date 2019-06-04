package com.topway.convert;

import com.topway.dto.DeviceComplaintDTO;
import com.topway.pojo.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
        deviceComplaintDTO.setAcceptTime(complaint.getAcceptTime());
        // 联系电话
        deviceComplaintDTO.setPhone("");
        // fkcf8c69a6 投诉单内容
        deviceComplaintDTO.setContent(complaint.getComplaintContent());
        return deviceComplaintDTO;

    }

    public static List<DeviceComplaintDTO> convert(List<Complaint> complaintList){
        return complaintList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }

    public static Page<DeviceComplaintDTO> convert(Page<Complaint> complaintPage){
        Pageable pageable = complaintPage.getPageable();
        List<Complaint> complaintList = complaintPage.getContent();
        List<DeviceComplaintDTO> deviceComplaintDTOList = convert(complaintList);
        Page<DeviceComplaintDTO> deviceComplaintDTOPage =
                new PageImpl(deviceComplaintDTOList, pageable, complaintPage.getTotalElements());

        return deviceComplaintDTOPage;

    }
}
