package com.kk.novel.userInfo.controller;

import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.userInfo.entity.TDept;
import com.kk.novel.userInfo.service.TDeptService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
/**
 * (TDept)表控制层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@RestController
@RequestMapping("dept")
public class TDeptController {
    /**
     * 服务对象
     */
    @Resource
    private TDeptService tDeptService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @PreAuthorize("isAuthenticated()")
    public TDept selectOne(Long id) {
        return this.tDeptService.queryById(id);
    }

    @PostMapping("select")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain selectDept() {
        return ResponseDoMain.success(this.tDeptService.queryAll());
    }

    @PostMapping("selectByDept")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain selectByDept(TDept dept) {
        return ResponseDoMain.success(this.tDeptService.queryAll(dept));
    }

    @PostMapping("add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain addDept(TDept dept) {
        return ResponseDoMain.success(this.tDeptService.insert(dept));
    }

    @PostMapping("update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain updateDept(TDept dept) {
        return ResponseDoMain.success(this.tDeptService.update(dept));
    }

    @DeleteMapping("delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseDoMain deleteDept(String ids) {
        return ResponseDoMain.success(this.tDeptService.deleteByIds(ids));
    }
}