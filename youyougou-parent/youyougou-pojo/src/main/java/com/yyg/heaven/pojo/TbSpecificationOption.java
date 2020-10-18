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
@TableName("tb_specification_option")
public class TbSpecificationOption implements Serializable {

    /**
     * 规格项ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 规格项名称
     */
    @TableField("option_name")
    private String optionName;

    /**
     * 规格ID
     */
    @TableField("spec_id")
    private Long specId;

    /**
     * 排序值
     */
    @TableField("orders")
    private Integer orders;


}
