package com.kk.novel.userInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.userInfo.entity.TRole;
import com.kk.novel.userInfo.dao.TRoleDao;
import com.kk.novel.userInfo.service.TRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TRole)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tRoleService")
public class TRoleServiceImpl implements TRoleService {
    @Resource
    private TRoleDao tRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TRole queryById(Long roleId) {
        return this.tRoleDao.queryById(roleId);
    }

    @Override
    public List<TRole> selectRole(TRole tRole) {
        return this.tRoleDao.queryList(tRole);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRole> queryAllByLimit(int offset, int limit) {
        return this.tRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole insert(TRole tRole) {
        tRole.setCreateTime(DateFormat.dateToString(new Date()));
        this.tRoleDao.insert(tRole);
        return tRole;
    }

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole update(TRole tRole) {
        tRole.setModifyTime(DateFormat.dateToString(new Date()));
        this.tRoleDao.update(tRole);
        return this.queryById(tRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.tRoleDao.deleteById(roleId) > 0;
    }

    @Override
    public boolean deleteByIds(List<String> roleIds) {
        return this.tRoleDao.deleteByIds(roleIds) > 0;
    }
}