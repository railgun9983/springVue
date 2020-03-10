package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TRoleMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * (TRoleMenu)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TRoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TRoleMenu queryById(Long roleId);

    List<Integer> queryByRoleId(Long roleId);

    void updateRoleMenu(Long roleId, ArrayList menuIds);


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRoleMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tRoleMenu 实例对象
     * @return 实例对象
     */
    TRoleMenu insert(TRoleMenu tRoleMenu);

    /**
     * 修改数据
     *
     * @param tRoleMenu 实例对象
     * @return 实例对象
     */
    TRoleMenu update(TRoleMenu tRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

    boolean deleteByIds(List<String> ids);

}