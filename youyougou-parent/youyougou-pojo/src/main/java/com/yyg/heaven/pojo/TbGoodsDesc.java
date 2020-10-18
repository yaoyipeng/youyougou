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
@TableName("tb_goods_desc")
public class TbGoodsDesc implements Serializable {


    /**
     * SPU_ID
     */
    @TableId(value = "goods_id",type = IdType.ASSIGN_ID)
    private Long goodsId;

    /**
     * 描述
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 规格结果集，所有规格，包含isSelected
     */
    @TableField("specification_items")
    private String specificationItems;

    /**
     * 自定义属性（参数结果）
     */
    @TableField("custom_attribute_items")
    private String customAttributeItems;
    @TableField("item_images")
    private String itemImages;

    /**
     * 包装列表
     */
    @TableField("package_list")
    private String packageList;

    /**
     * 售后服务
     */
    @TableField("sale_service")
    private String saleService;


}
