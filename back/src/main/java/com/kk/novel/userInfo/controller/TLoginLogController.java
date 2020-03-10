package com.kk.novel.userInfo.controller;

import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.userInfo.entity.TLoginLog;
import com.kk.novel.userInfo.service.TLoginLogService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.ParseException;

/**
 * (TLoginLog)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("loginLog")
public class TLoginLogController {
    /**
     * 服务对象
     */
    @Resource
    private TLoginLogService tLoginLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TLoginLog selectOne(Integer id) {
        return this.tLoginLogService.queryById(id);
    }

    @PostMapping("selectLog")
    @PreAuthorize("isAuthenticated()")
    public ResponseDoMain selectLog() {
        return ResponseDoMain.success(this.tLoginLogService.queryAllByTime());
    }
}