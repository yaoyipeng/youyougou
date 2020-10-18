package com.yyg.heaven.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 行政区域县区信息表
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
@TableName("tb_areas")
public class TbAreas implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 区域ID
     */
    @TableField("areaid")
    private String areaid;

    /**
     * 区域名称
     */
    @TableField("area")
    private String area;

    /**
     * 城市ID
     */
    @TableField("cityid")
    private String cityid;


}
