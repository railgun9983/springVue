package com.kk.novel.userInfo.service;

import com.kk.novel.common.vueRouter.router.VueRouter;
import com.kk.novel.userInfo.entity.TMenu;
import com.kk.novel.userInfo.entity.TRole;

import java.util.ArrayList;
import java.util.List;

/**
 * (TRole)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TRole queryById(Long roleId);

    List<TRole> selectRole(TRole tRole);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    TRole insert(TRole tRole);

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    TRole update(TRole tRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

    boolean deleteByIds(List<String> roleIds);
}