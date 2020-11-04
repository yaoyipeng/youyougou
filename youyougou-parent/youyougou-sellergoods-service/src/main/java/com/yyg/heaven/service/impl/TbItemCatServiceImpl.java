package com.yyg.heaven.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.mapper.TbItemCatMapper;
import com.yyg.heaven.pojo.TbItemCat;
import com.yyg.heaven.service.ITbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbItemCatServiceImpl extends ServiceImpl<TbItemCatMapper, TbItemCat> implements ITbItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据上级ID查询列表分类
     * @param parentId
     * @return
     */
    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCat tbItemCat = new TbItemCat();
        // 把需要查询的上级ID封装
        tbItemCat.setParentId(parentId);
        // 构建条件构造器
        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>(tbItemCat);
//        QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id",parentId);
        //每次执行查询的时候，一次性读取缓存进行存储 (因为每次增删改都要执行此方法)
        List<TbItemCat> list = tbItemCatMapper.selectList(null);
        for(TbItemCat itemCat:list){
            redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
        }
        System.out.println("将模板id放入缓存。。。");
        // 根据条件查询并返回
        return tbItemCatMapper.selectList(queryWrapper);
    }

    /**
     * 批量删除分类(级联删除)
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            QueryWrapper<TbItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            // 根据第一类目的主键查询第二类目数据
            List<TbItemCat> tbItemCats = tbItemCatMapper.selectList(queryWrapper);
            if (tbItemCats!=null && tbItemCats.size()>0){
                List<Long> id2 = new ArrayList<>();
                for (TbItemCat itemCat : tbItemCats) {
                    id2.add(itemCat.getId());
                    // 根据第二类目的主键查询第三类目
                    QueryWrapper<TbItemCat> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("parent_id",itemCat.getId());
                    List<TbItemCat> tbItemCats1 = tbItemCatMapper.selectList(queryWrapper1);
                    if (tbItemCats1!=null && tbItemCats1.size()>0){
                        List<Long> id3 = new ArrayList<>();
                        for (TbItemCat cat : tbItemCats1) {
                            id3.add(cat.getId());
                        }
                        // 根据第三类目主键批量删除第三类目
                        tbItemCatMapper.deleteBatchIds(id3);
                    }
                }
                // 根据第二类目主键批量删除第二类目数据
                tbItemCatMapper.deleteBatchIds(id2);
            }
        }
        // 根据主键id批量删除第一类目
        tbItemCatMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
