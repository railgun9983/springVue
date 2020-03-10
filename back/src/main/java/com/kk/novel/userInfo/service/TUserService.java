package com.kk.novel.userInfo.service;

import com.kk.novel.common.vueRouter.router.VueRouter;
import com.kk.novel.userInfo.entity.TMenu;
import com.kk.novel.userInfo.entity.TUser;

import java.util.ArrayList;
import java.util.List;

/**
 * (TUser)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUser queryById(Long userId);

    /**
     * 通过name查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    TUser queryByUserName(String username);

    List<TUser> queryByUser(TUser user);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser insert(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     */
    void update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    String getRoleById(Long userId);

    boolean deleteByIds(String ids);

    boolean updatePwd(Long id, String oldPwd, String newPwd);
}