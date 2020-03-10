package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TJobLog;
import com.kk.novel.userInfo.dao.TJobLogDao;
import com.kk.novel.userInfo.service.TJobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TJobLog)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tJobLogService")
public class TJobLogServiceImpl implements TJobLogService {
    @Resource
    private TJobLogDao tJobLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public TJobLog queryById(Long logId) {
        return this.tJobLogDao.queryById(logId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TJobLog> queryAllByLimit(int offset, int limit) {
        return this.tJobLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tJobLog 实例对象
     * @return 实例对象
     */
    @Override
    public TJobLog insert(TJobLog tJobLog) {
        this.tJobLogDao.insert(tJobLog);
        return tJobLog;
    }

    /**
     * 修改数据
     *
     * @param tJobLog 实例对象
     * @return 实例对象
     */
    @Override
    public TJobLog update(TJobLog tJobLog) {
        this.tJobLogDao.update(tJobLog);
        return this.queryById(tJobLog.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long logId) {
        return this.tJobLogDao.deleteById(logId) > 0;
    }
}