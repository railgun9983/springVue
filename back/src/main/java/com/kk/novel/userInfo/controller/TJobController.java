package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TJob;
import com.kk.novel.userInfo.service.TJobService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TJob)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("tJob")
public class TJobController {
    /**
     * 服务对象
     */
    @Resource
    private TJobService tJobService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TJob selectOne(Long id) {
        return this.tJobService.queryById(id);
    }

}