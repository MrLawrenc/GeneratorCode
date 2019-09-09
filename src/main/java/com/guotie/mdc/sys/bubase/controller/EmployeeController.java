package com.guotie.mdc.sys.bubase.controller;

import java.util.Map;
import java.util.List;

import cn.swust.generator.common.dto.ResponseResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guotie.mdc.sys.bubase.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.guotie.mdc.sys.bubase.entity.Employee;
import com.guotie.mdc.sys.bubase.service.EmployeeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 前端控制器
 *
 * @author lmy
 * @Description Created on 2019-09-09
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

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
        Page<Employee> page = new Page<>(currentPage, pageSize);
        IPage<Employee> iPage = service.page(page, null);
        return ResponseResult.success(iPage);
    }

    /**
     * 高级查询
     */
    @GetMapping("/selectByConditions")
    public ResponseResult selectByConditions(Map<String, Object> params) {
        if (params.get("currentPage") == null) {
            params.put("currentPage", "");
        }
        if (params.get("pageSize") == null) {
            params.put("pageSize", "");
        }
        /*    params.put("currentPage", 1);
        params.put("pageSize", 5);
        params.put("name", "ss");*/
        return ResponseResult.success(service.selectByConditions(params));
    }

    /**
     * 新增
     *
     * @param employee 传递的实体
     */
    @PostMapping("/add")
    public ResponseResult employeeAdd(Employee employee) {
        boolean b = service.save(employee);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量新增
     */
    @PostMapping("/batchAdd")
    public ResponseResult employeeBatchAdd(List<Employee> employees) {
        boolean b = service.saveBatch(employees);
        return b ? ResponseResult.success() : ResponseResult.error();
    }


    /**
     * 修改
     *
     * @param employee 传递的实体
     */
    @PostMapping("/updateById")
    public ResponseResult employeeUpdate(Employee employee) {
        boolean b = service.updateById(employee);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 批量根据id更新
     */
    @PostMapping("/updateBatchByIds")
    public ResponseResult employeeUpdate(List<Employee> employee) {
        boolean b = service.updateBatchById(employee);
        return b ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 根据id删除对象
     *
     * @param id 实体ID
     */
    @GetMapping("/deleteByid")
    public ResponseResult employeeDelete(int id) {
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
