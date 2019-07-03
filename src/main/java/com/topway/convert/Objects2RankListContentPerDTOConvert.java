package com.topway.convert;

import com.topway.dto.RankListContentPerDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/7/3.
 */
public class Objects2RankListContentPerDTOConvert {

    public static RankListContentPerDTO convert(Object[] object){
        RankListContentPerDTO rankListContentPerDTO = new RankListContentPerDTO();
        rankListContentPerDTO.setName(String.valueOf(object[0]));
        rankListContentPerDTO.setValue(Double.parseDouble(object[1].toString()==null?"0":object[1].toString()));
        rankListContentPerDTO.setPer(Double.parseDouble(object[2].toString()==null?"0":object[2].toString()));
        return rankListContentPerDTO;
    }

    public static List<RankListContentPerDTO> convert(List<Object[]> objects){
        List<RankListContentPerDTO> rankListContentPerDTOList = new ArrayList<>();
        objects.stream().forEach(e -> rankListContentPerDTOList.add(convert(e)));
        return rankListContentPerDTOList;
    }
}
