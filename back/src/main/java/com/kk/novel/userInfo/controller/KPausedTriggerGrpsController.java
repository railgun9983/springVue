package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.KPausedTriggerGrps;
import com.kk.novel.userInfo.service.KPausedTriggerGrpsService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (KPausedTriggerGrps)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("kPausedTriggerGrps")
public class KPausedTriggerGrpsController {
    /**
     * 服务对象
     */
    @Resource
    private KPausedTriggerGrpsService kPausedTriggerGrpsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KPausedTriggerGrps selectOne(String id) {
        return this.kPausedTriggerGrpsService.queryById(id);
    }

}