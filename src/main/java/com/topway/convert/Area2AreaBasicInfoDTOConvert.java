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
        areaBasicInfoDTO.setAreaName(area.getFk999cd340());
        areaBasicInfoDTO.setAreaId(area.getFka9350c89());
        areaBasicInfoDTO.setAttribute(area.getFkfeffc5ea().equals("非城中村")?"花园小区":"城中村");
        areaBasicInfoDTO.setAddress(area.getFk999cd340());
        areaBasicInfoDTO.setGridId(area.getFk89cc3f4d());
        areaBasicInfoDTO.setGridName(area.getFk3c052170());
        areaBasicInfoDTO.setStation(area.getFk75638e31());
        areaBasicInfoDTO.setCustomNum(customNum);
        areaBasicInfoDTO.setCoverNum(coverNum);
        return areaBasicInfoDTO;
    }
}
