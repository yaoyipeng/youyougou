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
@TableName("tb_freight_template")
public class TbFreightTemplate implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id = 1L;

    /**
     * 商家ID
     */
    @TableField("seller_id")
    private String sellerId;

    /**
     * 是否默认   （‘Y’是   'N'否）
     */
    @TableField("is_default")
    private String isDefault;

    /**
     * 模版名称
     */
    @TableField("name")
    private String name;

    /**
     * 发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
     */
    @TableField("send_time_type")
    private String sendTimeType;

    /**
     * 统一价格(数据库自动四舍五入，所有用Integer接收)
     */
    @TableField("price")
    private Integer price;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}
