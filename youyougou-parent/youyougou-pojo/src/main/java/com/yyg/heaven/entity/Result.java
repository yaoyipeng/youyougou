package com.yyg.heaven.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @program: Dubbox-code-youyougou
 * @description
 * @author: 影耀子（YingYew）
 * @create: 2020-10-20 15:52
 *
 * 返回结果封装
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result implements Serializable {

    private boolean success;
    private String message;

}
