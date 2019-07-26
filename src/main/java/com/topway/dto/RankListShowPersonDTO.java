package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/6/28.
 */
@Data
public class RankListShowPersonDTO {

    public Double value;

    public Double per;

    public String id;

    public String name;

    public Boolean isOneself = false;

}
