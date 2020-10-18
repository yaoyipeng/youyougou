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
@TableName("tb_brand")
public class TbBrand implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 品牌名称
     */
    @TableField("name")
    private String name;

    /**
     * 品牌首字母
     */
    @TableField("first_char")
    private String firstChar;


}
