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
@TableName("tb_order")
public class TbOrder implements Serializable {

    /**
     * 订单id
     */
    @TableId(value = "order_id",type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    @TableField("payment")
    private Float payment;

    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    @TableField("payment_type")
    private String paymentType;

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    @TableField("post_fee")
    private String postFee;

    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
     */
    @TableField("status")
    private String status;

    /**
     * 订单创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 付款时间
     */
    @TableField("payment_time")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @TableField("consign_time")
    private Date consignTime;

    /**
     * 交易完成时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 交易关闭时间
     */
    @TableField("close_time")
    private Date closeTime;

    /**
     * 物流名称
     */
    @TableField("shipping_name")
    private String shippingName;

    /**
     * 物流单号
     */
    @TableField("shipping_code")
    private String shippingCode;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 买家留言
     */
    @TableField("buyer_message")
    private String buyerMessage;

    /**
     * 买家昵称
     */
    @TableField("buyer_nick")
    private String buyerNick;

    /**
     * 买家是否已经评价
     */
    @TableField("buyer_rate")
    private String buyerRate;

    /**
     * 收货人地区名称(省，市，县)街道
     */
    @TableField("receiver_area_name")
    private String receiverAreaName;

    /**
     * 收货人手机
     */
    @TableField("receiver_mobile")
    private String receiverMobile;

    /**
     * 收货人邮编
     */
    @TableField("receiver_zip_code")
    private String receiverZipCode;

    /**
     * 收货人
     */
    @TableField("receiver")
    private String receiver;

    /**
     * 过期时间，定期清理
     */
    @TableField("expire")
    private Date expire;

    /**
     * 发票类型(普通发票，电子发票，增值税发票)
     */
    @TableField("invoice_type")
    private String invoiceType;

    /**
     * 订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端
     */
    @TableField("source_type")
    private String sourceType;

    /**
     * 商家ID
     */
    @TableField("seller_id")
    private String sellerId;


}
