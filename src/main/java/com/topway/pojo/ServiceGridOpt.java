package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/6/11.
 */
@Entity
@Data
public class ServiceGridOpt {

    @Id
    private String serviceGridId;

    private String opId;

}
