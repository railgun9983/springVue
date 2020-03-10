package com.kk.novel.userInfo.dao;

import com.kk.novel.userInfo.entity.TUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TUser)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUser queryById(Long userId);

    /**
     * 通过ID查询单条数据
     *
     * @param username
     * @return 实例对象
     */
    TUser queryByUserName(String username);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tUser 实例对象
     * @return 对象列表
     */
    List<TUser> queryAll(TUser tUser);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    String getRoleById(Long userId);

    int deleteByIds(String ids);

}