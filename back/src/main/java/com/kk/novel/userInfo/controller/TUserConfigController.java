package com.kk.novel.userInfo.controller;

import com.kk.novel.userInfo.entity.TUserConfig;
import com.kk.novel.userInfo.service.TUserConfigService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TUserConfig)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("tUserConfig")
public class TUserConfigController {
    /**
     * 服务对象
     */
    @Resource
    private TUserConfigService tUserConfigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TUserConfig selectOne(Long id) {
        return this.tUserConfigService.queryById(id);
    }

}