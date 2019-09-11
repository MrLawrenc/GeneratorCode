package com.guotie.mdc.sys.bubase.controller;

import cn.swust.generator.common.dto.ResponseResult;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guotie.mdc.sys.bubase.entity.BaseDic;
import com.guotie.mdc.sys.bubase.service.BaseDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 前端控制器
 *
 * @author lmy
 * @Description Created on 2019-09-11
 */
@RestController
@RequestMapping("/baseDic")
public class BaseDicController {
    @Autowired
    private BaseDicService service;

    /**
     * 根据id查询
     */
    @GetMapping("/getById")
    public ResponseResult getById(int id) {
        return ResponseResult.success(service.getById(id));
    }

    /**
     * 批量查询
     */
    @PostMapping("/listByIds")
    public ResponseResult getByIds(List<Integer> ids) {
        return ResponseResult.success(service.listByIds(ids));
    }

    /**
     * 查询所有
     */
    @GetMapping("/list")
    public ResponseResult list() {
        return ResponseResult.success(service.list());
    }

    /**
     * 分页查询
     */
    @GetMapping("/pageList")
    public ResponseResult queryPage(int currentPage, int pageSize) {
        Page<BaseDic> page = new Page<>(currentPage, pageSize);
        IPage<BaseDic> iPage = service.page(page, null);
        return ResponseResult.success(iPage);
    }

    /**
     * 高级查询
     */
    @GetMapping("/selectByConditions")
    public ResponseResult selectByConditions(Map<String, Object> params) {
        if (params.get("currentPage") == null || params.get("pageSize") == null) {
            params.put("currentPage", "");
            params.put("pageSize", "");
        }
        System.out.println(JSON.toJSONString(params));
//        params.put("dicId", 1);
        return ResponseResult.success(service.selectByConditions(params));
    }

    /**
     * 新增
     *
     * @param baseDic 传递的实体
     */
    @PostMapping("/add")
    public ResponseResult baseDicAdd(BaseDic baseDic) {
        boolean b = service.save(baseDic);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量新增
     */
    @PostMapping("/batchAdd")
    public ResponseResult employeeBatchAdd(List<BaseDic> baseDics) {
        boolean b = service.saveBatch(baseDics);
        return b ? ResponseResult.success() : ResponseResult.error();
    }


    /**
     * 修改
     *
     * @param baseDic 传递的实体
     */
    @PostMapping("/updateById")
    public ResponseResult baseDicUpdate(BaseDic baseDic) {
        boolean b = service.updateById(baseDic);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量根据id更新
     */
    @PostMapping("/updateBatchByIds")
    public ResponseResult baseDicUpdate(List<BaseDic> baseDic) {
        boolean b = service.updateBatchById(baseDic);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 根据id删除对象
     *
     * @param id 实体ID
     */
    @GetMapping("/deleteByid")
    public ResponseResult baseDicDelete(int id) {
        boolean b = service.removeById(id);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量删除对象
     */
    @PostMapping("/deleteBatchByIds")
    public ResponseResult deleteBatchIds(List<Integer> ids) {
        boolean b = service.removeByIds(ids);
        return b ? ResponseResult.success() : ResponseResult.error();
    }


}
