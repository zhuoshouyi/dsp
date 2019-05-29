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

    /** 工单编码_工单. */
    private String fk4bb12569;

    /** 客户编码_工单. */
    private String fk7ed12de2;

    /** 客户名称_工单. */
    private String fk3e8956c6;

    /** 客户地址_工单. */
    private String fk791ee49f;

    /** 地址编码_工单. */
    private String fk3b84f123;

    /** 创建时间_工单. */
    private String fk368c3e9d;

    /** 用户编码_工单. */
    private String fk5ede7403;

    /** 客户等级_工单. */
    private String fk7f4c5d04;

    /** 客户性质_工单. */
    private String fk6789c97d;

    /** 派单来源_工单. */
    private String fk4c0fe644;

    /** 派单时间_工单. */
    private String fk69edae7e;

    /** 派单内容_工单. */
    private String fkf784bca2;

    /** 派单备注_工单. */
    private String fk5b9b3e0a;

    /** 派单人编码_工单. */
    private String fk8ab988a2;

    /** 派单人名称_工单. */
    private String fk39f96080;

    /** 派单部门编码_工单. */
    private String fk6a49139f;

    /** 派单部门名称_工单. */
    private String fk0acc22ff;

    /** 责任部门编码_工单. */
    private String fk0d955c6c;

    /** 联系人名称_工单. */
    private String fk90372c1f;

    /** 承诺时间_工单. */
    private String fkd2402222;

    /** 楼栋编码_工单. */
    private String fkd6c036e8;

    /** 处理成功标志_工单. */
    private String fk05e8786a;

    /** 是否补签_工单. */
    private String fk54a39ef4;

    /** 过程节点标识_工单. */
    private String fk26c9176d;

    /** 工单完成时间_工单. */
    private String fkdc85637c;

    /** 重派创建时间_工单. */
    private String fk9f38801c;

    /** 是否重派_工单. */
    private String fk299ac3b2;

    /** 催单次数_工单. */
    private String fkebc44897;

    /** 预约次数_工单. */
    private String fk4d1e9520;

    /** 优先级_工单. */
    private String fkadd2dbbf;

    /** 单据属性_工单. */
    private String fk2939678f;

    /** 原工单编码_工单. */
    private String fk193e82d6;

    /** 父工单编码_工单. */
    private String fkcb54b5b5;

    /** BOSS工单编码_工单. */
    private String fk61ee083b;

    /** 其他系统工单编码_工单. */
    private String fkc249dd8a;

    /** 运营商_工单. */
    private String fk6b479553;

    /** 区域_工单. */
    private String fk02e15d28;

    /** 是否归档_工单. */
    private String fk0f724446;

    /** SLA服务等级编码_工单. */
    private String fk387a06c2;

    /** 机房_工单. */
    private String fke8971d12;

    /** 故障现象_工单. */
    private String fk04808a8a;

    /** 合同编码_工单. */
    private String fk79807cc2;

    /** 融合单业务_工单. */
    private String fkf7fd48d8;

    /** 业务网格_工单. */
    private String fkb3f77a20;

    /** 是否最新_工单. */
    private String fkd452b0ac;

    /** 返单类型_工单. */
    private String fk1f41ae6e;

    /** 返单大类_工单. */
    private String fk9604babb;

    /** 无纸化_工单. */
    private String fk373ecdaf;

    /** 预约信息_工单. */
    private String fkb620acb1;

    /** 用户类型_工单. */
    private String fkeb1a081e;

    /** 工单从表编码_从单. */
    private String fk14e1b2dc;

    /** 工单主表编码_从单. */
    private String fkfa7b61d9;

    /** 业务类型_从单. */
    private String fk4f40b3e2;

    /** 工单大类_从单. */
    private String fk43f1ba84;

    /** 工单类型_从单. */
    private String fk9fab84ff;

    /** 工单状态_从单. */
    private String fk08c25a7d;

    /** 派单内容_从单. */
    private String fkb4886aac;

    /** 派单备注_从单. */
    private String fkacbe11b5;

    /** 资源号_从单. */
    private String fk6ad92d5c;

    /** 机顶盒号_从单. */
    private String fka093d669;

    /** 产品编码_从单. */
    private String fkd14a1af4;

    /** 产品信息_从单. */
    private String fkb658261b;

    /** 是否附带赠品_从单. */
    private String fk9188d978;

    /** 回收设备_从单. */
    private String fk10499ceb;

    /** 更换设备_从单. */
    private String fk5ab6d2f2;

    /** 区域授权_从单. */
    private String fk696673d8;

    /** 处理方式_从单. */
    private String fk88e801a2;

    /** 工单成功标志_从单. */
    private String fk28806b92;

    /** 阶段名称_从单. */
    private String fk3f6223af;

    /** 阶段处理结果_从单. */
    private String fka18a635e;

    /** 阶段处理内容_从单. */
    private String fkf6a41dc1;

    /** 返单类型_从单. */
    private String fk0bfa8691;

    /** 返单大类_从单. */
    private String fk4c3b6582;

    /** 返单备注_从单. */
    private String fkc7192294;

    /** 返单时间_从单. */
    private String fkb0444a1b;

    /** 完成时间_从单. */
    private String fkd993684b;

    /** 工单承诺时间_从单. */
    private String fk6e5675d4;

    /** 机房_从单. */
    private String fk650a3a8f;

    /** 返单人编码_从单. */
    private String fk0f6fb368;

    /** 返单部门编码_从单. */
    private String fk4768c107;

    /** 当前处理部门编码_从单. */
    private String fk32f2c5d2;

    /** 当前责任人编码_从单. */
    private String fk83df54b3;

    /** 当前流转备注_从单. */
    private String fk96ee7e5e;

    /** 正向电平_从单. */
    private String fk01e6bf0f;

    /** 反向电平_从单. */
    private String fk5df40960;

    /** 机顶盒MAC_从单. */
    private String fk8553c785;

    /** 机顶盒IP_从单. */
    private String fk7b0629be;

    /** CMMAC_从单. */
    private String fk8f0255da;

    /** CMIP_从单. */
    private String fk24f1ca0d;

    /** 转派接收人编码_从单. */
    private String fka185ee10;

    /** 当前流程编号_从单. */
    private String fk3db68c31;

    /** 当前节点_从单. */
    private String fk4cb26684;

    /** 工单到达节点时间_从单. */
    private String fkb92d14df;

    /** 主机数_从单. */
    private String fk48a9e686;

    /** 副机数_从单. */
    private String fkda096e6f;

    /** 原始有效标识_从单. */
    private String fkb90567ef;

    /** 当前有效标识_从单. */
    private String fk0952e566;

    /** BOSS工单从单编码_从单. */
    private String fk4f9dcd63;

    /** 派单时间_从单. */
    private String fk232178c3;

    /** 接收人编码_从单. */
    private String fk1bb4b0f5;

    /** 上级流程编号_从单. */
    private String fk1076ad93;

    /** 故障现象_从单. */
    private String fk378f881f;

    /** 用户编码_从单. */
    private String fk17cd314f;

    /** 变更后产品编码_从单. */
    private String fkcf3e56dd;

    /** CMTSIP_从单. */
    private String fkb5e420e1;

    /** AP号_从单. */
    private String fk7ccedb86;

    /** 自动处理间隔_从单. */
    private String fk60ce354c;

    /** 是否已回收设备_从单. */
    private String fk89da7ac6;

    /** 相同设备代号_从单. */
    private String fk072ce35d;

    /** 迁移费用_从单. */
    private String fkb074f87e;

    /** 技术诊断_从单. */
    private String fk7a27002c;

    /** 当前处理部门_从单. */
    private String fkaa392c40;

    /** 当前工单强制签收_从单. */
    private String fk391e7a75;

    /** 强制签收时间_从单. */
    private String fk7e0dd718;

    /** 资源规格编码_从单. */
    private String fk2582c87b;

    /** 资源规格名称_从单. */
    private String fkdcb5cedf;

    /** 业务网格编码_工单. */
    private String fkf8d38020;

    /** 是否故障单标识. */
    private String fk921af1b0;

    /** 是否迟返_从单. */
    private String fk00450a9b;

    /** 是否迟返_工单. */
    private String fk8913984c;

    /** 是否安装单标识. */
    private String fk1905aa01;

    /** 处理时长m_工单. */
    private String fk00f49e03;

    /** 处理时长m_从单. */
    private String fk164a6402;

    /** 当前责任人名称_从单. */
    private String fk0f86cb80;

    /** 当前处理部门名称_从单. */
    private String fk80cac03b;

    /** 返单人名称_从单. */
    private String fk4f8949f5;

    /** 返单部门名称_从单. */
    private String fk04a385b1;


}
