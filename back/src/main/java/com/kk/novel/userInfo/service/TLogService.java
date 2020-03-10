package com.kk.novel.userInfo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kk.novel.userInfo.entity.TLog;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

/**
 * (TLog)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TLog> queryAllByLimit(int offset, int limit);

    List<TLog> queryAllByLog(TLog log);

    /**
     * 新增数据
     *
     * @param tLog 实例对象
     * @return 实例对象
     */
    TLog insert(ProceedingJoinPoint point, TLog tLog) throws JsonProcessingException;

    /**
     * 修改数据
     *
     * @param tLog 实例对象
     * @return 实例对象
     */
    TLog update(TLog tLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}