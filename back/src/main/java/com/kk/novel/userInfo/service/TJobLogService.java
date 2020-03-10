package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TJobLog;
import java.util.List;

/**
 * (TJobLog)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TJobLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    TJobLog queryById(Long logId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TJobLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tJobLog 实例对象
     * @return 实例对象
     */
    TJobLog insert(TJobLog tJobLog);

    /**
     * 修改数据
     *
     * @param tJobLog 实例对象
     * @return 实例对象
     */
    TJobLog update(TJobLog tJobLog);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    boolean deleteById(Long logId);

}