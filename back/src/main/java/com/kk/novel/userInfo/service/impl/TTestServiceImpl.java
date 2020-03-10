package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TTest;
import com.kk.novel.userInfo.dao.TTestDao;
import com.kk.novel.userInfo.service.TTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TTest)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tTestService")
public class TTestServiceImpl implements TTestService {
    @Resource
    private TTestDao tTestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TTest queryById(Long id) {
        return this.tTestDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TTest> queryAllByLimit(int offset, int limit) {
        return this.tTestDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tTest 实例对象
     * @return 实例对象
     */
    @Override
    public TTest insert(TTest tTest) {
        this.tTestDao.insert(tTest);
        return tTest;
    }

    /**
     * 修改数据
     *
     * @param tTest 实例对象
     * @return 实例对象
     */
    @Override
    public TTest update(TTest tTest) {
        this.tTestDao.update(tTest);
        return this.queryById(tTest.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tTestDao.deleteById(id) > 0;
    }
}