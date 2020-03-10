package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TLoginLog;

import java.text.ParseException;
import java.util.List;

/**
 * (TLoginLog)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TLoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param  id
     * @return 实例对象
     */
    TLoginLog queryById(Integer id );

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TLoginLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tLoginLog 实例对象
     * @return 实例对象
     */
    TLoginLog insert(TLoginLog tLoginLog);

    /**
     * 修改数据
     *
     * @param tLoginLog 实例对象
     * @return 实例对象
     */
    TLoginLog update(TLoginLog tLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param  id
     * @return 是否成功
     */
    boolean deleteById(Integer id );

    List<TLoginLog> queryAllByTime();

}