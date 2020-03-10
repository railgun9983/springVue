package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TDict;
import com.kk.novel.userInfo.service.TDictService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TDict)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("dict")
public class TDictController {
    /**
     * 服务对象
     */
    @Resource
    private TDictService tDictService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TDict selectOne(Long id) {
        return this.tDictService.queryById(id);
    }

}