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
@TableName("tb_seckill_goods")
public class TbSeckillGoods implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * spu ID
     */
    @TableField("goods_id")
    private Long goodsId;

    /**
     * sku ID
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 商品图片
     */
    @TableField("small_pic")
    private String smallPic;

    /**
     * 原价格
     */
    @TableField("price")
    private Float price;

    /**
     * 秒杀价格
     */
    @TableField("cost_price")
    private Float costPrice;

    /**
     * 商家ID
     */
    @TableField("seller_id")
    private String sellerId;

    /**
     * 添加日期
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 审核日期
     */
    @TableField("check_time")
    private Date checkTime;

    /**
     * 审核状态
     */
    @TableField("status")
    private String status;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 秒杀商品数
     */
    @TableField("num")
    private Integer num;

    /**
     * 剩余库存数
     */
    @TableField("stock_count")
    private Integer stockCount;

    /**
     * 描述
     */
    @TableField("introduction")
    private String introduction;


}
