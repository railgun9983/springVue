package com.kk.novel.userInfo.controller;

import com.alibaba.fastjson.JSONObject;
import com.kk.novel.common.annotation.requestLog;
import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.userInfo.entity.TRole;
import com.kk.novel.userInfo.service.TRoleMenuService;
import com.kk.novel.userInfo.service.TRoleService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * (TRole)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("role")
public class TRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleService tRoleService;

    @Resource
    private TRoleMenuService tRoleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRole selectOne(Long id) {
        return this.tRoleService.queryById(id);
    }

    @PostMapping("select")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain selectRole(TRole tRole) {
        return ResponseDoMain.success(this.tRoleService.selectRole(tRole));
    }

    @PostMapping("getMenu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain getMenu(Long id) {
        return ResponseDoMain.success(this.tRoleMenuService.queryByRoleId(id));
    }

    @requestLog("更新角色")
    @PostMapping("update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain updateRole(@RequestBody Map<String, Object> map) {
        TRole role = JSONObject.parseObject(JSONObject.toJSONString(map.get("role")), TRole.class);
        ArrayList lists = (ArrayList) map.get("list");
        this.tRoleService.update(role);
        this.tRoleMenuService.updateRoleMenu(role.getRoleId(), lists);
        return ResponseDoMain.success("");
    }

    @requestLog("添加角色")
    @PostMapping("add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain addRole(@RequestBody Map<String, Object> map) {
        TRole role = JSONObject.parseObject(JSONObject.toJSONString(map.get("role")), TRole.class);
        ArrayList lists = (ArrayList) map.get("list");
        this.tRoleService.insert(role);
        this.tRoleMenuService.updateRoleMenu(role.getRoleId(), lists);
        return ResponseDoMain.success("");
    }

    @requestLog("删除角色")
    @DeleteMapping("delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain deleteRole(String  ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.tRoleService.deleteByIds(list);
        this.tRoleMenuService.deleteByIds(list);
        return ResponseDoMain.success("");
    }

}