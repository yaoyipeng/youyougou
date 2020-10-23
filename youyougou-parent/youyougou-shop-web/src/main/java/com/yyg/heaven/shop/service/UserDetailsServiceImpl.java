package com.yyg.heaven.shop.service;

import com.yyg.heaven.pojo.TbSeller;
import com.yyg.heaven.service.ITbSellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Dubbox-code-youyougou
 * @description 认证类
 * @author: 影耀子（YingYew）
 * @create: 2020-10-24 01:16
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    private ITbSellerService sellerService;
    public void setSellerService(ITbSellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("经过了UserDetailsServiceImpl");
        //构建角色列表
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //得到商家对象
        TbSeller seller = sellerService.getById(username);
        if(seller!=null){
            if(seller.getStatus().equals("1")){
                return new User(username,seller.getPassword(),grantedAuths);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
