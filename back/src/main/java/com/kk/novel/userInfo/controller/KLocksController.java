package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.KLocks;
import com.kk.novel.userInfo.service.KLocksService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (KLocks)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("kLocks")
public class KLocksController {
    /**
     * 服务对象
     */
    @Resource
    private KLocksService kLocksService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KLocks selectOne(String id) {
        return this.kLocksService.queryById(id);
    }

}