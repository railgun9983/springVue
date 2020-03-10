package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.TUserConfig;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TUserConfig)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TUserConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUserConfig queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUserConfig> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tUserConfig 实例对象
     * @return 对象列表
     */
    List<TUserConfig> queryAll(TUserConfig tUserConfig);

    /**
     * 新增数据
     *
     * @param tUserConfig 实例对象
     * @return 影响行数
     */
    int insert(TUserConfig tUserConfig);

    /**
     * 修改数据
     *
     * @param tUserConfig 实例对象
     * @return 影响行数
     */
    int update(TUserConfig tUserConfig);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

}