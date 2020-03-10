package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.KFiredTriggers;
import com.kk.novel.userInfo.service.KFiredTriggersService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (KFiredTriggers)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("kFiredTriggers")
public class KFiredTriggersController {
    /**
     * 服务对象
     */
    @Resource
    private KFiredTriggersService kFiredTriggersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KFiredTriggers selectOne(String id) {
        return this.kFiredTriggersService.queryById(id);
    }

}