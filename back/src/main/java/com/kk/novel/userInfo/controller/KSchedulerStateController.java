package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.KSchedulerState;
import com.kk.novel.userInfo.service.KSchedulerStateService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (KSchedulerState)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("kSchedulerState")
public class KSchedulerStateController {
    /**
     * 服务对象
     */
    @Resource
    private KSchedulerStateService kSchedulerStateService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KSchedulerState selectOne(String id) {
        return this.kSchedulerStateService.queryById(id);
    }

}