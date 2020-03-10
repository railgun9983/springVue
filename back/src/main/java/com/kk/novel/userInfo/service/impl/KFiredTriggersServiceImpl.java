package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.KFiredTriggers;
import com.kk.novel.userInfo.dao.KFiredTriggersDao;
import com.kk.novel.userInfo.service.KFiredTriggersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KFiredTriggers)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("kFiredTriggersService")
public class KFiredTriggersServiceImpl implements KFiredTriggersService {
    @Resource
    private KFiredTriggersDao kFiredTriggersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    @Override
    public KFiredTriggers queryById(String schedName) {
        return this.kFiredTriggersDao.queryById(schedName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KFiredTriggers> queryAllByLimit(int offset, int limit) {
        return this.kFiredTriggersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kFiredTriggers 实例对象
     * @return 实例对象
     */
    @Override
    public KFiredTriggers insert(KFiredTriggers kFiredTriggers) {
        this.kFiredTriggersDao.insert(kFiredTriggers);
        return kFiredTriggers;
    }

    /**
     * 修改数据
     *
     * @param kFiredTriggers 实例对象
     * @return 实例对象
     */
    @Override
    public KFiredTriggers update(KFiredTriggers kFiredTriggers) {
        this.kFiredTriggersDao.update(kFiredTriggers);
        return this.queryById(kFiredTriggers.getSchedName());
    }

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String schedName) {
        return this.kFiredTriggersDao.deleteById(schedName) > 0;
    }
}