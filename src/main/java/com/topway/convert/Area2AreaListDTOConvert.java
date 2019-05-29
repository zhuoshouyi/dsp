package com.topway.convert;

import com.topway.dto.AreaListDTO;
import com.topway.pojo.Area;
import org.springframework.beans.BeanUtils;

/**
 * Created by haizhi on 2019/5/28.
 */
public class Area2AreaListDTOConvert {

    /**
     * 将 Area 对象转换为小区列表的 AreaListDTO 对象
     *
     * @param area
     * @return
     */
    public static AreaListDTO convert(Area area){
        AreaListDTO areaListDTO = new AreaListDTO();
        BeanUtils.copyProperties(area, areaListDTO);
        return areaListDTO;
    }
}
