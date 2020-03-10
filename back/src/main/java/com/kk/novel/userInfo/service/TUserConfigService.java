package com.kk.novel.userInfo.service;

import com.kk.novel.userInfo.entity.TUserConfig;
import java.util.List;

/**
 * (TUserConfig)表服务接口
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
public interface TUserConfigService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUserConfig queryById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUserConfig> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tUserConfig 实例对象
     * @return 实例对象
     */
    TUserConfig insert(TUserConfig tUserConfig);

    /**
     * 修改数据
     *
     * @param tUserConfig 实例对象
     * @return 实例对象
     */
    TUserConfig update(TUserConfig tUserConfig);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

}