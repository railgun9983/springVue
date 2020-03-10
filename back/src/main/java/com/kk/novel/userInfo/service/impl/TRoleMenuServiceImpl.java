package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TRoleMenu;
import com.kk.novel.userInfo.dao.TRoleMenuDao;
import com.kk.novel.userInfo.service.TRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * (TRoleMenu)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tRoleMenuService")
public class TRoleMenuServiceImpl implements TRoleMenuService {
    @Resource
    private TRoleMenuDao tRoleMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TRoleMenu queryById(Long roleId) {
        return this.tRoleMenuDao.queryById(roleId);
    }

    @Override
    public List<Integer> queryByRoleId(Long roleId) {
        return this.tRoleMenuDao.queryByRoleId(roleId);
    }

    @Override
    public void updateRoleMenu(Long roleId, ArrayList menuIds) {
        this.tRoleMenuDao.InsertRoleMenu(roleId, menuIds);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRoleMenu> queryAllByLimit(int offset, int limit) {
        return this.tRoleMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TRoleMenu insert(TRoleMenu tRoleMenu) {
        this.tRoleMenuDao.insert(tRoleMenu);
        return tRoleMenu;
    }

    /**
     * 修改数据
     *
     * @param tRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TRoleMenu update(TRoleMenu tRoleMenu) {
        this.tRoleMenuDao.update(tRoleMenu);
        return this.queryById(tRoleMenu.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.tRoleMenuDao.deleteById(roleId) > 0;
    }

    @Override
    public boolean deleteByIds(List<String> ids) {
        return this.tRoleMenuDao.deleteByIds(ids) > 0;
    }
}