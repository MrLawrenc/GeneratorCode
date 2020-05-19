package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Entity}.*;
import ${package.Service}.${table.serviceName};
<#if cfg.needValid??>
    import org.springframework.lang.NonNull;
</#if>
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
 
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
/**
* ${table.comment!} 前端控制器
*
* @author ${author}
* @Description Created on ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    @RequestMapping("/${table.entityPath}")
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass}<${entity}> {
    <#else>
        public class ${table.controllerName} {
    </#if>
    @Autowired
    private ${table.serviceName} service;

    <#if cfg.needControllerMethod??>
        /**
        * 根据id查询
        */
        @GetMapping("/getById")
        public  ResponseResult getById(<#if cfg.needValid??>@NonNull</#if> int id) {
        return ResponseResult.success(service.getById(id));
        }
         /**
          * 批量查询
          */
        @GetMapping("/listByIds")
        public ResponseResult getByIds(@RequestParam(name = "ids") List<Integer> ids) {
            return ResponseResult.success(service.listByIds(ids));
        }
        /**
        * 查询所有
        */
        @GetMapping("/list")
        public  ResponseResult list() {
        return ResponseResult.success(service.list(null));
        }

        /**
        * 分页查询
        */
        @GetMapping("/pageList")
        public ResponseResult queryPage(<#if cfg.needValid??>@NonNull</#if> int currentPage,<#if cfg.needValid??>@NonNull</#if> int pageSize) {
        Page<${entity}> page = new Page<>(currentPage, pageSize);
                      IPage<${entity}> iPage = service.page(page, null);
        return ResponseResult.success(iPage);
        }

        /**
        * 高级查询
        */
        @GetMapping("/selectByConditions")
        public ResponseResult selectByConditions(@RequestParam Map<String,Object> params){

            return ResponseResult.success(service.selectByConditions(params));
        }

        /**
        * 新增
        * @param ${table.entityPath}  传递的实体
        */
        <#if methodRestful??>
            @PutMapping("/add")
        <#else>
            @PostMapping("/add")
        </#if>
        public ResponseResult ${table.entityPath}Add(<#if cfg.needValid??>@NonNull</#if> ${entity} ${table.entityPath}) {
        boolean b = service.save(${table.entityPath});
        return b ? ResponseResult.success() : ResponseResult.error();
        }

         /**
          * 批量新增
          */
         <#if methodRestful??>
          @PutMapping("/batchAdd")
          <#else>
          @PostMapping("/batchAdd")
          </#if>
        public ResponseResult ${table.entityPath}BatchAdd(List<${entity}> ${table.entityPath}s) {
            boolean b = service.saveBatch(${table.entityPath}s);
            return b ? ResponseResult.success() : ResponseResult.error();
        }


        /**
        * 修改
        * @param ${table.entityPath}  传递的实体
        */
        @PostMapping("/updateById")
        public ResponseResult ${table.entityPath}Update(<#if cfg.needValid??>@NonNull</#if> ${entity} ${table.entityPath}) {
        boolean b = service.updateById(${table.entityPath});
        return b ? ResponseResult.success() : ResponseResult.error();
        }
        /**
        * 批量根据id更新
        */
        @PostMapping("/updateBatchByIds")
        public ResponseResult ${table.entityPath}Update(<#if cfg.needValid??>@NonNull</#if>  List<${entity}> ${table.entityPath}) {
        boolean b = service.updateBatchById(${table.entityPath});
        return b ? ResponseResult.success() : ResponseResult.error();
        }
        /**
        * 根据id删除对象
        * @param id  实体ID
        */
        <#if methodRestful??>
            @DeleteMapping("/deleteById")
        <#else>
            @GetMapping("/deleteById")
        </#if>
        public ResponseResult ${table.entityPath}Delete(<#if cfg.needValid??>@NonNull</#if> int id){
        boolean b = service.removeById(id);
        return b ? ResponseResult.success() : ResponseResult.error();
        }

        /**
        * 批量删除对象
        */
        @GetMapping("/deleteBatchByIds")
        public ResponseResult deleteBatchIds(@RequestParam(name = "ids") <#if cfg.needValid??>@NonNull</#if>  List<Integer> ids){
        boolean b = service.removeByIds(ids);
        return b ? ResponseResult.success() : ResponseResult.error();
        }


    </#if>


    }
</#if>