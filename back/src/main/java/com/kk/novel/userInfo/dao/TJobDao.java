package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.TJob;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TJob)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TJobDao {

    /**
     * 通过ID查询单条数据
     *
     * @param jobId 主键
     * @return 实例对象
     */
    TJob queryById(Long jobId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TJob> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tJob 实例对象
     * @return 对象列表
     */
    List<TJob> queryAll(TJob tJob);

    /**
     * 新增数据
     *
     * @param tJob 实例对象
     * @return 影响行数
     */
    int insert(TJob tJob);

    /**
     * 修改数据
     *
     * @param tJob 实例对象
     * @return 影响行数
     */
    int update(TJob tJob);

    /**
     * 通过主键删除数据
     *
     * @param jobId 主键
     * @return 影响行数
     */
    int deleteById(Long jobId);

}