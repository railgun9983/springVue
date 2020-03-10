package com.kk.novel.userInfo.controller;

import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.userInfo.entity.TLog;
import com.kk.novel.userInfo.service.TLogService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TLog)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("log")
public class TLogController {
    /**
     * 服务对象
     */
    @Resource
    private TLogService tLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TLog selectOne(Long id) {
        return this.tLogService.queryById(id);
    }

    @PostMapping("select")
    public ResponseDoMain selectLog(TLog log) {
        return ResponseDoMain.success(this.tLogService.queryAllByLog(log));
    }

}