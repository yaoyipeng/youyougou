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
@TableName("tb_type_template")
public class TbTypeTemplate implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    @TableField("name")
    private String name;

    /**
     * 关联规格
     */
    @TableField("spec_ids")
    private String specIds;

    /**
     * 关联品牌
     */
    @TableField("brand_ids")
    private String brandIds;

    /**
     * 自定义属性
     */
    @TableField("custom_attribute_items")
    private String customAttributeItems;


}
