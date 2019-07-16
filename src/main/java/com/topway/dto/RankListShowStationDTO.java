package com.topway.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by haizhi on 2019/6/28.
 */
@Data
public class RankListShowStationDTO {

    public List<RankListShowGridDTO> childs;

    public Double value;

    public Double per;

    public String id;

    public String name;

    public String parentId;

}
