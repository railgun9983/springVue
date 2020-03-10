package com.kk.novel.userInfo.service.impl;

import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.userInfo.entity.TLoginLog;
import com.kk.novel.userInfo.dao.TLoginLogDao;
import com.kk.novel.userInfo.service.TLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * (TLoginLog)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tLoginLogService")
public class TLoginLogServiceImpl implements TLoginLogService {
    @Resource
    private TLoginLogDao tLoginLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param  id
     * @return 实例对象
     */
    @Override
    public TLoginLog queryById(Integer id ) {
        return this.tLoginLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TLoginLog> queryAllByLimit(int offset, int limit) {
        return this.tLoginLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tLoginLog 实例对象
     * @return 实例对象
     */
    @Override
    public TLoginLog insert(TLoginLog tLoginLog) {
        this.tLoginLogDao.insert(tLoginLog);
        return tLoginLog;
    }

    /**
     * 修改数据
     *
     * @param tLoginLog 实例对象
     * @return 实例对象
     */
    @Override
    public TLoginLog update(TLoginLog tLoginLog) {
        this.tLoginLogDao.update(tLoginLog);
        return this.queryById(tLoginLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id ) {
        return this.tLoginLogDao.deleteById(id) > 0;
    }

    @Override
    public List<TLoginLog> queryAllByTime(){
        return this.tLoginLogDao.queryAllByToday();
    }
}