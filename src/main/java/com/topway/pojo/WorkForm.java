package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/26.
 */
@Entity
@Data
public class WorkForm {

    /** id */
    @Id
    @GeneratedValue
    private int id;

    /** 工单从表编码 */
    private String fkb6d0313a;

    /** 工单主表编码 */
    private String fk1767b996;

    /** 业务类型 */
    private String fk48d38a12;

    /** 工单大类 */
    private String fkcad55a66;

    /** 工单类型 */
    private String fk4daf9bee;

    /** 工单状态 */
    private String fk2a81754d;

    /** 派单内容 */
    private String fk1e14931b;

    /** 派单备注 */
    private String fkf4f6303a;

    /** 资源号 */
    private String fkf5266887;

    /** 机顶盒号 */
    private String fk8d61e5e3;

    /** 产品编码 */
    private String fk83b4b287;

    /** 产品信息 */
    private String fkb28cc75a;

    /** 是否附带赠品 */
    private String fk0b6db1bd;

    /** 回收设备 */
    private String fk5d6b33fe;

    /** 更换设备 */
    private String fk013b9407;

    /** 区域授权 */
    private String fkfcc1d2a9;

    /** 处理方式 */
    private String fk072cc93e;

    /** 工单成功标志 */
    private String fkc0c6392f;

    /** 阶段名称 */
    private String fk0634bff1;

    /** 阶段处理结果 */
    private String fk773df17e;

    /** 阶段处理内容 */
    private String fkbb6786ff;

    /** 返单类型 */
    private String fk1ab76cea;

    /** 返单大类 */
    private String fka3a8787e;

    /** 返单备注 */
    private String fk9d883dfb;

    /** 返单时间 */
    private String fk81a6feef;

    /** 完成时间 */
    private String fk469897fa;

    /** 工单承诺时间 */
    private String fk491e51f2;

    /** 机房 */
    private String fk86bb0250;

    /** 返单部门编码 */
    private String fka533b4d5;

    /** 返单部门名称 */
    private String fk89506c7c;

    /** 返单人编码 */
    private String fk192fb65c;

    /** 返单人名称 */
    private String fkb45e4b8f;

    /** 当前处理部门编码 */
    private String fk645a7aed;

    /** 当前处理部门名称 */
    private String fk08e92476;

    /** 当前责任人编码 */
    private String fkfd3c500b;

    /** 当前责任人名称 */
    private String fk920dbf6b;

    /** 当前流转备注 */
    private String fk76b56acf;

    /** 正向电平 */
    private String fk08b918d4;

    /** 反向电平 */
    private String fkf8dc45dc;

    /** 机顶盒MAC */
    private String fkdedb4dbe;

    /** 机顶盒IP */
    private String fk29bc5270;

    /** CMMAC */
    private String fk6dde705d;

    /** CMIP */
    private String fk2751933f;

    /** 转派接收人编码 */
    private String fkbf15d97a;

    /** 当前流程编号 */
    private String fk97988ed5;

    /** 当前节点 */
    private String fk4896eca6;

    /** 工单到达节点时间 */
    private String fk30f307b9;

    /** 主机数 */
    private String fk005bd7e5;

    /** 副机数 */
    private String fk5f89b19a;

    /** 原始有效标识 */
    private String fk3ef08343;

    /** 当前有效标识 */
    private String fk769fed1c;

    /** BOSS工单从单编码 */
    private String fkf20eb08a;

    /** 派单时间 */
    private String fkc7646a53;

    /** 接收人编码 */
    private String fk1d9c4514;

    /** 上级流程编号 */
    private String fkd6346d46;

    /** 故障现象 */
    private String fk68b34a47;

    /** 用户编码 */
    private String fk496397ec;

    /** 变更后产品编码 */
    private String fk64418226;

    /** CMTSIP */
    private String fk82b59300;

    /** AP号 */
    private String fkdd8cb274;

    /** 自动处理间隔 */
    private String fk7bf9f56e;

    /** 是否已回收设备 */
    private String fkc4400789;

    /** 相同设备代号 */
    private String fk3baeb732;

    /** 迁移费用 */
    private String fk0a649f34;

    /** 技术诊断 */
    private String fk20bafbff;

    /** 当前处理部门属性 */
    private String fk3bc609d7;

    /** 当前处理部门属性编码 */
    private String fk02f4f155;

    /** 当前工单强制签收 */
    private String fkdd9d1135;

    /** 强制签收时间 */
    private String fka8e8eb60;

    /** 资源规格编码 */
    private String fkd0716777;

    /** 资源规格名称 */
    private String fk3cd1b60f;

    /** 处理时长m */
    private String fk379c3b9e;

    /** 是否迟返 */
    private String fkdf8b6dba;

}
