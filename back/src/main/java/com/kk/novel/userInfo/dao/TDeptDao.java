package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.TDept;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TDept)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TDeptDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    TDept queryById(Long deptId);

    List<TDept> queryAllDepet();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDept> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDept 实例对象
     * @return 对象列表
     */
    List<TDept> queryAll(TDept tDept);

    /**
     * 新增数据
     *
     * @param tDept 实例对象
     * @return 影响行数
     */
    int insert(TDept tDept);

    /**
     * 修改数据
     *
     * @param tDept 实例对象
     * @return 影响行数
     */
    int update(TDept tDept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 影响行数
     */
    int deleteById(Long deptId);

    int deleteByIds(String ids);
}