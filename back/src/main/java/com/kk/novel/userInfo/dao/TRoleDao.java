package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.TRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TRole)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TRole queryById(Long roleId);

    List<TRole> queryList(TRole tRole);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tRole 实例对象
     * @return 对象列表
     */
    List<TRole> queryAll(TRole tRole);

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 影响行数
     */
    int insert(TRole tRole);

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 影响行数
     */
    int update(TRole tRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Long roleId);

    int deleteByIds(@Param("roleIds") List<String> roleIds);

}