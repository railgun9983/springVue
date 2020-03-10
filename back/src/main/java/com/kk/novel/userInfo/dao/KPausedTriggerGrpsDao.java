package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.KPausedTriggerGrps;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (KPausedTriggerGrps)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface KPausedTriggerGrpsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    KPausedTriggerGrps queryById(String schedName);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KPausedTriggerGrps> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param kPausedTriggerGrps 实例对象
     * @return 对象列表
     */
    List<KPausedTriggerGrps> queryAll(KPausedTriggerGrps kPausedTriggerGrps);

    /**
     * 新增数据
     *
     * @param kPausedTriggerGrps 实例对象
     * @return 影响行数
     */
    int insert(KPausedTriggerGrps kPausedTriggerGrps);

    /**
     * 修改数据
     *
     * @param kPausedTriggerGrps 实例对象
     * @return 影响行数
     */
    int update(KPausedTriggerGrps kPausedTriggerGrps);

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 影响行数
     */
    int deleteById(String schedName);

}