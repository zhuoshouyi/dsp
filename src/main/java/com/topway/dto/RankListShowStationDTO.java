package com.topway.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by haizhi on 2019/6/28.
 */
@Data
public class RankListShowStationDTO<T> {

    public List<T> childs;

    public String code;

    public String id;

    public String name;

    public String parentId;

}
