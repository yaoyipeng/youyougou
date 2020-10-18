package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 行政区域地州市信息表
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
@TableName("tb_cities")
public class TbCities implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 城市ID
     */
    @TableField("cityid")
    private String cityid;

    /**
     * 城市名称
     */
    @TableField("city")
    private String city;

    /**
     * 省份ID
     */
    @TableField("provinceid")
    private String provinceid;


}
