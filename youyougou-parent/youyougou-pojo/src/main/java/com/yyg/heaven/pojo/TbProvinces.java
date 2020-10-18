package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 省份信息表
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
@TableName("tb_provinces")
public class TbProvinces implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 省份ID
     */
    @TableField("provinceid")
    private String provinceid;

    /**
     * 省份名称
     */
    @TableField("province")
    private String province;


}
