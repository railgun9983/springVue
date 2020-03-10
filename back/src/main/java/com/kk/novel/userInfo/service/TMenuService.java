package com.kk.novel.userInfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kk.novel.userInfo.entity.TMenu;
import java.util.List;
import java.util.Map;

/**
 * (TMenu)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TMenuService extends IService<TMenu> {

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


    Map<String, Object> queryMenuByEntry(TMenu menu);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    TMenu insert(TMenu tMenu);

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    TMenu update(TMenu tMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

    void deleteByIds(String ids);
}