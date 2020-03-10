package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TJobLog;
import com.kk.novel.userInfo.service.TJobLogService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TJobLog)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("tJobLog")
public class TJobLogController {
    /**
     * 服务对象
     */
    @Resource
    private TJobLogService tJobLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TJobLog selectOne(Long id) {
        return this.tJobLogService.queryById(id);
    }

}