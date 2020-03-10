package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.KCalendars;
import com.kk.novel.userInfo.service.KCalendarsService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (KCalendars)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:00:57
 */
@RestController
@RequestMapping("kCalendars")
public class KCalendarsController {
    /**
     * 服务对象
     */
    @Resource
    private KCalendarsService kCalendarsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public KCalendars selectOne(String id) {
        return this.kCalendarsService.queryById(id);
    }

}