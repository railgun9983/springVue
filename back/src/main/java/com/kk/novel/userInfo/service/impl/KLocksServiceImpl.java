package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.KLocks;
import com.kk.novel.userInfo.dao.KLocksDao;
import com.kk.novel.userInfo.service.KLocksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (KLocks)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("kLocksService")
public class KLocksServiceImpl implements KLocksService {
    @Resource
    private KLocksDao kLocksDao;

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    @Override
    public KLocks queryById(String schedName) {
        return this.kLocksDao.queryById(schedName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<KLocks> queryAllByLimit(int offset, int limit) {
        return this.kLocksDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param kLocks 实例对象
     * @return 实例对象
     */
    @Override
    public KLocks insert(KLocks kLocks) {
        this.kLocksDao.insert(kLocks);
        return kLocks;
    }

    /**
     * 修改数据
     *
     * @param kLocks 实例对象
     * @return 实例对象
     */
    @Override
    public KLocks update(KLocks kLocks) {
        this.kLocksDao.update(kLocks);
        return this.queryById(kLocks.getSchedName());
    }

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String schedName) {
        return this.kLocksDao.deleteById(schedName) > 0;
    }
}