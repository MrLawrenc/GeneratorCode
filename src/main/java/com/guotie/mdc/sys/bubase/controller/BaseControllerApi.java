package com.guotie.mdc.sys.bubase.controller;

import cn.swust.generator.common.dto.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author : LiuMingyao
 * @date : 2019/9/17 15:26
 * @description : 基础swagger的api接口文档
 */

public interface BaseControllerApi {

    @ApiOperation(value = "根据id获取", notes = "功能描述")
    @ApiImplicitParam(name = "id", value = "获取的资源id", required = true, dataType = "int", paramType = "query")
    @GetMapping("/getById")
    ResponseResult getById(int id);

    @ApiOperation(value = "根据id获取", notes = "请求方式如:http://localhost/list?ids=1,2,3")
    @ApiImplicitParam(name = "ids", value = "获取的资源id集合", required = true, dataType = "list", paramType = "query")
    @GetMapping("/listByIds")
    ResponseResult getByIds(@RequestParam(name = "ids") List<Integer> ids);


    @ApiOperation(value = "获取所有", notes = "功能描述")
    @GetMapping("/list")
    ResponseResult list();


    @ApiOperation(value = "分页查询", notes = "功能描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页数", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示的数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/pageList")
    ResponseResult queryPage(int currentPage, int pageSize);

    @ApiOperation(value = "条件查询", notes = "请求方式如: http://localhost/condition?pId=AFCODE&codeName=1106")
    @ApiImplicitParam(name = "params", value = "条件键值对", required = false, dataType = "map", paramType = "query")
    @GetMapping("/selectByConditions")
    ResponseResult selectByConditions(@RequestParam Map<String, Object> params);

    @ApiOperation(value = "批量删除", notes = "  ")
    @ApiImplicitParam(name = "ids", value = "需要删除的id集合", required = true, dataType = "list", paramType = "body")
    @PostMapping("/deleteBatchByIds")
    ResponseResult deleteBatchIds(List<Integer> ids);
}