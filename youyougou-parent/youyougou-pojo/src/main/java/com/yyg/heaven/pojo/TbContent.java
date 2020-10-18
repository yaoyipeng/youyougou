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
@TableName("tb_content")
public class TbContent implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 内容类目ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 内容标题
     */
    @TableField("title")
    private String title;

    /**
     * 链接
     */
    @TableField("url")
    private String url;

    /**
     * 图片绝对路径
     */
    @TableField("pic")
    private String pic;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;


}
