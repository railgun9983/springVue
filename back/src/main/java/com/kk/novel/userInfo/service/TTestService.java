package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TTest;
import java.util.List;

/**
 * (TTest)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TTestService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TTest queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TTest> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tTest 实例对象
     * @return 实例对象
     */
    TTest insert(TTest tTest);

    /**
     * 修改数据
     *
     * @param tTest 实例对象
     * @return 实例对象
     */
    TTest update(TTest tTest);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}