package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("tb_pay_log")
public class TbPayLog implements Serializable {


    /**
     * 支付订单号
     */
    @TableId(value = "out_trade_no",type = IdType.ASSIGN_ID)
    private String outTradeNo;

    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 支付完成时间
     */
    @TableField("pay_time")
    private Date payTime;

    /**
     * 支付金额（分）
     */
    @TableField("total_fee")
    private Long totalFee;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 交易号码
     */
    @TableField("transaction_id")
    private String transactionId;

    /**
     * 交易状态
     */
    @TableField("trade_state")
    private String tradeState;

    /**
     * 订单编号列表
     */
    @TableField("order_list")
    private String orderList;

    /**
     * 支付类型
     */
    @TableField("pay_type")
    private String payType;


}
