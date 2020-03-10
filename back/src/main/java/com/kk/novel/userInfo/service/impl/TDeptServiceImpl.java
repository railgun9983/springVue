package com.kk.novel.userInfo.service.impl;

import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.common.vueRouter.router.MenuTree;
import com.kk.novel.common.vueRouter.router.util.TreeUtil;
import com.kk.novel.userInfo.entity.TDept;
import com.kk.novel.userInfo.dao.TDeptDao;
import com.kk.novel.userInfo.service.TDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TDept)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tDeptService")
public class TDeptServiceImpl implements TDeptService {
    @Resource
    private TDeptDao tDeptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public TDept queryById(Long deptId) {
        return this.tDeptDao.queryById(deptId);
    }

    @Override
    public List<TDept> queryAll() {
        return this.tDeptDao.queryAllDepet();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDept> queryAllByLimit(int offset, int limit) {
        return this.tDeptDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public TDept insert(TDept tDept) {
        tDept.setCreateTime(DateFormat.dateToString(new Date()));
        this.tDeptDao.insert(tDept);
        return tDept;
    }

    /**
     * 修改数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public Boolean update(TDept tDept) {
        tDept.setModifyTime(DateFormat.dateToString(new Date()));
        return this.tDeptDao.update(tDept) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long deptId) {
        return this.tDeptDao.deleteById(deptId) > 0;
    }

    @Override
    public MenuTree<TDept> queryAll(TDept dept) {
        List<MenuTree<TDept>> trees = new ArrayList<>();
        List<TDept> depts = this.tDeptDao.queryAll(dept);
        buildTrees(trees, depts);
        return TreeUtil.build(trees);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public boolean deleteByIds(String ids) {
        return this.tDeptDao.deleteByIds(ids) > 0;
    }

    private void buildTrees(List<MenuTree<TDept>> trees, List<TDept> depts) {
        depts.forEach(dept -> {
            MenuTree<TDept> tree = new MenuTree<>();
            tree.setId(dept.getDeptId().toString());
            tree.setKey(tree.getId());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getDeptName());
            tree.setCreateTime(dept.getCreateTime());
            tree.setModifyTime(dept.getModifyTime());
            tree.setOrder(dept.getOrderNum());
            tree.setTitle(tree.getText());
            tree.setValue(tree.getId());
            trees.add(tree);
        });
    }

}