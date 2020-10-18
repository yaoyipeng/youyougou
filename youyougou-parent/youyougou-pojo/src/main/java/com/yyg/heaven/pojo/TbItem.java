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
 * 商品表
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
@TableName("tb_item")
public class TbItem implements Serializable {
    /**
     * 商品id，同时也是商品编号
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 商品标题
     */
    @TableField("title")
    private String title;

    /**
     * 商品卖点
     */
    @TableField("sell_point")
    private String sellPoint;

    /**
     * 商品价格，单位为：元
     */
    @TableField("price")
    private Float price;
    @TableField("stock_count")
    private Integer stockCount;

    /**
     * 库存数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 商品条形码
     */
    @TableField("barcode")
    private String barcode;

    /**
     * 商品图片
     */
    @TableField("image")
    private String image;

    /**
     * 所属类目，叶子类目
     */
    @TableField("categoryId")
    private Long categoryId;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    @TableField("status")
    private String status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    @TableField("item_sn")
    private String itemSn;
    @TableField("cost_pirce")
    private Float costPirce;
    @TableField("market_price")
    private Float marketPrice;
    @TableField("is_default")
    private String isDefault;
    @TableField("goods_id")
    private Long goodsId;
    @TableField("seller_id")
    private String sellerId;
    @TableField("cart_thumbnail")
    private String cartThumbnail;
    @TableField("category")
    private String category;
    @TableField("brand")
    private String brand;
    @TableField("spec")
    private String spec;
    @TableField("seller")
    private String seller;


}
