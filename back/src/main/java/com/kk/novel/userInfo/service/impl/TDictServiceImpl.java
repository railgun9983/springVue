package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TDict;
import com.kk.novel.userInfo.dao.TDictDao;
import com.kk.novel.userInfo.service.TDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TDict)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tDictService")
public class TDictServiceImpl implements TDictService {
    @Resource
    private TDictDao tDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    @Override
    public TDict queryById(Long dictId) {
        return this.tDictDao.queryById(dictId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDict> queryAllByLimit(int offset, int limit) {
        return this.tDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDict 实例对象
     * @return 实例对象
     */
    @Override
    public TDict insert(TDict tDict) {
        this.tDictDao.insert(tDict);
        return tDict;
    }

    /**
     * 修改数据
     *
     * @param tDict 实例对象
     * @return 实例对象
     */
    @Override
    public TDict update(TDict tDict) {
        this.tDictDao.update(tDict);
        return this.queryById(tDict.getDictId());
    }

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long dictId) {
        return this.tDictDao.deleteById(dictId) > 0;
    }
}