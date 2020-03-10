package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.KSchedulerState;
import com.kk.novel.userInfo.dao.KSchedulerStateDao;
import com.kk.novel.userInfo.service.KSchedulerStateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KSchedulerState)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("kSchedulerStateService")
public class KSchedulerStateServiceImpl implements KSchedulerStateService {
    @Resource
    private KSchedulerStateDao kSchedulerStateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    @Override
    public KSchedulerState queryById(String schedName) {
        return this.kSchedulerStateDao.queryById(schedName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KSchedulerState> queryAllByLimit(int offset, int limit) {
        return this.kSchedulerStateDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kSchedulerState 实例对象
     * @return 实例对象
     */
    @Override
    public KSchedulerState insert(KSchedulerState kSchedulerState) {
        this.kSchedulerStateDao.insert(kSchedulerState);
        return kSchedulerState;
    }

    /**
     * 修改数据
     *
     * @param kSchedulerState 实例对象
     * @return 实例对象
     */
    @Override
    public KSchedulerState update(KSchedulerState kSchedulerState) {
        this.kSchedulerStateDao.update(kSchedulerState);
        return this.queryById(kSchedulerState.getSchedName());
    }

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String schedName) {
        return this.kSchedulerStateDao.deleteById(schedName) > 0;
    }
}