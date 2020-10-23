package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbSellerMapper;
import com.yyg.heaven.pojo.TbSeller;
import com.yyg.heaven.service.ITbSellerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
public class TbSellerServiceImpl extends ServiceImpl<TbSellerMapper, TbSeller> implements ITbSellerService {

    @Autowired
    private TbSellerMapper tbSellerMapper;

    /**
     * 根据条件分页模糊查询
     * @param page
     * @param rows
     * @param tbSeller
     * @return
     */
    @Override
    public Page<TbSeller> findPageLike(int page, int rows, TbSeller tbSeller) {
        QueryWrapper<TbSeller> queryWrapper = new QueryWrapper<>();
        if (tbSeller!=null){
            // 判断公司名称是否为空
            if (tbSeller.getName()!=null && tbSeller.getName().length()>0){
                queryWrapper.like("name","%"+tbSeller.getName()+"%");
            }
            // 判断店铺名称是否为空
            if (tbSeller.getNickName()!=null && tbSeller.getNickName().length()>0){
                queryWrapper.like("nick_name","%"+tbSeller.getNickName()+"%");
            }
        }
        Page<TbSeller> tbSellerPage = new Page<>(page, rows);
        Page<TbSeller> tbSellerPage1 = tbSellerMapper.selectPage(tbSellerPage, queryWrapper);
        return tbSellerPage1;
    }

}
