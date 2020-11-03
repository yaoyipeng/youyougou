package com.yyg.heaven.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.heaven.content.service.ITbContentService;
import com.yyg.heaven.mapper.TbContentMapper;
import com.yyg.heaven.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@Service
@Transactional
public class TbContentServiceImpl extends ServiceImpl<TbContentMapper, TbContent> implements ITbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据广告分类ID查询广告列表
     * @param categoryId
     * @return
     */
    @Override
    public List<TbContent> findByCategoryId(Long categoryId) {
        List<TbContent> contentList= (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);
        if(contentList==null){
            QueryWrapper<TbContent> tbContentQueryWrapper = new QueryWrapper<>();
            tbContentQueryWrapper.eq("category_id",categoryId);
            tbContentQueryWrapper.eq("status","1");// 查看广告开启状态
            tbContentQueryWrapper.orderByAsc("sort_order");// 按排序字段升序
            contentList = tbContentMapper.selectList(tbContentQueryWrapper);
            redisTemplate.boundHashOps("content").put(categoryId, contentList);//存入缓存
        }else{
            System.out.println("从缓存读取数据");
        }
        return contentList;
    }
    /**
     * 添加
     * @param tbContent
     * @return
     */
    @Override
    public void add(TbContent tbContent) {
        tbContentMapper.insert(tbContent);
        //清除缓存
        redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
    }
    /**
     * 修改
     * @param tbContent
     * @return
     */
    @Override
    public void updateTbContentById(TbContent tbContent) {
        //查询修改前的分类Id
        Long categoryId = tbContentMapper.selectById(tbContent.getId()).getCategoryId();
        redisTemplate.boundHashOps("content").delete(categoryId);
        tbContentMapper.updateById(tbContent);
        //如果分类ID发生了修改,清除修改后的分类ID的缓存
        if(categoryId.longValue()!=tbContent.getCategoryId().longValue()){
            redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
        }
    }
    /**
     * 批量删除
     * @return
     */
    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            //清除缓存
            Long categoryId = tbContentMapper.selectById(id).getCategoryId();//广告分类ID
            redisTemplate.boundHashOps("content").delete(categoryId);
        }
        tbContentMapper.deleteBatchIds(Arrays.asList(ids));
    }


}
