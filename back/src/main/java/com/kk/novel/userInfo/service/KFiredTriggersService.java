package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.KFiredTriggers;
import java.util.List;

/**
 * (KFiredTriggers)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface KFiredTriggersService {

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    KFiredTriggers queryById(String schedName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KFiredTriggers> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kFiredTriggers 实例对象
     * @return 实例对象
     */
    KFiredTriggers insert(KFiredTriggers kFiredTriggers);

    /**
     * 修改数据
     *
     * @param kFiredTriggers 实例对象
     * @return 实例对象
     */
    KFiredTriggers update(KFiredTriggers kFiredTriggers);

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    boolean deleteById(String schedName);

}