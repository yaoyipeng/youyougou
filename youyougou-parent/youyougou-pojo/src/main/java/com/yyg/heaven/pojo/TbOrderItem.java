package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

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
@TableName("tb_order_item")
public class TbOrderItem implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品id
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * SPU_ID
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 商品标题
     */
    @TableField("title")
    private String title;

    /**
     * 商品单价
     */
    @TableField("price")
    private Float price;

    /**
     * 商品购买数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 商品总金额
     */
    @TableField("total_fee")
    private Float totalFee;

    /**
     * 商品图片地址
     */
    @TableField("pic_path")
    private String picPath;
    @TableField("seller_id")
    private String sellerId;


}
