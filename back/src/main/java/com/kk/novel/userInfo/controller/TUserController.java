package com.kk.novel.userInfo.controller;

import com.kk.novel.common.annotation.requestLog;
import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.userInfo.entity.TDept;
import com.kk.novel.userInfo.entity.TUser;
import com.kk.novel.userInfo.service.TUserRoleService;
import com.kk.novel.userInfo.service.TUserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * (TUser)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("user")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    @Resource
    private TUserRoleService tUserRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @PreAuthorize("isAuthenticated()")
    public ResponseDoMain selectOne(Long id) {
        return ResponseDoMain.success(this.tUserService.queryById(id));
    }


    @PostMapping("select")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseDoMain selectUser(TUser user) {
        return ResponseDoMain.success(this.tUserService.queryByUser(user));
    }

    @requestLog("删除用户")
    @DeleteMapping("delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseDoMain deleteUser(String ids) {
        return ResponseDoMain.success(this.tUserService.deleteByIds(ids));
    }

    @requestLog("更新用户")
    @PostMapping("update")
    @PreAuthorize("isAuthenticated()")
    public ResponseDoMain updateUser(TUser user) {
        List<String> list = Arrays.asList((user.getRoleId().split(",")));
        this.tUserRoleService.updateUserRole(user.getUserId(), list);
        return ResponseDoMain.success("");
    }

    @requestLog("新增用户")
    @PostMapping("add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain addUser(TUser user) {
        if (this.tUserService.queryByUserName(user.getUsername()) == null) {
            List<String> list = Arrays.asList((user.getRoleId().split(",")));
            this.tUserRoleService.updateUserRole(this.tUserService.insert(user).getUserId(), list);
            return ResponseDoMain.success("");
        } else {
            return ResponseDoMain.failure("", "用户名重复");
        }
    }

    @PostMapping("updatePwd")
    @PreAuthorize("isAuthenticated()")
    public ResponseDoMain updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd,
                                    @RequestParam("id") Long id){
        if (this.tUserService.updatePwd(id, oldPwd, newPwd)){
            return ResponseDoMain.success("");
        } else {
            return ResponseDoMain.failure("");
        }
    }

    @PostMapping("updateUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseDoMain updateUserByOne(TUser user) {
        this.tUserService.update(user);
        return ResponseDoMain.success("");
    }
}