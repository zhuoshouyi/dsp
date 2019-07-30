package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/6/28.
 */
@Data
public class RankListShowGridDTO {

//    public List<T> childs = new ArrayList<T>();

    public Double value;

    public Double per;

    public String id;

    public String name;

    public Boolean isOneself = false;

//    public String parentId;
}
