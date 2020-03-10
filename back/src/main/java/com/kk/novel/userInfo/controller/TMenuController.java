package com.kk.novel.userInfo.controller;

import com.kk.novel.common.annotation.requestLog;
import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.common.vueRouter.router.VueRouter;
import com.kk.novel.userInfo.entity.TMenu;
import com.kk.novel.userInfo.manager.UserManager;
import com.kk.novel.userInfo.service.TMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

/**
 * (TMenu)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("menu")
public class TMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TMenuService tMenuService;

    @Autowired
    private UserManager userManager;

    @GetMapping("/{userId}")
    public ResponseDoMain getUserRouters(@NotBlank(message = "{required}") @PathVariable Long userId) {
        return ResponseDoMain.success(this.userManager.getUserRouters(userId));
    }

    @PostMapping("select")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain getMenuList (TMenu menu) {
        return ResponseDoMain.success(this.tMenuService.queryMenuByEntry(menu));
    }

    @requestLog("更新菜单")
    @PostMapping("update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain updateMenuList (TMenu menu) {
        return ResponseDoMain.success(this.tMenuService.update(menu));
    }

    @requestLog("添加菜单")
    @PostMapping("add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain addMenu (TMenu menu) {
        return ResponseDoMain.success(this.tMenuService.insert(menu));
    }

    @requestLog("删除菜单")
    @DeleteMapping("delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain deleteMenu (String ids) {
        this.tMenuService.deleteByIds(ids);
        return ResponseDoMain.success("");
    }
}