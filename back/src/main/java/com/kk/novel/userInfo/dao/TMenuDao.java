package com.kk.novel.userInfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kk.novel.userInfo.entity.TMenu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TMenu)表数据库访问层
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TMenuDao extends BaseMapper<TMenu> {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    TMenu queryById(Long menuId);

    /**
     * 通过userID查询数据
     *
     * @param userId
     * @return 实例对象
     */
    List<TMenu> queryAllByUserId(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TMenu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tMenu 实例对象
     * @return 对象列表
     */
    List<TMenu> queryAll(TMenu tMenu);

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int insert(TMenu tMenu);

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int update(TMenu tMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 影响行数
     */
    int deleteById(Long menuId);

    int deleteByIds(String menuIds);
}