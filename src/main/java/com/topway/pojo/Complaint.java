package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
@Entity
public class Complaint {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 投诉编码. */
    private String fk2d546781;

    /** 投诉级别编码. */
    private String fk0999b16a;

    /** 受理时间. */
    private String fk0b5c4bd1;

    /** 受理人编码. */
    private String fkd86d1ad1;

    /** 状态. */
    private String fk99f52854;

    /** 应结单时间. */
    private String fkc4df9e7e;

    /** 当前状态时间. */
    private String fk8d4807ae;

    /** 服务辖区. */
    private String fk97a1771a;

    /** 用户属性. */
    private String fk16432da4;

    /** 客户编码. */
    private String fk54e41e30;

    /** 客户名称. */
    private String fk6b5146e7;

    /** 地址编码. */
    private String fk56250aae;

    /** 分单类型. */
    private String fkd192ff8f;

    /** 满意度. */
    private String fk9ea83a6c;

    /** 行政区域. */
    private String fk0910615b;

    /** 是否有效. */
    private String fkd866e5fa;

    /** 用户备注. */
    private String fk28dc5240;

    /** 结单时间. */
    private String fk25c3d8d3;

    /** 第一次联系上用户时间. */
    private String fk7f21285c;

    /** 下次应该联系用户时间. */
    private String fk47df7966;

    /** 投诉单内容. */
    private String fkcf8c69a6;

    /** 是否删除. */
    private String fk143d358c;

    /** 分单时间. */
    private String fkd9f28eee;

    /** 责任人编码. */
    private String fk9e4a27d8;

    /** 是否被质检. */
    private String fkce7ce5bc;

    /** 智能卡号. */
    private String fk4267c6c6;

    /** 用户地址. */
    private String fkefe5a2a7;

    /** 维护站编码. */
    private String fkb2211f51;

    /** 是否需要回复. */
    private String fkc85e631b;

    /** 受理类型. */
    private String fk846b9b23;

    /** 服务类型. */
    private String fk771ec95c;

    /** 红黑名单标识. */
    private String fk461a39b0;

    /** 下一次应跟进时间. */
    private String fkec8066eb;

    /** 关单类型. */
    private String fk22e0dc4d;

    /** 是否经理确认. */
    private String fkd62806b4;

    /** 处理方式分类一. */
    private String fk7fadbec0;

    /** 处理方式分类二. */
    private String fk14d9b227;

    /** 回访建议标识. */
    private String fk54821b12;

    /** 当前处理人编码. */
    private String fk0f1fe715;

    /** 录音编码. */
    private String fk4b840c75;

    /** 应截止时间. */
    private String fk289f916b;

    /** 业务类型. */
    private String fke37958d9;

    /** 用户业务类型. */
    private String fka7123fa6;

    /** 用户辖区. */
    private String fkc8c345b6;

    /** 用户编码. */
    private String fk4b0f0ba1;

    /** 用户维护站. */
    private String fk527cb3cc;

    /** 是否规范标识. */
    private String fk74440464;

    /** 是否规范原因. */
    private String fk9a7a2f8d;

    /** 用户类型. */
    private String fkbf057ba4;

    /** 规范标志编码. */
    private String fkfdc25d2e;

    /** 客户等级标识. */
    private String fk859358d7;

    /** 行政属性标识. */
    private String fkf302b60b;

    /** 投诉子单编码. */
    private String fk9292e3f3;

    /** 创建时间. */
    private String fkca36ad78;

    /** 子单状态. */
    private String fk692b9528;

    /** 状态时间. */
    private String fk5251ee53;

    /** 应结单时间_子单. */
    private String fk53978e6e;

    /** 责任部门编码. */
    private String fk055536e9;

    /** 投诉子单级别. */
    private String fk8f27c557;

    /** 业务类型_子单. */
    private String fk58e6bfdd;

    /** 反馈状态. */
    private String fk63027c94;

    /** 打印. */
    private String fkb8df6992;

    /** 责任部门责任人编码. */
    private String fk1ab155ab;

    /** 是否有效标识. */
    private String fk13bd1308;

    /** 满意度标识. */
    private String fkc2f949c4;

    /** 一级分类. */
    private String fk005526ed;

    /** 二级分类. */
    private String fk559e5b6a;

    /** 三级分类. */
    private String fkac29cba8;

    /** 四级分类. */
    private String fk0a99495a;

    /** 投诉描述. */
    private String fk2fa0e281;

    /** 实际结单时间. */
    private String fk7d4add61;

    /** 是否删除_子单. */
    private String fke9b155bf;

    /** 建单人编码. */
    private String fk4dfc22d8;

    /** 是否重复投诉. */
    private String fk5ba3d85f;

    /** 受理类型_子单. */
    private String fk51aa8539;

    /** 是否需分发. */
    private String fk3dae3579;

    /** 关单类型_子单. */
    private String fkeb7109ec;

    /** 是否已阅读. */
    private String fk57d19fa6;

    /** 是否跟进. */
    private String fk49df6406;

    /** 跟进内容. */
    private String fk26e4bf91;

    /** 行政区域编码. */
    private String fk5afd4e75;

    /** 应处理截止时间. */
    private String fk8458ae40;

    /** 处理时间. */
    private String fkbe7a8968;

    /** 操作员编码_处理. */
    private String fk7f6fdcd8;

    /** 内容分类. */
    private String fke039e965;

    /** 实际联系用户时间. */
    private String fkc9abd556;

    /** 要求联系用户时间. */
    private String fk7b262f6b;

    /** 呼叫类型. */
    private String fkaa05825c;

    /** 操作员编码_通话. */
    private String fke4cb4b87;

    /** OA对接部门编码. */
    private String fk5f7f80a6;

    /** OA对接部门名称. */
    private String fk79f5a880;

    /** 超时处理量. */
    private String fkdafff7d8;

    /** 关单时长. */
    private String fka343c617;

    /** 结单量. */
    private String fk77eaf517;

    /** 和解量. */
    private String fkf755e2e3;

    /** 投诉来源. */
    private String fkee9a3c22;

}
