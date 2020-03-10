package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TJob;
import com.kk.novel.userInfo.dao.TJobDao;
import com.kk.novel.userInfo.service.TJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TJob)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tJobService")
public class TJobServiceImpl implements TJobService {
    @Resource
    private TJobDao tJobDao;

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    @Override
    public TJob queryById(Long jobId) {
        return this.tJobDao.queryById(jobId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TJob> queryAllByLimit(int offset, int limit) {
        return this.tJobDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tJob 实例对象
     * @return 实例对象
     */
    @Override
    public TJob insert(TJob tJob) {
        this.tJobDao.insert(tJob);
        return tJob;
    }

    /**
     * 修改数据
     *
     * @param tJob 实例对象
     * @return 实例对象
     */
    @Override
    public TJob update(TJob tJob) {
        this.tJobDao.update(tJob);
        return this.queryById(tJob.getJobId());
    }

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long jobId) {
        return this.tJobDao.deleteById(jobId) > 0;
    }
}