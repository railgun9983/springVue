package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.TLoginLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TLoginLog)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TLoginLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    TLoginLog queryById(Integer id );

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TLoginLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tLoginLog 实例对象
     * @return 对象列表
     */
    List<TLoginLog> queryAll(TLoginLog tLoginLog);

    List<TLoginLog> queryAllByToday();

    /**
     * 新增数据
     *
     * @param tLoginLog 实例对象
     * @return 影响行数
     */
    int insert(TLoginLog tLoginLog);

    /**
     * 修改数据
     *
     * @param tLoginLog 实例对象
     * @return 影响行数
     */
    int update(TLoginLog tLoginLog);

    /**
     * 通过主键删除数据
     *
     * @return 影响行数
     */
    int deleteById(Integer id );

}