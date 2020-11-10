package com.yyg.heaven.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbUser;
import com.yyg.heaven.user.service.ITbUserService;
import com.yyg.heaven.utils.PhoneFormatCheckUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/user")
public class TbUserController {

    @Reference
    private ITbUserService tbUserService;

    /**
     * 添加
     * @param user
     * @param smscode
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody TbUser user, String smscode){
        boolean checkSmsCode = tbUserService.checkSmsCode(user.getPhone(), smscode);
        if(!checkSmsCode){
            return new Result(false, "验证码输入错误！");
        }
        try {
            tbUserService.add(user);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }
    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @GetMapping("/sendCode")
    public Result sendCode(String phone){
        //判断手机号格式
        if(!PhoneFormatCheckUtils.isPhoneLegal(phone)){
            return new Result(false, "手机号格式不正确");
        }
        try {
            tbUserService.createSmsCode(phone);//生成验证码
            return new Result(true, "验证码发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "验证码发送失败");
        }
    }

}