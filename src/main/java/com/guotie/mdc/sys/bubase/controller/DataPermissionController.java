package com.guotie.mdc.sys.bubase.controller;

import cn.swust.generator.common.dto.ResponseResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guotie.mdc.sys.bubase.entity.DataPermission;
import com.guotie.mdc.sys.bubase.service.DataPermissionService;
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
@RequestMapping("/dataPermission")
public class DataPermissionController implements BaseControllerApi {
    @Autowired
    private DataPermissionService service;

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
    @GetMapping("/listByIds")
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
        Page<DataPermission> page = new Page<>(currentPage, pageSize);
        IPage<DataPermission> iPage = service.page(page, null);
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
        return ResponseResult.success(service.selectByConditions(params));
    }

    /**
     * 新增
     *
     * @param dataPermission 传递的实体
     */
    @PostMapping("/add")
    public ResponseResult dataPermissionAdd(DataPermission dataPermission) {
        boolean b = service.save(dataPermission);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量新增
     */
    @PostMapping("/batchAdd")
    public ResponseResult employeeBatchAdd(List<DataPermission> dataPermissions) {
        boolean b = service.saveBatch(dataPermissions);
        return b ? ResponseResult.success() : ResponseResult.error();
    }


    /**
     * 修改
     *
     * @param dataPermission 传递的实体
     */
    @PostMapping("/updateById")
    public ResponseResult dataPermissionUpdate(DataPermission dataPermission) {
        boolean b = service.updateById(dataPermission);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量根据id更新
     */
    @PostMapping("/updateBatchByIds")
    public ResponseResult dataPermissionUpdate(List<DataPermission> dataPermission) {
        boolean b = service.updateBatchById(dataPermission);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 根据id删除对象
     *
     * @param id 实体ID
     */
    @GetMapping("/deleteByid")
    public ResponseResult dataPermissionDelete(int id) {
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
