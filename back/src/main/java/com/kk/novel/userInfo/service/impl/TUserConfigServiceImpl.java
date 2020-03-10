package com.kk.novel.userInfo.service.impl;

import com.kk.novel.userInfo.entity.TUserConfig;
import com.kk.novel.userInfo.dao.TUserConfigDao;
import com.kk.novel.userInfo.service.TUserConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TUserConfig)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tUserConfigService")
public class TUserConfigServiceImpl implements TUserConfigService {
    @Resource
    private TUserConfigDao tUserConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public TUserConfig queryById(Long userId) {
        return this.tUserConfigDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TUserConfig> queryAllByLimit(int offset, int limit) {
        return this.tUserConfigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tUserConfig 实例对象
     * @return 实例对象
     */
    @Override
    public TUserConfig insert(TUserConfig tUserConfig) {
        this.tUserConfigDao.insert(tUserConfig);
        return tUserConfig;
    }

    /**
     * 修改数据
     *
     * @param tUserConfig 实例对象
     * @return 实例对象
     */
    @Override
    public TUserConfig update(TUserConfig tUserConfig) {
        this.tUserConfigDao.update(tUserConfig);
        return this.queryById(tUserConfig.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.tUserConfigDao.deleteById(userId) > 0;
    }
}