package com.kk.novel.userInfo.service.impl;

import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.common.vueRouter.router.RouterMeta;
import com.kk.novel.common.vueRouter.router.VueRouter;
import com.kk.novel.common.vueRouter.router.util.TreeUtil;
import com.kk.novel.security.jwt.MD5Util;
import com.kk.novel.userInfo.dao.TMenuDao;
import com.kk.novel.userInfo.entity.TMenu;
import com.kk.novel.userInfo.entity.TUser;
import com.kk.novel.userInfo.dao.TUserDao;
import com.kk.novel.userInfo.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TUser)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserDao tUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public TUser queryById(Long userId) {
        return this.tUserDao.queryById(userId);
    }

    @Override
    public TUser queryByUserName(String username) {
        return this.tUserDao.queryByUserName(username);
    }

    @Override
    public List<TUser> queryByUser(TUser user) {
        return this.tUserDao.queryAll(user);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TUser> queryAllByLimit(int offset, int limit) {
        return this.tUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public TUser insert(TUser tUser) {
        tUser.setPassword("e10adc3949ba59abbe56e057f20f883e"); // 新添加用户密码默认： 123456
        tUser.setCreateTime(DateFormat.dateToString(new Date()));
        this.tUserDao.insert(tUser);
        return tUser;
    }

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public void update(TUser tUser) {
        tUser.setModifyTime(DateFormat.dateToString(new Date()));
        this.tUserDao.update(tUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.tUserDao.deleteById(userId) > 0;
    }

    @Override
    public String getRoleById(Long userId) {
        return this.tUserDao.getRoleById(userId);
    }

    @Override
    public boolean deleteByIds(String ids) {
        return this.tUserDao.deleteByIds(ids) > 0;
    }

    @Override
    public boolean updatePwd(Long id, String oldPwd, String newPwd) {
        TUser user = this.tUserDao.queryById(id);
        if (user.getPassword().equals(MD5Util.string2MD5(oldPwd))) {
            user.setPassword(MD5Util.string2MD5(newPwd));
            this.tUserDao.update(user);
            return true;
        }
        return false;
    }
}