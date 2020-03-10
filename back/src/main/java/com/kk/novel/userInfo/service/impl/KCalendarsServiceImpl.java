package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.KCalendars;
import com.kk.novel.userInfo.dao.KCalendarsDao;
import com.kk.novel.userInfo.service.KCalendarsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KCalendars)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:00:56
 */
@Service("kCalendarsService")
public class KCalendarsServiceImpl implements KCalendarsService {
    @Resource
    private KCalendarsDao kCalendarsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    @Override
    public KCalendars queryById(String schedName) {
        return this.kCalendarsDao.queryById(schedName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KCalendars> queryAllByLimit(int offset, int limit) {
        return this.kCalendarsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kCalendars 实例对象
     * @return 实例对象
     */
    @Override
    public KCalendars insert(KCalendars kCalendars) {
        this.kCalendarsDao.insert(kCalendars);
        return kCalendars;
    }

    /**
     * 修改数据
     *
     * @param kCalendars 实例对象
     * @return 实例对象
     */
    @Override
    public KCalendars update(KCalendars kCalendars) {
        this.kCalendarsDao.update(kCalendars);
        return this.queryById(kCalendars.getSchedName());
    }

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String schedName) {
        return this.kCalendarsDao.deleteById(schedName) > 0;
    }
}