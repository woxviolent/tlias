package com.itheima.controller;


import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;


    @GetMapping
    public Result page( ClazzQueryParam clazzQueryParam ){
        log.info("查询请求参数： {}", clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        log.info("批量删除部门: id={} ", id);
        clazzService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result save( @RequestParam(required = true) String name,
                        @RequestParam(required = true) String room,
                        @RequestParam(required = true) String beginDate,

                        @RequestParam(required = true) String endDate,
                        @RequestParam(required = false) Integer masterId,
                        @RequestParam(required = true) Integer subject
                        ){
        Clazz clazz = new Clazz();
        clazz.setName(name);
        clazz.setRoom(room);
        clazz.setBeginDate(LocalDate.parse(beginDate));
        clazz.setEndDate(LocalDate.parse(endDate));
        clazz.setMasterId(masterId);
        clazz.setSubject(subject);
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setStatus("未开班");
        clazz.setMasterName("未指定");

        log.info("请求参数clazz: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Clazz clazz  = clazzService.getInfo(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改员工信息, {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

}
