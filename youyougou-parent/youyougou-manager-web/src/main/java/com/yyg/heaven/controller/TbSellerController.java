package com.yyg.heaven.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.heaven.entity.Result;
import com.yyg.heaven.pojo.TbSeller;
import com.yyg.heaven.service.ITbSellerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YingYew
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/seller")
public class TbSellerController {

    @Reference
    private ITbSellerService tbSellerService;

    /**
     * 根据条件分页模糊查询
     * @param page
     * @param rows
     * @param tbSeller
     * @return
     */
    @PostMapping("search")
    public Page<TbSeller> search(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                           @RequestParam(name = "rows",required = true,defaultValue = "10") int rows,
                           @RequestBody TbSeller tbSeller){
        return tbSellerService.findPageLike(page,rows,tbSeller);
    }

    /**
     * 分页查询数据
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findPage")
    public Page<TbSeller> findPage(@RequestParam(name = "page",required = true,defaultValue = "1") int page,
                             @RequestParam(name = "rows",required = true,defaultValue = "10") int rows){
        Page<TbSeller> tbSellerPage = new Page<>(page, rows);
//        return tbSellerService.findPage(page,rows);
        return tbSellerService.page(tbSellerPage);
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping("findAll")
    public List<TbSeller> findAll(){
        return tbSellerService.list();
    }

    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    @GetMapping("findOne/{id}")
    public TbSeller findOne(@PathVariable(value = "id")String id){
        return tbSellerService.getById(id);
    }

    /**
     * 更改状态
     * @param sellerId 商家ID
     * @param status 状态
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(String sellerId, String status){
        try {
            TbSeller tbSeller = new TbSeller();
            tbSeller.setSellerId(sellerId);
            tbSeller.setStatus(status);
            tbSellerService.updateById(tbSeller);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }

    /**
     * 增加
     * @param seller
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody TbSeller seller){
        //密码加密
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(seller.getPassword());//加密
        seller.setPassword(password);
        seller.setStatus("0");
        seller.setCreateTime(new Date());
        try {
            tbSellerService.save(seller);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }



}
