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
@TableName("tb_address")
public class TbAddress implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private  Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 省
     */
    @TableField("province_id")
    private String provinceId;

    /**
     * 市
     */
    @TableField("city_id")
    private String cityId;

    /**
     * 县/区
     */
    @TableField("town_id")
    private String townId;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 是否是默认 1默认 0否
     */
    @TableField("is_default")
    private String isDefault;

    /**
     * 备注
     */
    @TableField("notes")
    private String notes;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 别名
     */
    @TableField("alias")
    private String alias;

}
