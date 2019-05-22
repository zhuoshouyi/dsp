package com.topway.convert;

import com.topway.dto.AreaListDTO;
import com.topway.pojo.Area;
import org.springframework.beans.BeanUtils;

/**
 * Created by haizhi on 2019/5/22.
 */
public class Area2AreaListDTOConvert {

    public static AreaListDTO convert(Area area){
        AreaListDTO areaListDTO = new AreaListDTO();
        BeanUtils.copyProperties(area, areaListDTO);
        return areaListDTO;
    }
}
