package com.topway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/28.
 */
@Data
public class RankListShowGridDTO<T> {

    public List<T> childs = new ArrayList<T>();

    public Double value;

    public Double per;

    public String id;

    public String name;

    public String parentId;
}
