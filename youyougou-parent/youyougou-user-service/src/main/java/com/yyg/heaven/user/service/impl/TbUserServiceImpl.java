package com.yyg.heaven.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbUserMapper;
import com.yyg.heaven.pojo.TbUser;
import com.yyg.heaven.user.service.ITbUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;
import javax.jms.MapMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private RedisTemplate<String , Object> redisTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination smsDestination;
    @Value("${template_code}")
    private String template_code;
    @Value("${sign_name}")
    private String sign_name;

    /**
     * 添加
     * @param user
     */
    @Override
    public void add(TbUser user) {
        user.setCreated(new Date());// 注册时间
        user.setUpdated(new Date());// 修改时间
        user.setSourceType("1");    // 注册来源
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));// 密码加密
        tbUserMapper.insert(user);

    }
    /**
     * 生成短信验证码
     */
    @Override
    public void createSmsCode(final  String phone) {
        //生成6位随机数
        final String code =  (long) (Math.random()*1000000)+"";
        System.out.println("验证码："+code);
        //存入缓存
        redisTemplate.boundHashOps("smscode").put(phone, code);
        //发送到activeMQ	....
        jmsTemplate.send(smsDestination, session -> {
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("mobile", phone);//手机号
            mapMessage.setString("template_code", template_code);//模板编号
            mapMessage.setString("sign_name", sign_name);//签名
            Map m=new HashMap<>();
            m.put("code", code);
            mapMessage.setString("code", JSON.toJSONString(m));//参数
            return mapMessage;
        });
    }
    /**
     * 判断验证码是否正确
     */
    @Override
    public boolean  checkSmsCode(String phone,String code){
        //得到缓存中存储的验证码
        String sysCode = (String) redisTemplate.boundHashOps("smscode").get(phone);
        if(sysCode==null){
            return false;
        }
        if(!sysCode.equals(code)){
            return false;
        }
        return true;
    }
}
