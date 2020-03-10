package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.KCalendars;
import java.util.List;

/**
 * (KCalendars)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:00:56
 */
public interface KCalendarsService {

    /**
     * 通过ID查询单条数据
     *
     * @param schedName 主键
     * @return 实例对象
     */
    KCalendars queryById(String schedName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<KCalendars> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param kCalendars 实例对象
     * @return 实例对象
     */
    KCalendars insert(KCalendars kCalendars);

    /**
     * 修改数据
     *
     * @param kCalendars 实例对象
     * @return 实例对象
     */
    KCalendars update(KCalendars kCalendars);

    /**
     * 通过主键删除数据
     *
     * @param schedName 主键
     * @return 是否成功
     */
    boolean deleteById(String schedName);

}