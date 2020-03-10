package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TTest;
import com.kk.novel.userInfo.service.TTestService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TTest)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("tTest")
public class TTestController {
    /**
     * 服务对象
     */
    @Resource
    private TTestService tTestService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TTest selectOne(Long id) {
        return this.tTestService.queryById(id);
    }

}