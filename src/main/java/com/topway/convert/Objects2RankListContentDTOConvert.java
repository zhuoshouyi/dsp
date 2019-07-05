package com.topway.convert;

import com.topway.dto.RankListContentDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/6.
 */
public class Objects2RankListContentDTOConvert {

    public static RankListContentDTO convert(Object[] object){
        RankListContentDTO rankListContentDTO = new RankListContentDTO();
        rankListContentDTO.setName(String.valueOf(object[0]));
        rankListContentDTO.setValue(Double.parseDouble(object[1]==null?"0":object[1].toString()));
        return rankListContentDTO;
    }

    public static List<RankListContentDTO> convert(List<Object[]> objects){
        List<RankListContentDTO> rankListContentDTOList = new ArrayList<RankListContentDTO>();
        objects.stream().forEach(e -> rankListContentDTOList.add(convert(e)));
        return rankListContentDTOList;
    }
}
