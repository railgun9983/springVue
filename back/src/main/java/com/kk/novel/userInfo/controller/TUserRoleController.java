package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TUserRole;
import com.kk.novel.userInfo.service.TUserRoleService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TUserRole)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("tUserRole")
public class TUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TUserRoleService tUserRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TUserRole selectOne(Long id) {
        return this.tUserRoleService.queryById(id);
    }

}