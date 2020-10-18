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
@TableName("tb_seller")
public class TbSeller implements Serializable {


    /**
     * 用户ID
     */
    @TableId(value = "seller_id",type = IdType.ASSIGN_ID)
    private String sellerId;

    /**
     * 公司名
     */
    @TableField("name")
    private String name;

    /**
     * 店铺名称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * EMAIL
     */
    @TableField("email")
    private String email;

    /**
     * 公司手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 公司电话
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 详细地址
     */
    @TableField("address_detail")
    private String addressDetail;

    /**
     * 联系人姓名
     */
    @TableField("linkman_name")
    private String linkmanName;

    /**
     * 联系人QQ
     */
    @TableField("linkman_qq")
    private String linkmanQq;

    /**
     * 联系人电话
     */
    @TableField("linkman_mobile")
    private String linkmanMobile;

    /**
     * 联系人EMAIL
     */
    @TableField("linkman_email")
    private String linkmanEmail;

    /**
     * 营业执照号
     */
    @TableField("license_number")
    private String licenseNumber;

    /**
     * 税务登记证号
     */
    @TableField("tax_number")
    private String taxNumber;

    /**
     * 组织机构代码
     */
    @TableField("org_number")
    private String orgNumber;

    /**
     * 公司地址
     */
    @TableField("address")
    private Long address;

    /**
     * 公司LOGO图
     */
    @TableField("logo_pic")
    private String logoPic;

    /**
     * 简介
     */
    @TableField("brief")
    private String brief;

    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 法定代表人
     */
    @TableField("legal_person")
    private String legalPerson;

    /**
     * 法定代表人身份证
     */
    @TableField("legal_person_card_id")
    private String legalPersonCardId;

    /**
     * 开户行账号名称
     */
    @TableField("bank_user")
    private String bankUser;

    /**
     * 开户行
     */
    @TableField("bank_name")
    private String bankName;


}
