package com.kk.novel.userInfo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.common.vueRouter.router.MenuTree;
import com.kk.novel.common.vueRouter.router.util.TreeUtil;
import com.kk.novel.userInfo.dao.TMenuDao;
import com.kk.novel.userInfo.entity.TMenu;
import com.kk.novel.userInfo.service.TMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * (TMenu)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Slf4j
@Service("tMenuService")
public class TMenuServiceImpl extends ServiceImpl<TMenuDao, TMenu> implements TMenuService {
    @Resource
    private TMenuDao tMenuDao;

    private final String TYPE_BUTTON = "1"; // 菜单 0, 按钮 1

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public TMenu queryById(Long menuId) {
        return this.tMenuDao.queryById(menuId);
    }

    @Override
    public List<TMenu> queryAllByUserId(Long userId) {
        return this.tMenuDao.queryAllByUserId(userId);
    }

    @Override
    public Map<String, Object> queryMenuByEntry(TMenu menu) {
        Map<String, Object> result = new HashMap<>();
        List<String> ids = new ArrayList<>();
        List<MenuTree<TMenu>> trees = new ArrayList<>();
        try {
            LambdaQueryWrapper<TMenu> queryWrapper = new LambdaQueryWrapper<>();
            if (!StringUtils.isEmpty(menu.getMenuName())) {
                queryWrapper.eq(TMenu::getMenuName, menu.getMenuName());
            }
            if (!StringUtils.isEmpty(menu.getType())) {
                queryWrapper.eq(TMenu::getType, menu.getType());
            }
            if (!StringUtils.isEmpty(menu.getCreateTime()) && !StringUtils.isEmpty(menu.getModifyTime())) {
                queryWrapper
                        .ge(TMenu::getCreateTime, menu.getCreateTime())
                        .le(TMenu::getCreateTime, menu.getModifyTime());
            }
            List<TMenu> menus = baseMapper.selectList(queryWrapper);
            buildTrees(trees, menus, ids);
            result.put("ids", ids);
            if (StringUtils.equals(menu.getType(), TYPE_BUTTON)) {
                result.put("rows", trees);
            } else {
                MenuTree<TMenu> menuTree = TreeUtil.build(trees);
                result.put("rows", menuTree);
            }
            result.put("total", menus.size());
        } catch (Exception e) {
            log.error("查询菜单失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return result;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TMenu> queryAllByLimit(int offset, int limit) {
        return this.tMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TMenu insert(TMenu tMenu) {
        tMenu.setCreateTime(DateFormat.dateToString(new Date()));
        this.tMenuDao.insert(tMenu);
        return tMenu;
    }

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TMenu update(TMenu tMenu) {
        tMenu.setModifyTime(DateFormat.dateToString(new Date()));
        this.tMenuDao.update(tMenu);
        return this.queryById(tMenu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.tMenuDao.deleteById(menuId) > 0;
    }

    @Override
    @Transactional
    public void deleteByIds(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        this.menuDelete(idList);
    }

    private void buildTrees(List<MenuTree<TMenu>> trees, List<TMenu> menus, List<String> ids) {
        for(TMenu menu : menus) {
            ids.add(menu.getMenuId().toString());
            MenuTree<TMenu> tree = new MenuTree<>();
            tree.setId(menu.getMenuId().toString());
            tree.setKey(tree.getId());
            tree.setParentId(menu.getParentId().toString());
            tree.setText(menu.getMenuName());
            tree.setTitle(tree.getText());
            tree.setIcon(menu.getIcon());
            tree.setComponent(menu.getComponent());
            tree.setCreateTime(menu.getCreateTime());
            tree.setModifyTime(menu.getModifyTime());
            tree.setPath(menu.getPath());
            tree.setOrder(menu.getOrderNum());
            tree.setPermission(menu.getPerms());
            tree.setType(menu.getType());
            tree.setIsShow(menu.getIsShow());
            trees.add(tree);
        }
    }

    private void menuDelete(List<String> ids) {
        this.baseMapper.deleteBatchIds(ids);
        LambdaQueryWrapper<TMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(TMenu::getParentId, ids);
        List<TMenu> menus = this.tMenuDao.selectList(lambdaQueryWrapper); // 查询父id的子菜单
        if (!CollectionUtils.isEmpty(menus)) {
            List<String> menuIds = new ArrayList<>();
            for (TMenu menu : menus) {
                menuIds.add(String.valueOf(menu.getMenuId()));
            }
            this.menuDelete(menuIds); // 递归删除子菜单
        }
    }
}