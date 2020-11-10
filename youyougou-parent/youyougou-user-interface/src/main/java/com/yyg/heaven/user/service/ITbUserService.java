package com.yyg.heaven.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyg.heaven.pojo.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
public interface ITbUserService extends IService<TbUser> {

    /**
     * 添加
     * @param user
     */
    void add(TbUser user);
    /**
     * 生成短信验证码
     * @return
     */
    void createSmsCode(String phone);
    /**
     * 判断短信验证码是否存在
     * @param phone
     * @return
     */
    public boolean  checkSmsCode(String phone,String code);
}
