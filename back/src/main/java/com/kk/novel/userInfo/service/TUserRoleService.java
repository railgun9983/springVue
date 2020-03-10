package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TUserRole;
import java.util.List;

/**
 * (TUserRole)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUserRole queryById(Long userId);

    /**
     * 通过ID查询数据 一个用户多个角色
     *
     * @param userId
     * @return 实例对象
     */
    List<TUserRole> queryAllById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUserRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    TUserRole insert(TUserRole tUserRole);

    /**
     * 修改数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    TUserRole update(TUserRole tUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    void updateUserRole(Long userId, List<String> roles);
}