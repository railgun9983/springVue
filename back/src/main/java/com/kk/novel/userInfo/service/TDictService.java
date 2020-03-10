package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TDict;
import java.util.List;

/**
 * (TDict)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TDictService {

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    TDict queryById(Long dictId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDict 实例对象
     * @return 实例对象
     */
    TDict insert(TDict tDict);

    /**
     * 修改数据
     *
     * @param tDict 实例对象
     * @return 实例对象
     */
    TDict update(TDict tDict);

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 是否成功
     */
    boolean deleteById(Long dictId);

}