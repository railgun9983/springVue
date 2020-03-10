package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.KPausedTriggerGrps;
import com.kk.novel.userInfo.dao.KPausedTriggerGrpsDao;
import com.kk.novel.userInfo.service.KPausedTriggerGrpsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KPausedTriggerGrps)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("kPausedTriggerGrpsService")
public class KPausedTriggerGrpsServiceImpl implements KPausedTriggerGrpsService {
    @Resource
    private KPausedTriggerGrpsDao kPausedTriggerGrpsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    @Override
    public KPausedTriggerGrps queryById(String schedName) {
        return this.kPausedTriggerGrpsDao.queryById(schedName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KPausedTriggerGrps> queryAllByLimit(int offset, int limit) {
        return this.kPausedTriggerGrpsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kPausedTriggerGrps 实例对象
     * @return 实例对象
     */
    @Override
    public KPausedTriggerGrps insert(KPausedTriggerGrps kPausedTriggerGrps) {
        this.kPausedTriggerGrpsDao.insert(kPausedTriggerGrps);
        return kPausedTriggerGrps;
    }

    /**
     * 修改数据
     *
     * @param kPausedTriggerGrps 实例对象
     * @return 实例对象
     */
    @Override
    public KPausedTriggerGrps update(KPausedTriggerGrps kPausedTriggerGrps) {
        this.kPausedTriggerGrpsDao.update(kPausedTriggerGrps);
        return this.queryById(kPausedTriggerGrps.getSchedName());
    }

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String schedName) {
        return this.kPausedTriggerGrpsDao.deleteById(schedName) > 0;
    }
}