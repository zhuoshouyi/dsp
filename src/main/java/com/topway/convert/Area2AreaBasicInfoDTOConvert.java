package com.topway.convert;

import com.topway.dto.AreaBasicInfoDTO;
import com.topway.pojo.Area;

/**
 * Created by haizhi on 2019/5/28.
 */
public class Area2AreaBasicInfoDTOConvert {

    /**
     * 将 Area 对象转换为小区详情页的 AreaBasicInfoDTO 对象
     *
     * @param area
     * @param customNum 天威客户数
     * @param coverNum  覆盖住户数
     * @return
     */
    public static AreaBasicInfoDTO convert(Area area, Integer customNum, Integer coverNum){
        AreaBasicInfoDTO areaBasicInfoDTO = new AreaBasicInfoDTO();
        areaBasicInfoDTO.setAreaName(area.getAreaName());
        areaBasicInfoDTO.setAreaId(area.getAreaId());
        areaBasicInfoDTO.setAttribute(area.getBuildAttrbute().equals("非城中村")?"花园小区":"城中村");
        areaBasicInfoDTO.setAddress(area.getAreaName());
        areaBasicInfoDTO.setGridId(area.getGridId());
        areaBasicInfoDTO.setGridName(area.getGridName());
        areaBasicInfoDTO.setStation(area.getStation());
        areaBasicInfoDTO.setCustomNum(customNum);
        areaBasicInfoDTO.setCoverNum(coverNum);
        return areaBasicInfoDTO;
    }
}
