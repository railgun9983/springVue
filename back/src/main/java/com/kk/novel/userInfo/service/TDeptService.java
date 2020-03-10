package com.kk.novel.userInfo.service;

import com.kk.novel.common.vueRouter.router.MenuTree;
import com.kk.novel.userInfo.entity.TDept;
import java.util.List;

/**
 * (TDept)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    TDept queryById(Long deptId);

    List<TDept> queryAll();

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDept> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    TDept insert(TDept tDept);

    /**
     * 修改数据
     *
     * @param tDept 实例对象
     * @return Boolean
     */
    Boolean update(TDept tDept);

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    boolean deleteById(Long deptId);

    MenuTree<TDept> queryAll(TDept dept);

    boolean deleteByIds(String ids);
}