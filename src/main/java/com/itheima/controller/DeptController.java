package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    @GetMapping
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @DeleteMapping
    public Result delete(Integer id){
        deptService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Dept dept){
        System.out.println("新增部门, dept=" + dept);
        deptService.save(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门, dept=" + dept);
        deptService.update(dept);
        return Result.success();
    }
}