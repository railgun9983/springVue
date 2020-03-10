package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.KSchedulerState;
import java.util.List;

/**
 * (KSchedulerState)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface KSchedulerStateService {

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    KSchedulerState queryById(String schedName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KSchedulerState> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kSchedulerState 实例对象
     * @return 实例对象
     */
    KSchedulerState insert(KSchedulerState kSchedulerState);

    /**
     * 修改数据
     *
     * @param kSchedulerState 实例对象
     * @return 实例对象
     */
    KSchedulerState update(KSchedulerState kSchedulerState);

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    boolean deleteById(String schedName);

}