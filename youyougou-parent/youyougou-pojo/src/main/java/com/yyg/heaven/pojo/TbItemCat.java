package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 商品类目
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
@TableName("tb_item_cat")
public class TbItemCat implements Serializable {
    /**
     * 类目ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 类目名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型id
     */
    @TableField("type_id")
    private Long typeId;


}
