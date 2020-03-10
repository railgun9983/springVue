package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TRoleMenu;
import com.kk.novel.userInfo.service.TRoleMenuService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TRoleMenu)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("tRoleMenu")
public class TRoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleMenuService tRoleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRoleMenu selectOne(Long id) {
        return this.tRoleMenuService.queryById(id);
    }

}