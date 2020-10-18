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
@TableName("tb_seckill_order")
public class TbSeckillOrder implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 秒杀商品ID
     */
    @TableField("seckill_id")
    private Long seckillId;

    /**
     * 支付金额
     */
    @TableField("money")
    private Float money;

    /**
     * 用户
     */
    @TableField("user_id")
    private String userId;

    /**
     * 商家
     */
    @TableField("seller_id")
    private String sellerId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @TableField("pay_time")
    private Date payTime;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 收货人地址
     */
    @TableField("receiver_address")
    private String receiverAddress;

    /**
     * 收货人电话
     */
    @TableField("receiver_mobile")
    private String receiverMobile;

    /**
     * 收货人
     */
    @TableField("receiver")
    private String receiver;

    /**
     * 交易流水
     */
    @TableField("transaction_id")
    private String transactionId;


}
