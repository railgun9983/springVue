package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TUserRole;
import com.kk.novel.userInfo.dao.TUserRoleDao;
import com.kk.novel.userInfo.service.TUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TUserRole)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tUserRoleService")
public class TUserRoleServiceImpl implements TUserRoleService {
    @Resource
    private TUserRoleDao tUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public TUserRole queryById(Long userId) {
        return this.tUserRoleDao.queryById(userId);
    }

    @Override
    public List<TUserRole> queryAllById(Long userId) {
        return this.tUserRoleDao.queryAllById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TUserRole> queryAllByLimit(int offset, int limit) {
        return this.tUserRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public TUserRole insert(TUserRole tUserRole) {
        this.tUserRoleDao.insert(tUserRole);
        return tUserRole;
    }

    /**
     * 修改数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public TUserRole update(TUserRole tUserRole) {
        this.tUserRoleDao.update(tUserRole);
        return this.queryById(tUserRole.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.tUserRoleDao.deleteById(userId) > 0;
    }

    @Override
    public void updateUserRole(Long userId, List<String> roles) {
        this.tUserRoleDao.updateUserRole(userId, roles);
    }
}