package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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
@TableName("tb_specification")
public class TbSpecification implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("spec_name")
    private String specName;
    /**
     * 对应多个规格表
     */
    @TableField(exist = false)
    private List<TbSpecificationOption> specificationOptionList;

}
